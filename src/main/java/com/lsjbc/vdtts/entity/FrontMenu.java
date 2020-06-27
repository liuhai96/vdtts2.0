package com.lsjbc.vdtts.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

/*
前端菜单表
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "`tb_front_menu`")
public class FrontMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`fr_menu_id`")
    private Integer frMenuId;
  /**
   * 菜单名
   */
  @Column(name = "`fr_menu_name`")
  private String frMenuName;
  /**
   * 菜单路径
   */
  @Column(name = "`fr_url`")
  private String frUrl;
  /**
   * 父菜单ID
   */
  @Column(name = "`fr_parent_id`")
  private long frParentId;
  /**
   * 备注
   */
  @Column(name = "`fr_icon`")
  private String frIcon;

  @Transient
  private ArrayList<FrontMenu> children;

  @Transient
  private String title;

  @Transient
  private int id;

  @Transient
  private String type;

  @Transient
  private String icon;

  @Transient
  private String href;

  @Transient
  private String openType;

  @Transient
  private String checked;

}
