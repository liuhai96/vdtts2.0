package com.lsjbc.vdtts.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
/**
 *考试结束表
 */
@Table(name = "`tb_exam_result`")
public class ExamResult {

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
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`er_id`")
	private Integer erId;

	/**
	 * 学员ID
	 */
	@Column(name = "`er_student_id`")
	private Integer erStudentId;

	/**
	 * 科一成绩
	 */
	@Column(name = "`er_score1`")
	private Integer erScore1;

	/**
	 * 科一考试状态
	 */
	@Column(name = "`er_state1`")
	private Integer erState1;

	/**
	 * 科二学时
	 */
	@Column(name = "`er_time2`")
	private Integer erTime2;

	/**
	 * 科二成绩
	 */
	@Column(name = "`er_score2`")
	private Integer erScore2;

	/**
	 * 科二考试状态
	 */
	@Column(name = "`er_state2`")
	private Integer erState2;

	/**
	 * 科三学时
	 */
	@Column(name = "`er_time3`")
	private Integer erTime3;

	/**
	 * 科三成绩
	 */
	@Column(name = "`er_score3`")
	private Integer erScore3;

	/**
	 * 科三考试状态
	 */
	@Column(name = "`er_state3`")
	private Integer erState3;

	/**
	 * 科四成绩
	 */
	@Column(name = "`er_score4`")
	private Integer erScore4;

	/**
	 * 科四考试状态
	 */
	@Column(name = "`er_state4`")
	private Integer erState4;

	/**
	 * 科四学时
	 */
	@Transient
	private Integer erTime4;

	@Transient
	private Student student;

	@Transient
	private String tName;

	/**
	 * 科目二学时转换为可读数据
	 */
	@Transient
	private String erTime2String;

	/**
	 * 科目三学时转换为可读数据
	 */
	@Transient
	private String erTime3String;
}
