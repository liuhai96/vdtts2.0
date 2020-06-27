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
 * 	科目学时
 */
@Table(name = "`tb_exam_time`")
public class ExamTime {

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
	@Column(name = "`et_id`")
	private Integer etId;

	/**
	 * 学员ID
	 */
	@Column(name = "`et_student_id`")
	private Integer etStudentId;

	/**
	 * 学时
	 */
	@Column(name = "`et_time`")
	private String etTime;

	/**
	 * 产生学时的时间
	 */
	@Column(name = "`et_start`")
	private String etStart;

	/**
	 * 是否减半
	 */
	@Column(name = "`et_half`")
	private String etHalf;

	/**
	 * 是否有效学时
	 */
	@Column(name = "`et_effective`")
	private String etEffective;

	/**
	 * 科目级别
	 */
	@Column(name = "`et_level`")
	private Integer etLevel;
}
