package com.lsjbc.vdtts.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`tb_log`")
public class TbLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "`id`")
  private Integer id;

  @Column(name = "`operator_no`")
  private String operatorNo;

  @Column(name = "`operator_name`")
  private String operatorName;

  @Column(name = "`create_time`")
  private String createTime;

  @Column(name = "`action_type`")
  private String actionType;

  @Column(name = "`content`")
  private String content;

  @Column(name = "`ip_address`")
  private String ipAddress;




}
