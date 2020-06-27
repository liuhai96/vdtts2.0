package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.SchoolMapper;
import com.lsjbc.vdtts.entity.School;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: SchoolDao
 * @Description:
 * @Datetime: 2020/6/12   11:53
 * @Author: JX181114 - 郑建辉
 */
@Repository(SchoolDao.NAME)
public class SchoolDao implements BaseDao<School> {
    /**
     * Bean名
     */
    public static final String NAME = "SchoolDao";

    @Resource
    private SchoolMapper mapper;

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
    public Integer add(School object) {
        return mapper.addSchool(object);
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
    public Integer updateById(School object) {
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

    /**
     * 根据驾校名称来进行模糊查询驾校信息
     *
     * @param name 驾校名称
     * @return 驾校集合
     * @author JX181114 --- 郑建辉
     */
    public List<School> getByNameLike(String name) {
        Example example = new Example(School.class);


        Example.Criteria criteria = example.createCriteria();

        criteria.andLike("sName", "%" + name + "%");

        return mapper.selectByExample(example);
    }

    //陈竑霖
    //通过主键来获取一个对象
    @Override
    public School getById(Integer id) {
        return (School) mapper.selectByPrimaryKey(id);
    }
}
