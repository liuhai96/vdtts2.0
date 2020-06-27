package com.lsjbc.vdtts.aop;

import com.lsjbc.vdtts.enums.OperateType;
import com.lsjbc.vdtts.enums.ResourceType;

import java.lang.annotation.*;

/**
 * @Description: 操作日志注解
 * @Author:
 * @Date: 2020/6/5 17:56
 */

@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 动作类型
     */
    OperateType operateType();

    /**
     * 资源类型
     */
    ResourceType resourceType();

    /**
     * 具体内容
     */
    String detail() default "";

}
