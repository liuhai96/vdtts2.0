package com.lsjbc.vdtts.pojo.vo;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ResultData
 * @Description: 后台向前台返回的数据格式
 * @Datetime: 2020/6/6   15:07
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultData {

    /**
     * 错误代码
     * 正常为0
     * 异常为其他
     */
    @Builder.Default
    private Integer code = 0;

    /**
     * 执行结果后附带的信息
     */
    @Builder.Default
    private String msg = "";

    /**
     * 携带数据
     */
    @Builder.Default
    private Map<String, Object> data = new HashMap<>(10);


    //res.data.url

    /**
     * 向Data中存入数据
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, Object value) {
        data.put(key, value);
    }

    /**
     * 设置一个成功的Success对象
     *
     * @return 表示操作成功的ResultData对象
     * @author JX181114 --- 郑建辉
     */
    public static ResultData success() {
        return ResultData.builder().msg("操作成功").build();
    }

    /*
     *@Description:
     *@Author:李浪_191019
     *@Param:
     *@return:
     *@Date:2020/6/10 11:57
     **/
    public static ResultData success(String msg){
        return ResultData.builder().msg(msg).build();
    }

    /**
     * 设置一个成功的Success对象，并传入一个数据
     *
     * @param key   数据-键
     * @param value 数据-值
     * @return 表示操作成功的ResultData对象
     * @author JX181114 --- 郑建辉
     */
    public static ResultData success(String key, Object value) {
        ResultData resultData = new ResultData();

        resultData.put(key, value);

        return resultData;
    }


    /**
     * 设置一个出错的Error对象，并设置错误编码和消息
     *
     * @param code 成功编码
     * @param msg  消息
     * @return 表示操作成功的ResultData对象
     * @author JX191012 --- 刘海
     */
    public static ResultData success(Integer code, String msg) {
        return ResultData.builder().code(code).msg(msg).build();
    }

    /**
     * 设置一个出错的Error对象，并设置错误编码和消息
     *
     * @param code 错误编码
     * @param msg  消息
     * @return 表示操作失败的ResultData对象
     * @author JX181114 --- 郑建辉
     */
    public static ResultData error(Integer code, String msg) {
        return ResultData.builder().code(code).msg(msg).build();
    }

    /**
     * 设置一个正确的error对象，并附言和添加一个数据
     *
     * @param msg   消息
     * @param key   数据
     * @param value 数据
     * @return 表示操作正常的ResultData对象
     * @author JX181114 --- 郑建辉
     */
    public static ResultData success(String msg, String key, Object value) {
        ResultData resultData = success(msg);
        resultData.put(key, value);
        return resultData;
    }

    /**
     * 设置一个出错的Error对象，并设置消息
     *
     * @param msg 消息
     * @return 表示操作失败的ResultData对象
     * @author JX181114 --- 郑建辉
     */
    public static ResultData error(String msg) {
        return ResultData.builder().code(-1).msg(msg).build();
    }

    /**
     * 设置一个警告的Warning对象，并设置消息
     *
     * @param msg 消息
     * @return 表示操作异常的ResultData对象
     * @author JX181114 --- 郑建辉
     */
    public static ResultData warning(String msg) {
        return ResultData.builder().code(-2).msg(msg).build();
    }

    /**
     * 设置一个出错的Error对象
     *
     * @return 表示操作失败的ResultData对象
     * @author JX181114 --- 郑建辉
     */
    public static ResultData error() {
        return ResultData.builder().code(-1).msg("出现未知错误").build();
    }

    /**
     * 追加消息
     *
     * @param msg   追加的消息
     * @param split 与之前的消息分隔符
     * @author JX181114 --- 郑建辉
     */
    public void appendMessage(String msg, String split) {
        StringBuilder sb = new StringBuilder(this.msg);

        if (sb.length() > 0) {
            sb.append(split);
        }

        sb.append(msg);
        this.msg = sb.toString();
    }
}
