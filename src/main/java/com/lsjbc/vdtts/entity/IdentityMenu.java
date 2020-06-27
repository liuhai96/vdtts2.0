package com.lsjbc.vdtts.entity;

import lombok.*;

import javax.persistence.*;

/*
前端菜单与身份关系表
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "`tb_identity_menu`")
public class IdentityMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`im_id`")
  private long imId;
    /**
     * 身份Id
     */
    @Column(name = "`in_identity_id`")
  private long imIdentityId;
    /**
     * 前端菜单Id
     */
    @Column(name = "`im_menu_id`")
  private long imMenuId;

}
