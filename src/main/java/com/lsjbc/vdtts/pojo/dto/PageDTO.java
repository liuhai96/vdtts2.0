package com.lsjbc.vdtts.pojo.dto;


import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @Description: 分页参数
 * @Author:
 * @Date: 2020/6/5 17:30
 */

public class PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 第几页
     */
    private Integer page = 1;

    /**
     * 一页几行
     */
    private Integer limit = 25;

    /**
     * 起始行
     */
    private int startRow;
    /**
     * 末行
     */
    private int endRow;

    public PageDTO(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
        calculateStartAndEndRow();
    }

    public PageDTO() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * 计算起止行号
     */
    private void calculateStartAndEndRow() {
        this.startRow = this.page > 0 ? (this.page - 1) * this.limit : 0;
        this.endRow = this.startRow + this.limit * (this.page > 0 ? 1 : 0);
    }

    public int getStartRow() {
        return startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

