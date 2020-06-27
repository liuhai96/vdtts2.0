package com.lsjbc.vdtts.pojo.vo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PageData
 * @Description: 分页数据
 * @Datetime: 2020/6/8   16:20
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageData<T> {

    /**
     * 错误代码，正确是0
     */
    @Builder.Default
    private Integer code = 0;

    /**
     * 总页数
     */
    @Builder.Default
    private Integer pages = 0;

    @Builder.Default
    private String msg = "";

    /**
     * 总记录数
     */
    @Builder.Default
    private Long count = 0L;

    /**
     * 指定页数显示的数据
     */
    @Builder.Default
    private List<T> data = new ArrayList<>(0);
}
