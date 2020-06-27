package com.lsjbc.vdtts.service.impl;

import com.lsjbc.vdtts.dao.VideoDao;
import com.lsjbc.vdtts.entity.Video;
import com.lsjbc.vdtts.service.intf.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: VideoServiceImpl
 * @Description:
 * @Datetime: 2020/6/14   8:07
 * @Author: JX181114 - 郑建辉
 */
@Service(VideoServiceImpl.NAME)
public class VideoServiceImpl implements VideoService {

    /**
     * Bean名
     */
    public static final String NAME = "VideoService";

    @Resource(name = VideoDao.NAME)
    private VideoDao videoDao;


    /**
     * 通过特定科目去获取教学视频
     *
     * @param level 特定科目
     * @return 教学视频集合
     * @author JX181114 --- 郑建辉
     */
    @Override
    public List<Video> getVideoByLevel(Integer level) {
        return videoDao.getVideoByLevel(level);
    }

    /**
     * 通过主键来获取一条记录
     *
     * @param id 主键
     * @return 记录
     * @author JX181114 --- 郑建辉
     */
    @Override
    public Video getVideoById(Integer id) {
        return videoDao.getById(id);
    }
}
