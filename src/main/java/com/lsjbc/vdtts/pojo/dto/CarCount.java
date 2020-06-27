package com.lsjbc.vdtts.pojo.dto;

import lombok.*;

/**
 * @ClassName: CarCount
 * @Description: 从数据库中查询出的驾校ID和所拥有的教练车数量
 * @Datetime: 2020/6/19   21:59
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarCount {

    /**
     * 驾校ID
     */
    private Integer schoolId;

    /**
     * 学员数量
     */
    private Integer count;

}
