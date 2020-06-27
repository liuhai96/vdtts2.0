package com.lsjbc.vdtts.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsjbc.vdtts.service.intf.sfzService;
import com.lsjbc.vdtts.utils.HttpUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(sfzServiceImpl.NAME)
@Transactional
public class sfzServiceImpl implements sfzService
{

    /**
     * Bean名
     */
    public static final String NAME = "sfzService";

    @Resource
    private com.lsjbc.vdtts.dao.mapper.sfzMapper sfzMapper;


    @Override
    public String sfzdiscern(HttpServletRequest request, HttpServletResponse response, MultipartFile file)
    {

        String url = null;
        try
        {
            String path = request.getSession().getServletContext().getRealPath("/image");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-ddHHmmss");
            String date = df.format(new Date());
            String fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            url = path+"/"+date+fileName;
            System.out.println("路径："+path+"/"+date+fileName);
            file.transferTo(new File(path+"/"+date+fileName)); // 将接收的文件保存到指定文件中
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        String host = "http://dm-51.data.aliyun.com";
        String path = "/rest/160601/ocr/ocr_idcard.json";
        String appcode = "92f66ab7c6ab4e7fbb07fb18200d12cd";
        String imgFile = url;
        String method = "POST";

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");

        Map<String, String> querys = new HashMap<String, String>();
        // 对图像进行base64编码
        String imgBase64 = img_base64(imgFile);

        //configure配置
        JSONObject configObj = new JSONObject();
        configObj.put("side", "face");

        String config_str = configObj.toString();

        // 拼装请求body的json字符串
        JSONObject requestObj = new JSONObject();
        requestObj.put("image", imgBase64);
        if(configObj.size() > 0) {
            requestObj.put("configure", config_str);
        }
        String bodys = requestObj.toString();

        try {
            HttpResponse respons = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            int stat = respons.getStatusLine().getStatusCode();
            if(stat != 200){
                System.out.println("Http code: " + stat);
                System.out.println("http header error msg: "+ respons.getFirstHeader("X-Ca-Error-Message"));
                System.out.println("Http body error msg:" + EntityUtils.toString(respons.getEntity()));
                return null;
            }

            String res = EntityUtils.toString(respons.getEntity());
            JSONObject res_obj = JSON.parseObject(res);
            //打印
            System.out.println(res_obj.get("num"));
            System.out.println(res_obj.get("name"));
            System.out.println(res_obj.toJSONString());
            request.getSession().setAttribute("sfz",res_obj);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传身份证模糊换个照片";
    }

    public static String img_base64(String path) {
        /**
         *  对path进行判断，如果是本地文件就二进制读取并base64编码，如果是url,则返回
         */
        String imgBase64="";
        if (path.startsWith("http")){
            imgBase64 = path;
        }else {
            try {
                File file = new File(path);
                byte[] content = new byte[(int) file.length()];
                FileInputStream finputstream = new FileInputStream(file);
                finputstream.read(content);
                finputstream.close();
                imgBase64 = new String(Base64.encodeBase64(content));
            } catch (IOException e) {
                e.printStackTrace();
                return imgBase64;
            }
        }

        return imgBase64;
    }
}
