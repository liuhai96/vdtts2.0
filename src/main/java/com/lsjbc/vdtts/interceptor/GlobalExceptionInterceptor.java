package com.lsjbc.vdtts.interceptor;

import com.lsjbc.vdtts.pojo.vo.ResultData;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: GlobalExceptionInterceptor
 * @Description: 全局异常
 * @Datetime: 2020/6/8   10:54
 * @Author: JX181114 - 郑建辉
 */
public class GlobalExceptionInterceptor {

    /**
     * 处理参数异常
     *
     * @param request
     * @param exception
     * @return 返回给前端的信息
     * @author JX181114 --- 郑建辉
     */
    @ExceptionHandler(value = BindException.class)
    public ResultData methodArgumentNotValidExceptionHandler(HttpServletRequest request, BindException exception) {
        ResultData resultData = ResultData.error();

        resultData.setCode(-1);
        String msg = exception.getBindingResult().getFieldError().getDefaultMessage();
        resultData.setMsg(msg);

        return resultData;
    }
}
