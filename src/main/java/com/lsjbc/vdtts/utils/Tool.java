package com.lsjbc.vdtts.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Tool {
    public Tool() { }

    public int getRandom(int start, int end) {//区域随机数
        return new Random().nextInt(end - start) + start;
    }

    public String getRandCode(int count, String str) {//在str获取count位的随机码
        String verCode = "";
        if (str == null)//srt为空时在下面字符中随机获取count位返回
            str = "0mcSDFGxz2aZnRu5yb1vYf3gitO8PAE7reU9HXCoJKLBNVw6qQWsdIThjk4lpM";
        for (int i = 0; i < count; i++)
            verCode += str.charAt(getRandom(0, str.length()));
        return verCode;
    }

    public String getDate(String regex) {//日期格式
        SimpleDateFormat df = new SimpleDateFormat(regex);
        //设置日期格式yyyy-MM-dd HH:mm:ss精确到秒
        return df.format(new Date());
    }

    public String createMd5(String str) {//字符转MD5码
        MessageDigest md = null;
        byte[] array = null;
        try {
            md = MessageDigest.getInstance("MD5");
            array = md.digest(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFE) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }


    public String createMd5(File file) {//文件转ΪMD5码
        if(file == null)
            return null;
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);
            while((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bi.toString();
    }
    public String getPostfix(String str, String divide, String defaultReturnValue) {//获取字符串后缀
        if(defaultReturnValue == null || defaultReturnValue.equals("")) defaultReturnValue = "Othe";
        if(divide == null || divide.equals("")) divide = "\\.";
        String[] data = str.split(divide);
        if (data.length != 1) defaultReturnValue = data[data.length-1];
        return defaultReturnValue;
    }
    public String fileClass(String fileName) {//文件类型匹配方法
        String fileClass = "文档,声音,压缩包,可执行,图片,视频,语言,其他";//文件类型
        String data[] = {"txt,sql,ppt,doc,docx,xls,xlsx,hlp,html,pdf"/*文档*/
        ,"wav,aif,au,mp3,ram,wma,mmf,amr,aac,flac",/*声音*/"rar,zip,arj,gz,z"/*压缩包*/
        ,"exe,com,chm",/*可执行*/"bmp,gif,jpg,ic,png,tif",/*图片*/
        "AVI,WMV,MPEG,QuickTime,ealVideo,Flash,Mpeg-4,map4",/*视频*/
       "c,asm,for,lib,lst,msg,obj,pas,wki,bas,java"/*语言*/};
        String[] head = fileClass.split(",");
        for(int i=0;i < data.length;i++){
            if(Matching(data[i],fileName))return head[i];
        }
        return head[head.length-1];//返回文件类型名称
    }
    private boolean Matching(String content, String fileName){
        String datas[] = content.split(",");
        for(String name:datas){
            if(name.equalsIgnoreCase(fileName))
                return true;
        }
        return false;
    }
    /**
     * @Description 获取当前时间的毫秒值  时间搓
     * @Author  ZhengJianHui
     * @Date   2019/9/6 14:58
     * @Param  []
     * @Return      java.lang.String
     */
    public String getCurrentMillisecond(){
        return String.valueOf(Calendar.getInstance().getTimeInMillis());
    }
}
