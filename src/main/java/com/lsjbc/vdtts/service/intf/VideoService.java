package com.lsjbc.vdtts.service.intf;

import com.lsjbc.vdtts.entity.Video;

import java.util.List;

/**
 * @ClassName: VideoService
 * @Description:
 * @Datetime: 2020/6/14   8:04
 * @Author: JX181114 - 郑建辉
 */
public interface VideoService {

    /**
     * 通过特定科目去获取教学视频
     *
     * @param level 特定科目
     * @return 教学视频集合
     */
    List<Video> getVideoByLevel(Integer level);

    /**
     * 通过主键来获取一条记录
     *
     * @param id 主键
     * @return 记录
     */
    Video getVideoById(Integer id);


}
