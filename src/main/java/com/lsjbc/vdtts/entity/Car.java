package com.lsjbc.vdtts.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
/*
 *@Description:
 *@Author:陈竑霖
 *@Param:
 *@return:
 *@Date:2020/6/9 1591684823042
 **/
/**
 * 	教练车表
 */
@Table(name = "`tb_car`")
public class Car {

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
	 *主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`c_id`")
	private Integer cId;

	/**
	 * 	驾校ID
	 */
	@Column(name = "`c_school_id`")
	private Integer cSchoolId;

	/**
	 * 	品牌
	 */
	@Column(name = "`c_logo`")
	private String cLogo;

	/**
	 * 	型号
	 */
	@Column(name = "`c_model`")
	private String cModel;

	/**
	 * 	颜色
	 */
	@Column(name = "`c_color`")
	private String cColor;

	/**
	 * 	车牌号
	 */
	@Column(name = "`c_number`")
	private String cNumber;

	/**
	 * 	教练ID
	 */
	@Column(name = "`c_teacher_id`")
	private Integer cTeacherId;

	/**
	 * 所属驾校名
	 */
	@Transient
	private String schoolName;


	/**
	 * 教练
	 */
	@Transient
	private Teacher teacher;

	
	/**
	 * 教练名字
	 */
	@Transient
	private String tName;

	/**
	 * 车辆禁用
	 */
	@Transient
	private String cLock;
}
