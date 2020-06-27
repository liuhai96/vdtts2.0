package com.lsjbc.vdtts.utils;

import java.util.Random;

/**
 * @ClassName: CustomStringUtils
 * @Description: 字符串工具类
 * @Datetime: 2020/6/16   0:00
 * @Author: JX181114 - 郑建辉
 */
public class CustomStringUtils {

    /**
     * 身份证号加密
     *
     * @param idNumber 身份证号码
     * @return 加密之后的身份证号码
     */
    public static String encryptionIdCardNumber(String idNumber) {
        try {
            return idNumber.substring(0, 3) + "***********" + idNumber.substring(14);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < idNumber.length(); i++) {
                sb.append("*");
            }

            return sb.toString();
        }
    }

    /**
     * 生成一个固定长度的随机字符串
     *
     * @param index 长度
     * @return 随机字符串
     * @author JX181114 --- 郑建辉
     */
    public static String generatingVerificationCode(int index){

        //源字符串
        String sourceStr="1234567890";

        //生成的字符串
        StringBuffer code=new StringBuffer();

        Random random=new Random();

        for(int i=0;i<index;i++){
            code.append(sourceStr.charAt(random.nextInt(sourceStr.length())));
        }

        return code.toString();
    }

}
