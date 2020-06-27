package com.lsjbc.vdtts.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
/**
 *考试安排表
 */
public class Exam
{
	/**
	 *主键
	 */
	private Integer exId;

	/**
	 *学员ID
	 */
	private Integer exStudentId;

	/**
	 *教练ID
	 */
	private Integer exTeacherId;

	/**
	 *科目等级
	 */
	private String exLevel;
}
