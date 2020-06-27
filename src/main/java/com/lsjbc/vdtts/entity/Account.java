package com.lsjbc.vdtts.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 前台人员账号表
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`tb_account`")
public class Account
{

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`a_id`")
	private Integer aId;

	/**
	 * 账号
	 */
	@Column(name = "`a_account`")
	private String aAccount;

	/**
	 * 密码
	 */
	@Column(name = "`a_password`")
	private String aPassword;

	/**
	 * 要查询的表名
	 */
	@Column(name = "`a_type`")
	private String aType;


	@Override
	public String toString() {
		return "Account{" +
				"aId=" + aId +
				", aAccount='" + aAccount + '\'' +
				", aPassword='" + aPassword + '\'' +
				", aType='" + aType + '\'' +
				'}';
	}
}
