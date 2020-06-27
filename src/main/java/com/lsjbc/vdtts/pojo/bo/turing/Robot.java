package com.lsjbc.vdtts.pojo.bo.turing;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Robot
 * @Description:
 * @Datetime: 2020/6/21   22:06
 * @Author: JX181114 - 郑建辉
 */
@Component(Robot.NAME)
public class Robot {

    /**
     * Bean名
     */
    public static final String NAME = "Robot";

    /**
     * 图灵机器人撕咬
     */
    private static final String PRIVATE_KEY = "22d02d41ea9943c191037f290a9ad807";

    /**
     * 接口地址
     */
    private static final String URL = "http://openapi.tuling123.com/openapi/api/v2";

    /**
     * 用户ID
     */
    private static final String UID = "0";

    /**
     * 和智能机器人交谈
     *
     * @param str 对机器人说的话
     * @return 机器人对你说的话
     * @author JX181114 --- 郑建辉
     */
    public String talkToRobot(String str) {
        try {
            //将发送的消息转换为特定格式的Json字符串
            String sendJsonStr = getReqJson(str);

            //发送请求,并获取返回值
            String getJsonStr = sendPost(URL, sendJsonStr);

            //从返回值中获取到机器人说的话
            return getResultMeg("["+getJsonStr+"]");
        } catch (Exception e) {
            e.printStackTrace();
            return "我不知道你说了什么？换个说法吧";
        }
    }


    /**
     * 将输入的信息转换为json对象
     *
     * @param content 消息内容
     * @return JSON字符串
     * @author JX181114 --- 郑建辉
     */
    public String getReqJson(String content) {
        //请求json
        JSONObject reqJson = new JSONObject();
        //输入类型
        int reqType = 0;

        //输入信息json,如文本，图片
        JSONObject perception = new JSONObject();

        //输入的信息
        JSONObject inputText = new JSONObject();
        inputText.put("text", content);

        perception.put("inputText", inputText);


        //用户信息
        JSONObject userInfo = new JSONObject();
        userInfo.put("apiKey", PRIVATE_KEY);
        userInfo.put("userId", UID);

        reqJson.put("reqType", reqType);
        reqJson.put("perception", perception);
        reqJson.put("userInfo", userInfo);
        return reqJson.toString();
    }

    /**
     * 发送请求
     *
     * @param url     接口地址
     * @param jsonStr 发送的数据
     * @return 接收到的数据
     * @author JX181114 --- 郑建辉
     */
    public String sendPost(String url, String jsonStr) {
        String status = "";
        //回复的信息
        String responseStr = "";
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            java.net.URL realUrl = new URL(url);
            //打开url连接
            URLConnection urlCon = realUrl.openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) urlCon;

            //设置请求属性
            httpUrlConnection.setRequestProperty("Content-Type", "application/json");
            httpUrlConnection.setRequestProperty("x-adviewrtb-version", "2.1");

            //发送post请求必须设置一下两行
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);

            //获取URLConnection对象对应的输出流
            out = new PrintWriter(httpUrlConnection.getOutputStream());
            //发送请求参数
            out.write(jsonStr);
            //flush输出流的缓冲
            out.flush();
            httpUrlConnection.connect();

            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                responseStr += line;
            }

            status = new Integer(httpUrlConnection.getResponseCode()).toString();

            httpUrlConnection.disconnect();

        } catch (IOException e) {
            System.out.println("发送post请求出现异常");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return responseStr;
    }

    /**
     * 从结果中找到消息并返回
     *
     * @param turingResult 调用接口返回的结果
     * @return 消息
     * @author JX181114 --- 郑建辉
     */
    public String getResultMeg(String turingResult) {
        JSONArray jsonArray = JSONArray.parseArray(turingResult);
        Map<String, Object> map = (Map<String, Object>) jsonArray.get(0);
        List<Object> resultList = (List<Object>) map.get("results");
        Map<String, Map<String, String>> valueMap = (Map<String, Map<String, String>>) resultList.get(0);
        return valueMap.get("values").get("text");
    }
}
