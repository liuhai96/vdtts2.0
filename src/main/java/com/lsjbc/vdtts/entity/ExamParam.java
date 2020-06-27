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
 * 	参数表
 */
@Table(name = "`tb_param`")
public class ExamParam
{

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`pm_id`")
	private Integer pmId;

	/**
	 *键
	 */
	@Column(name = "`pm_key`")
	private String pmKey;

	/**
	 *值
	 */
	@Column(name = "`pm_value`")
	private String pmValue;

	/**
	 *描述
	 */
	@Column(name = "`pm_describe`")
	private String pmDescribe;
}
