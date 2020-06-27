package com.lsjbc.vdtts.utils;


//import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSONObject;
import com.lsjbc.vdtts.interceptor.ExamEnity;
import com.lsjbc.vdtts.service.intf.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import net.sf.json.JSONObject;

public class GetExamQuestion<http> {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    @Autowired
    private  ExamQuestionService examQuestionService;
    //配置您申请的KEY
    public static final String APPKEY ="555673ecba856d1fa2b54ba864f3e452";

    //1.题库接口
    public static List getRequest1(String level){
        String result =null;
        String url ="http://api2.juheapi.com/jztk/query";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//您申请的appKey
        params.put("subject",level);//选择考试科目类型，1：科目1；4：科目4
        params.put("model","c1");//驾照类型，可选择参数为：c1,c2,a1,a2,b1,b2；当subject=4时可省略
        params.put("testType","rand");//测试类型，rand：随机测试（随机100个题目），order：顺序测试（所选科目全部题目）
        List<ExamEnity> examEnityList = new ArrayList<>();
        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            if (object.getIntValue("error_code") == 0) {
                examEnityList = JSONObject.parseArray(object.getString("result"), ExamEnity.class);//将题目result转换程list

            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        }
            catch (Exception e) {
            e.printStackTrace();
        }
        return examEnityList;
    }

    //2.answer字段对应答案
    public static void getRequest2(){
        String result =null;
        String url ="http://api2.juheapi.com/jztk/answers";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//您申请的appk

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);
            System.out.println("答案》》》》》》》》》"+object.size());
            if(object.getIntValue("error_code")==0){
                 Object object1 =  object.get("result");
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *;
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}