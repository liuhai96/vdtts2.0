package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.TeacherMapper;
import com.lsjbc.vdtts.entity.Teacher;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: TeacherDao
 * @Description:
 * @Datetime: 2020/6/12   14:15
 * @Author: JX181114 - 郑建辉
 */
@Repository(TeacherDao.NAME)
public class TeacherDao implements BaseDao<Teacher> {

    /**
     * Bean名
     */
    public static final String NAME = "TeacherDao";

    @Resource
    private TeacherMapper mapper;

    /**
     * 查询出该驾校下所拥有的教练数字
     *
     * @param schoolId 驾校ID
     * @return 教练数量
     * @author JX181114 --- 郑建辉
     */
    public Integer getCountBySchoolId(Integer schoolId) {
        Example example = new Example(Teacher.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("tSchoolId", schoolId);

        return mapper.selectCountByExample(example);
    }

    /**
     * 根据姓名和性别来模糊查询教练记录
     *
     * @param name 教练姓名
     * @param sex  教练性别
     * @return 教练集合
     * @author JX181114 --- 郑建辉
     */
    public List<Teacher> getByNameAndSex(String name, String sex) {
        Example example = new Example(Teacher.class);
        Example.Criteria criteria = example.createCriteria();

        if (name != null && name.length() > 0) {
            criteria.andLike("tName", "%" + name + "%");
        }

        if (sex != null && sex.length() > 0) {
            criteria.andEqualTo("tSex", sex);
        }

        return mapper.selectByExample(example);

    }

    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     * @author JX181114 --- 郑建辉
     */
    @Override
    public Teacher getById(Integer id) {
        return (Teacher) mapper.selectByPrimaryKey(id);
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
    public Integer add(Teacher object) {
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
    public Integer updateById(Teacher object) {
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
