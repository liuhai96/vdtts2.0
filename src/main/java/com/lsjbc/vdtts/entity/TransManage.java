package com.lsjbc.vdtts.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "`tb_trans_manage`")
public class TransManage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "`tm_id`")
  private Integer tmId;
  @Column(name = "`tm_account_id`")
  private Integer tmAccountId;
  @Column(name = "`tm_name`")
  private String tmName;
  @Column(name = "`tm_phone`")
  private String tmPhone;
  @Column(name = "`tm_addres`")
  private String tmAddres;
  @Column(name = "`tm_identity_id`")
  private Integer tmIdentityId;

}
