package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.LinkMapper;
import com.lsjbc.vdtts.entity.Link;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: LinkDao
 * @Description:
 * @Datetime: 2020/6/11   14:02
 * @Author: JX181114 - 郑建辉
 */
@Repository(LinkDao.NAME)
public class LinkDao implements BaseDao<Link> {

    /**
     * Bean名
     */
    public static final String NAME = "LinkDao";

    @Resource
    private LinkMapper mapper;

    /**
     * 获取所有的友情链接
     *
     * @return 数据库中的所有友情链接
     * @author JX181114 --- 郑建辉
     */
    public List<Link> getAll() {
        return mapper.selectAll();
    }

    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     */
    @Override
    public Link getById(Integer id) {
        return null;
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
    public Integer add(Link object) {
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
    public Integer updateById(Link object) {
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
