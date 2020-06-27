package com.lsjbc.vdtts.pojo.vo;

import com.lsjbc.vdtts.entity.School;
import lombok.*;

/**
 * @ClassName: SchoolDetail
 * @Description: 前台显示驾校列表时传输的数据
 * @Datetime: 2020/6/12   15:22
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SchoolDetail {

    /**
     * 驾校ID
     */
    private Integer id;

    /**
     * 驾校名字
     */
    private String name;

    /**
     * 驾校地址
     */
    private String address;

    /**
     * 驾校评分
     */
    private Double score;

    /**
     * 教练人数
     */
    private Integer teacherCount;

    /**
     * 教练车辆数
     */
    private Integer carCount;

    /**
     * 学员人数
     */
    private Integer studentCount;

    private String urlInfo;

    /**
     * 把平均分转换为小数点后保留一位的分数
     *
     * @param score 分数
     */
    public void setScore(Double score) {
        Double d = score * 10;
        Integer i = d.intValue();
        this.score = i / 10.0;
    }

    /**
     * 根据School对象生成SchoolDetail对象
     *
     * @param school 驾校对象
     * @return 驾校详细信息对象
     * @author JX181114 --- 郑建辉
     */
    public static SchoolDetail generateDetail(School school) {
        SchoolDetail detail = SchoolDetail.builder().id(school.getSId())
                .name(school.getSName()).address(school.getSAddress())
                .address(school.getSAddress()).build();
        return detail;
    }
}
