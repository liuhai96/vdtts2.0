package com.lsjbc.vdtts.enums;

/**
 * @Description: 统一返回码定义
 * @Author:
 * @Date: 2020/6/4 12:21
 */

public enum ReturnStatus {

    //成功
    SUCCESS(0, "成功"),
    SC_BAD_REQUEST(400, "错误的入参(参数基础验证失败)"),
    SC_UNAUTHORIZED(401, "没有权限"),
    SC_FORBIDDEN(403, "拒绝操作"),
    SC_NOT_FOUND(404, "找不到资源"),
    //系统内部异常
    SC_INTERNAL_SERVER_ERROR(500, "系统内部异常"),

    //未登录
    NO_LOGIN(600, "未登录"),

    //逻辑验证未通过
    LOGICAL_VALIDATE_FAILED(601, "逻辑验证未通过"),

    //消息重复发送
    MESSAGE_REPEAT(602, "消息重复发送"),

    //连接超时
    TIME_OUT(603, "连接超时"),

    //数据库执行异常
    JDBC_ERROR(700, "数据库执行异常");

    private int code;

    private String description;

    ReturnStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
