package com.lsjbc.vdtts.pojo.vo;

import lombok.*;

import java.util.List;

/**
 * @ClassName: LayuiPageData
 * @Description: Layui分页的实体类
 * @Datetime: 2020/6/12   0:07
 * @Author: JX181114 - 郑建辉
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LayuiPageData<T> {

    /**
     * 错误代码
     * 正确为0
     */
    @Builder.Default
    private Integer code = 0;

    /**
     * 错误消息
     */
    @Builder.Default
    private String msg = "";

    /**
     * 总记录数
     */
    private Long count;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 群聊记录
     * 查询私聊记录时不做任何操作
     */
    private List<T> list;
}
