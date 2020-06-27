package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.StudentMapper;
import com.lsjbc.vdtts.entity.Student;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @ClassName: StudentDao
 * @Description:
 * @Datetime: 2020/6/12   14:24
 * @Author: JX181114 - 郑建辉
 */
@Repository(StudentDao.NAME)
public class StudentDao implements BaseDao<Student> {

    /**
     * Bean名
     */
    public static final String NAME = "StudentDao";

    @Resource
    private StudentMapper mapper;

    /**
     * 根据账号ID来获取学员信息
     *
     * @param accountId 账号Id
     * @return 学员信息
     * @author JX181114 --- 郑建辉
     */
    public Student getStudentByAccountId(Integer accountId) {
        return (Student) mapper.selectOne(Student.builder().sAccountId(accountId).build());
    }

    /**
     * 根据驾校ID来拉取学员数量
     *
     * @param schoolId 驾校ID
     * @return 学员数量
     * @author JX181114 --- 郑建辉
     */
    public Integer getStudentCountBySchoolId(Integer schoolId) {

        Example example = new Example(Student.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("sSchoolId", schoolId);

        return mapper.selectCountByExample(example);
    }

    /**
     * 根据教练ID来拉取学员数量
     *
     * @param teacherId 教练ID
     * @return 学员数量
     * @author JX181114 --- 郑建辉
     */
    public Integer getStudentCountByTeacherId(Integer teacherId) {
        Example example = new Example(Student.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("sTeacherId", teacherId);

        return mapper.selectCountByExample(example);
    }

    /**
     * 根据学员ID来找出对应的教练ID
     *
     * @param studentId 学员ID
     * @return 所属教练ID
     * @author JX181114 --- 郑建辉
     */
    public Integer getTeacherIdByStudentId(Integer studentId) {

        Student student = (Student) mapper.selectByPrimaryKey(studentId);

        return student.getSTeacherId();
    }

    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     */
    @Override
    public Student getById(Integer id) {
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
    public Integer add(Student object) {
        return mapper.insertSelective(object);
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
    public Integer updateById(Student object) {
        return mapper.updateByPrimaryKeySelective(object);
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
