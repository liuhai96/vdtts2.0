package com.lsjbc.vdtts.pojo.vo;

import com.lsjbc.vdtts.entity.ExamTime;
import com.lsjbc.vdtts.utils.CustomTimeUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Min;

/**
 * @ClassName: ExamTimeNew
 * @Description: 用户提交新学时申请
 * @Datetime: 2020/6/14   21:55
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ExamTimeNew {

    /**
     * 前端传入的学员ID
     */
    @Min(value = 0, message = "学员ID不能小于1")
    private Integer studentId;

    /**
     * 前端传入的模拟考试分数
     */
    @Min(value = -1, message = "学时不得小于0")
    private Integer time;

    /**
     * 前端传入的科目等级
     */
    private Integer level;

    /**
     * 根据现有对象生成一个完整时长的学时记录
     *
     * @param half      是否学时减半：减半：true    完整学时：false
     * @param effective 是否有效学时：有效：true    无效：false
     * @return 学时记录
     */
    public ExamTime generateBean(Boolean half, Boolean effective) {
        Long startTime = System.currentTimeMillis() - time * 1000;

        if (half) {
            time = time / 2;
        }

        return ExamTime.builder()
                .etStudentId(studentId)
                .etTime(CustomTimeUtils.turnSecondsToString(time))
                .etStart(CustomTimeUtils.getTimeStringFromMills(startTime))
                .etHalf(half.toString())
                .etEffective(effective.toString())
                .etLevel(level)
                .build();

    }
}
