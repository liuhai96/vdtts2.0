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
 * 	驾校表
 */
@Table(name = "`tb_school`")
public class  School {

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
	 * 	主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`s_id`")
	private Integer sId;

	/**
	 * 	账号ID
	 */
	@Column(name = "`s_account_id`")
	private Integer sAccountId;

	/**
	 * 	账号表中账号
	 */
	@Transient
	private Integer sAccount;

	/**
	 * 	驾校名
	 */
	@Column(name = "`s_name`")
	private String sName;

	/**
	 * 	驾校地址
	 */
	@Column(name = "`s_address`")
	private String sAddress;

	/**
	 * 	驾校报名电话
	 */
	@Column(name = "`s_phone`")
	private String sPhone;

	/**
	 * 	统一信用代码
	 */
	@Column(name = "`s_business_id`")
	private String sBusinessId;

	/**
	 * 	营业执照照片保存路径
	 */
	@Column(name = "`s_business_pic`")
	private String sBusinessPic;

	/**
	 *法人身份证号
	 */
	@Column(name = "`s_owner_id`")
	private String sOwnerId;

	/**
	 *是否通过审核
	 */
	@Column(name = "`s_verification`")
	private String sVerification;

	/**
	 *是否允许招生
	 */
	@Column(name = "`s_recruit`")
	private String sRecruit;

	/**
	 *是否允许登录
	 */
	@Column(name = "`s_lock`")
	private String sLock;

	/**
	 *注册时间
	 */
	@Column(name = "`s_reg_time`")
	private String sRegTime;

	/**
	 *法人代表证件地址
	 */
	@Column(name = "`s_owner_pic`")
	private String sOwnerPic;


	@Column(name = "`s_image_url`")
	private String sImageUrl;


    /**
     *驾校报名费
     */
	@Column(name = "`s_registery_fee`")
    private Integer sRegisteryFee;


	@Column(name = "`s_identity_id`")
	private Integer sIdentityId;


	/**
	 * 驾校账号
	 */
	@Transient
    private String aAccount;

	/**
	 * 驾校密码
	 */
	@Transient
	private String aPassword;

	/**
	 * 学生人数
	 */
	@Transient
	private Integer studentCount;
}
