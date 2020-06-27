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
 *评价表
 */
@Table(name = "`tb_evaluate`")
public class Evaluate {

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
	@Column(name = "`e_id`")
	private Integer eId;

	/**
	 * 学员ID
	 */
	@Column(name = "`e_student_id`")
	private Integer eStudentId;

	/**
	 * 评价对象ID，教练ID或驾校ID
	 */
	@Column(name = "`e_to_id`")
	private Integer eToId;

	/**
	 * 推荐指数
	 */
	@Column(name = "`e_score`")
	private Float eScore;

	/**
	 * 评语
	 */
	@Column(name = "`e_content`")
	private String eContent;

	/**
	 * 评论时间
	 */
	@Column(name = "`e_time`")
	private String eTime;

	/**
	 * 评价对象类型：教练/驾校
	 */
	@Column(name = "`e_type`")
	private String eType;
}
