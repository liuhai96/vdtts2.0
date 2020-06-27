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
 *@Date:2020/6/9 1591681355328
 **/
/**
 * 	教练表
 */
@Table(name = "`tb_teacher`")
public class Teacher {

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
	@Column(name = "`t_id`")
	private Integer tId;

	/**
	 * 账号ID
	 */
	@Column(name = "`t_account_id`")
	private Integer tAccountId;

	/**
	 * 驾校ID
	 */
	@Column(name = "`t_school_id`")
	private Integer tSchoolId;

	/**
	 * 身份证号
	 */
	@Column(name = "`t_sfz`")
	private String tSfz;

	/**
	 * 姓名
	 */
	@Column(name = "`t_name`")
	private String tName;

	/**
	 * 性别
	 */
	@Column(name = "`t_sex`")
	private String tSex;

	/**
	 * 出生日期
	 */
	@Column(name = "`t_birthday`")
	private String tBirthday;

	/**
	 * 电话号码
	 */
	@Column(name = "`t_phone`")
	private String tPhone;

	/**
	 * 是否允许教新学员
	 */
	@Column(name = "`t_teach`")
	private String tTeach;

	/**
	 * 是否允许登录
	 */
	@Column(name = "`t_lock`")
	private String tLock;

	/**
	 * 获取驾照时间
	 */
	@Column(name = "`t_license_time`")
	private String tLicenseTime;

	/**
	 * 本月毕业学员数
	 */
	@Column(name = "`t_count`")
	private String tCount;

	@Column(name = "`t_identity_id`")
	private Integer tIdentityId;

	/**
	 * 本月限制毕业学员数
	 */
	@Column(name = "`t_limit`")
	private String tLimit;

	/**
	 * 教练头像
	 */
    @Column(name = "`t_pic`")
	private String tPic;
	/**
	 * 教练所属的驾校名
	 */
	@Transient
	private String schoolName;

// 周永哲
	/**
	 *教练对应的驾校
	 */
	@Transient
	private String aAccount;

//	刘海
	/**
	 * 教练对应的驾校
	 */

	/**
	 * 教练所属的学校名
	 */
	@Transient
	private String sName;

	//李浪
    /**
     * 评分
     */
    @Transient
    private Integer evaluate;

    //李浪
    /**
     * 总培训人数
     */
    @Transient
    private Integer count;

	@Transient
    private String account;
}
