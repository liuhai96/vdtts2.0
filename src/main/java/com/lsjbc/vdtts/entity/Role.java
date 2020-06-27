package com.lsjbc.vdtts.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
/**
 * 	角色表
 */
public class Role
{

	/**
	 * 主键
	 */
	private Integer rId;

	/**
	 * 角色名
	 */
	private String rName;
}
