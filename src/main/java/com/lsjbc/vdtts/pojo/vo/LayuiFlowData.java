package com.lsjbc.vdtts.pojo.vo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LayuiFlowData
 * @Description: Layui的流加载格式
 * @Datetime: 2020/6/16   0:22
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LayuiFlowData<T> {

    /**
     * 代码，正确为0
     * 错误为其他
     */
    private Integer code = 0;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 消息
     */
    private String msg = "";

    /**
     * 数据
     */
    private List<T> data = new ArrayList<>(0);
}
