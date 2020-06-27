package com.lsjbc.vdtts.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 错题记录表
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "`tb_exam_error`")
public class ExamError
{

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
	@Column(name = "`ee_id`")
	private Integer eeId;

	/**
	 * 模拟考试记录ID
	 */
	@Column(name = "`ee_student_id`")
	private Integer eeStudentId;

	/**
	 *错题ID
	 */
	@Column(name = "`ee_question_id`")
	private Integer eeQuestionId;

	/**
	 *错题ID
	 */
	@Column(name = "`ee_level`")
	private Integer eeLevel;
}
