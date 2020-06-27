package com.lsjbc.vdtts.utils;

import com.lsjbc.vdtts.enums.ReturnStatus;

/**
 * @Description: 异常返回对象
 * @Author:
 * @Date: 2020/6/4 12:21
 */

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    public BusinessException() {
    }

    public BusinessException(ReturnStatus status, String msg) {
        super(msg);
        this.code = status.getCode();
    }


    public BusinessException(ReturnStatus status, String message, Throwable cause) {
        super(message, cause);
        this.code = status.getCode();
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}