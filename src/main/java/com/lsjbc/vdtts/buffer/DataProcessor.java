package com.lsjbc.vdtts.buffer;

import java.util.List;

/**
 * @Description: 数据处理器接口
 * @Author:
 * @Date: 2020/6/6 13:14
 */

public interface DataProcessor<T> {
    /**
     * 功能描述: 处理数据
     *
     * @param data 数据集合
     */
    void process(List<T> data);
}
