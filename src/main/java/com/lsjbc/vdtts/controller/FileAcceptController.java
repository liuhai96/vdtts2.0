package com.lsjbc.vdtts.controller;

import com.alibaba.fastjson.JSON;
import com.lsjbc.vdtts.pojo.vo.FileAccept;
import com.lsjbc.vdtts.utils.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author LiLang9725
 * @date 2020/6/11 17:06
 */
@RequestMapping
@Controller
public class FileAcceptController {

    @RequestMapping(value = "/upImage")
    @ResponseBody
    /*
     *@Description:
     *@Author:李浪_191019
     *@Param:[imgFile]
     *@return:java.lang.Object
     *@Date:2020/6/11 21:18
     * 用途：接收本地上传的文件 并上传到保存到git路径
     **/
    private Object FileUpImage(@RequestParam("file") MultipartFile imgFile, HttpServletRequest request){
        long startTime = System.currentTimeMillis();
        FileAccept fileAccept = new FileAccept();
        if (imgFile.isEmpty()) fileAccept.setFProResult("上传失败");
        // 拿到文件名
        String filename = imgFile.getOriginalFilename();
        filename = filename.substring(filename.lastIndexOf(".") + 1);
        filename = new Tool().getCurrentMillisecond()+"."+filename;
        fileAccept.setFName(filename);//返回对象的名称赋值
        // 存放上传图片的文件夹
        File fileDir = fileAccept.getImgDirFile(request.getSession().
                getServletContext().getRealPath("/image/"));//存放image路径（动态）
        System.out.println(fileDir.getAbsolutePath());
        fileAccept.setFPath("/image/"+filename);//返回对象的路径赋值
        try {// 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
            fileAccept.setFSize( newFile.length());
            imgFile.transferTo(newFile);
            fileAccept.setFProResult("上传成功");
        } catch (IOException e) { e.printStackTrace(); }
        long endTime=System.currentTimeMillis();
        fileAccept.setFProTime((endTime-startTime)+"ms");
        return JSON.toJSONString(fileAccept);
    }
}
