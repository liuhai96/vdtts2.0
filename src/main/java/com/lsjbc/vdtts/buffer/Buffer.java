package com.lsjbc.vdtts.buffer;

/**
 * @Description: buffer
 * @Author:
 * @Date: 2020/6/6 13:14
 */

public interface Buffer<T> {
    /**
     * 功能描述: 放入数据
     *
     * @param data 数据
     */
    void put(T data);
    /**
     * 功能描述: 关闭
     *
     */
    void close();
}
