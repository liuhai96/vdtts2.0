package com.lsjbc.vdtts.entity;

import lombok.*;

import javax.persistence.*;

/**
 *模拟考试记录表
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "`tb_exam_simulate_record`")
public class ExamSimulateRecord {
	/**
	 *主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`esr_id`")
	private Integer esrId;

	/**
	 *学员ID
	 */
	@Column(name = "`esr_student_id`")
	private Integer esrStudentId;

	/**
	 *模拟考试成绩
	 */
	@Column(name = "`esr_score`")
	private Integer esrScore;

	/**
	 *科目等级
	 */
	@Column(name = "`esr_level`")
	private Integer esrLevel;

	/**
	 *模拟考试时间
	 */
	@Column(name = "`esr_time`")
	private String esrTime;
}
