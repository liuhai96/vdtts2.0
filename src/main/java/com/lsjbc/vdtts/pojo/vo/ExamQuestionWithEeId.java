package com.lsjbc.vdtts.pojo.vo;

import com.lsjbc.vdtts.entity.ExamAnswer;
import com.lsjbc.vdtts.entity.ExamError;
import com.lsjbc.vdtts.entity.ExamQuestion;
import lombok.*;

import java.util.List;

/**
 * @ClassName: ExamQuestionWithEeId
 * @Description: 携带错题记录ID的题目对象
 * @Datetime: 2020/6/10   13:58
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ExamQuestionWithEeId {

    /**
     * 构造方法
     *
     * @param ee       ExamError对象
     * @param question 与ExamError对象有关联的ExamQuestion对象
     */
    public ExamQuestionWithEeId(ExamError ee, ExamQuestion question) {
        this.eeId = ee.getEeId();
        this.eqId = question.getEqId();
        this.eqQuestion = question.getEqQuestion();
        this.eqPic = question.getEqPic();
        this.eqLevel = question.getEqLevel();
        this.answers = question.getAnswers();
    }

    /**
     * 错题记录ID
     */
    private Integer eeId;

    /**
     * ID
     */
    private Integer eqId;

    /**
     * 问题
     */
    private String eqQuestion;

    /**
     * 路径
     */
    private String eqPic;

    /**
     * 科目等级
     */
    private Integer eqLevel;

    /**
     * 题目对应的答案
     */
    private List<ExamAnswer> answers;
}
