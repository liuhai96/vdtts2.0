/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.lsjbc.vdtts.utils.baidu.client;


import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

/**
 * Json工具类.
 */
public class GsonUtils {

    public static String toJson(Object value) {
        return JSON.toJSONString(value);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws Exception {
        return JSON.parseObject(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) throws Exception {
        return (T) JSON.parseObject(json, typeOfT);
    }
}
