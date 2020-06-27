package com.lsjbc.vdtts.pojo.vo;

import lombok.*;

/**
 * @ClassName: PowerSchool
 * @Description: 返回给首页的有实力的驾校排名
 * @Datetime: 2020/6/19   22:22
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PowerSchool {

    /**
     * 驾校名
     */
    private String name;

    /**
     * 驾校所拥有的车辆数
     */
    private Integer count;
}
