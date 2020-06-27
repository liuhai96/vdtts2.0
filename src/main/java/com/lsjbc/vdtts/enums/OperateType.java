package com.lsjbc.vdtts.enums;

/**
 * @Description: 操作类型枚举
 * @Author: changhao.chen@luckincoffee.com
 * @Date: 2020/6/5 17:48
 */

public enum OperateType {
    /**
     * 新增
     */
    ADD("新增"),
    /**
     * 删除
     */
    DELETE("删除"),
    /**
     * 更新
     */
    MODIFY("更新"),
    /**
     * 查询
     */
    QUERY("查询");
    private String name;

    public String getName() {
        return name;
    }

    OperateType(String name) {
        this.name = name;
    }

    public static OperateType getTarget(String value) {
        OperateType[] operateTypes = OperateType.values();
        OperateType   target        = null;
        for (OperateType operateType : operateTypes) {
            if (value.equals(operateType.getName())) {
                target = operateType;
                break;
            }
        }
        return target;
    }
}
