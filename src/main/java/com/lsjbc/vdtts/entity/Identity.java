package com.lsjbc.vdtts.entity;

import lombok.*;

import javax.persistence.*;

/*前端人员身份表*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "`tb_identity`")
public class Identity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`identity_id`")
  private long identityId;

    /**
     * 驾校ID
     */
    @Column(name = "`identity_name`")
  private String identityName;
}
