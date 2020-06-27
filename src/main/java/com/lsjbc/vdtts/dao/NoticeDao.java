package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.NoticeMapper;
import com.lsjbc.vdtts.entity.Notice;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: NoticeDao
 * @Description:
 * @Datetime: 2020/6/11   11:46
 * @Author: JX181114 - 郑建辉
 */
@Repository(NoticeDao.NAME)
public class NoticeDao implements BaseDao<Notice> {

    /**
     * Bean名
     */
    public static final String NAME = "NoticeDao";

    @Resource
    private NoticeMapper mapper;

    /**
     * 根据类型和时ID倒序获取不同的通知公告/法律法规
     *
     * @param type 通知类型
     * @param name 模糊搜索
     * @return 法律法规/通知公告集合
     * @author JX181114 --- 郑建辉
     */
    public List<Notice> getNoticeByTypeOrderByIdDesc(String type, String name) {
        Example example = new Example(Notice.class);
        example.orderBy("nId").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("nType", type);
        criteria.andLike("nName", "%" + name + "%");
        return mapper.selectByExample(example);
    }

    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     */
    @Override
    public Notice getById(Integer id) {
        return (Notice) mapper.selectByPrimaryKey(id);
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
    public Integer add(Notice object) {
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
    public Integer updateById(Notice object) {
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
