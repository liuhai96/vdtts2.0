package com.lsjbc.vdtts.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 学员表
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "`tb_student`")
public class Student {

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
	@Column(name = "`s_id`")
	private Integer sId;

	/**
	 * 账号ID
	 */
	@Column(name = "`s_account_id`")
	private Integer sAccountId;

	/**
	 * 驾校ID
	 */
	@Column(name = "`s_school_id`")
	private Integer sSchoolId;

	/**
	 * 身份证号
	 */
	@Column(name = "`s_sfz`")
	private String sSfz;

	/**
	 * 姓名
	 */
	@Column(name = "`s_name`")
	private String sName;

	/**
	 * 电话号码
	 */
	@Column(name = "`s_phone`")
	private String sPhone;

	/**
	 * 性别
	 */
	@Column(name = "`s_sex`")
	private String sSex;

	/**
	 * 出生日期
	 */
	@Column(name = "`s_birthday`")
	private String sBirthday;

	/**
	 * 人像保存路径
	 */
	@Column(name = "`s_pic`")
	private String sPic;

	/**
	 * 获取驾照日期
	 */
	@Column(name = "`s_license_time`")
	private String sLicenseTime;

	/**
	 *注册时间
	 */
	@Column(name = "`s_reg_time`")
	private String sRegTime;

	/**
	 * 所属教练ID
	 */
	@Column(name = "`s_teacher_id`")
	private Integer sTeacherId;

	/**
	 * 报名状态
	 */
	@Column(name = "`s_apply_state`")
	private Integer sApplyState;

	/**
	 * 驾校姓名
	 */
	@Transient
	private String schoolName;

	/**
	 *所属教练姓名
	 */
	@Transient
	private String teacherName;

	/**
	 *学员在考科目1人数
	 */
	@Transient
	private Integer state1;
	/**
	 *学员在考科目2人数
	 */
	@Transient
	private Integer state2;
	/**
	 *学员在考科目3人数
	 */
	@Transient
	private Integer state3;
	/**
	 *学员在考科目4人数
	 */
	@Transient
	private Integer state4;
}
