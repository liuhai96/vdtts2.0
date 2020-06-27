package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.VideoMapper;
import com.lsjbc.vdtts.entity.Video;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: VideoDao
 * @Description:
 * @Datetime: 2020/6/14   8:01
 * @Author: JX181114 - 郑建辉
 */
@Repository(VideoDao.NAME)
public class VideoDao implements BaseDao<Video> {

    /**
     * Bean名
     */
    public static final String NAME = "VideoDao";

    @Resource
    private VideoMapper mapper;

    /**
     * 根据特定科目的教学视频
     *
     * @param level 科目等级
     * @return 教学视频集合
     * @authoe JX181114 --- 郑建辉
     */
    public List<Video> getVideoByLevel(Integer level) {
        Example example = new Example(Video.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("vLevel", level);

        return mapper.selectByExample(example);
    }

    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     * @authoe JX181114 --- 郑建辉
     */
    @Override
    public Video getById(Integer id) {
        return (Video) mapper.selectByPrimaryKey(id);
    }

    /**
     * 新增对象
     * 注意
     * 调用这个方法，会自动的向对象中注入主键
     * 所以返回的不是主键，而是受影响条数
     *
     * @param object 对象
     * @return 受影响条数
     */
    @Override
    public Integer add(Video object) {
        return null;
    }

    /**
     * 根据ID来更新对象
     * 注意
     * 传入的obj对象中主键不得为空
     * 否则会抛出异常
     *
     * @param object 对象
     * @return 受影响条数
     */
    @Override
    public Integer updateById(Video object) {
        return null;
    }

    /**
     * 根据主键来删除记录
     *
     * @param id 主键
     * @return 受影响条数
     */
    @Override
    public Integer deleteById(Integer id) {
        return null;
    }
}
