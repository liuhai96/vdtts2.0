package com.lsjbc.vdtts.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @ClassName: ExamAnswer
 * @Description: 题库答案表
 * @Datetime: 2020/6/6   11:29
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "`tb_exam_answer`")
public class ExamAnswer {

    /**
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     */

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ea_id`")
    private Integer eaId;

    /**
     * 所属问题ID
     */
    @Column(name = "`ea_question_id`")
    private Integer eaQuestionId;

    /**
     * 答案
     */
    @Column(name = "`ea_answer`")
    private String eaAnswer;

    /**
     * 是否正确答案
     */
    @Column(name = "`ea_right`")
    private String eaRight;

    /**
     * 答案所属的科目等级
     */
    @Column(name = "`ea_level`")
    private Integer eaLevel;

}
