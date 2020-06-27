package com.lsjbc.vdtts.api;

import com.lsjbc.vdtts.entity.Video;
import com.lsjbc.vdtts.service.impl.VideoServiceImpl;
import com.lsjbc.vdtts.service.intf.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: VideoApi
 * @Description:
 * @Datetime: 2020/6/14   8:10
 * @Author: JX181114 - 郑建辉
 */
@CrossOrigin
@RestController
@RequestMapping("api/video")
public class VideoApi {

    @Resource(name = VideoServiceImpl.NAME)
    private VideoService videoService;

    /**
     * 传回特定科目的教学时评
     *
     * @param level 指定科目
     * @return 教学视频集合
     * @author JX181114 --- 郑建辉
     */
    @GetMapping("level/{level}")
    public List<Video> getVideoByLevel(@PathVariable("level") Integer level) {
        return videoService.getVideoByLevel(level);
    }

    @GetMapping("id/{id}")
    public Video getVideoById(@PathVariable("id") Integer id){
        return videoService.getVideoById(id);
    }

}
