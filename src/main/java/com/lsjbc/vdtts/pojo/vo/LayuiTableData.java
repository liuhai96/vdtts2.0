package com.lsjbc.vdtts.pojo.vo;


import lombok.*;

import java.util.List;

/**
 * @author csd
 * layui表格的实体类
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LayuiTableData {
    /**
     * 错误代码，正确(默认)为0
     */
    private int code;

    /**
     * 返回给前端的消息
     */
    private String msg = "";

    /**
     * 总记录数
     */
    private Long count;

    /**
     * 数据
     */
    private List<?> data;

    /**
     * 将Integer转换为Long，并进行赋值
     *
     * @param count count
     * @author JX181114 --- 郑建辉
     */
    public void setCount(Integer count) {
        this.count = count.longValue();
    }

    /**
     * 赋值Count
     *
     * @param count count
     * @author JX181114 --- 郑建辉
     */
    public void setCount(Long count) {
        this.count = count;
    }

}
