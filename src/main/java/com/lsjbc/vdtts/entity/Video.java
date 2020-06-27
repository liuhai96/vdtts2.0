package com.lsjbc.vdtts.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @ClassName: Video
 * @Description: 教学视频表
 * @Datetime: 2020/6/14   7:56
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "`tb_video`")
public class Video {

    /**
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     * 所有非数据库的字段通通加上@Transient注解
     */

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`v_id`")
    private Integer vId;

    /**
     * 视频截图路径
     */
    @Column(name = "`v_pic`")
    private String vPic;

    /**
     * 视频保存路径
     */
    @Column(name = "`v_video`")
    private String vVideo;

    /**
     * 视频标题
     */
    @Column(name = "`v_title`")
    private String vTitle;

    /**
     * 视频所属科目
     */
    @Column(name = "`v_level`")
    private Integer vLevel;


}
