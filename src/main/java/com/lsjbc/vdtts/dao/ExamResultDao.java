package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.ExamResultMapper;
import com.lsjbc.vdtts.entity.ExamResult;
import com.lsjbc.vdtts.pojo.vo.ExamTimeNew;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @ClassName: ExamResultDao
 * @Description:
 * @Datetime: 2020/6/14   22:23
 * @Author: JX181114 - 郑建辉
 */
@Repository(ExamResultDao.NAME)
public class ExamResultDao implements BaseDao<ExamResult> {

    /**
     * Bean名
     */
    public static final String NAME = "ExamResultDao";

    @Resource
    private ExamResultMapper mapper;

    /**
     * 检查各个科目是否允许继续学习
     *
     * @param studentId 学员ID
     * @param level     科目等级
     * @return 允许学习：true，不允许学习：false
     * @author JX181114 --- 郑建辉
     */
    public boolean allowLearn(Integer studentId, Integer level) {

        ExamResult result = getByStudentId(studentId);

        switch (level) {
            case 1:
                //当科目一的考试还没有通过时，允许学习
                if (result.getErState1() != 1) {
                    return true;
                }
                return false;
            case 2:
                //当科目一的考试通过，并且科目二的考试还没有结束时，允许学习
                if (result.getErState1() == 1 && result.getErState2() != 1) {
                    return true;
                }
                return false;
            case 3:
                //当科目二的考试通过，并且科目三的考试还没有结束时，允许学习
                if (result.getErState2() == 1 && result.getErState3() != 1) {
                    return true;
                }
                return false;
            case 4:
                //当科目三的考试通过，并且科目四的考试还没有结束时，允许学习
                if (result.getErState3() == 1 && result.getErState4() != 1) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    /**
     * 通过学员ID来更新学时
     *
     * @param object 学时对象
     * @return 受影响条数
     */
    public Integer updateTimeByStudentId(ExamTimeNew object) {

        if (object == null) {
            return 0;
        }

        if (object.getLevel() != 2 && object.getLevel() != 3) {
            return 0;
        }

        ExamResult result = getByStudentId(object.getStudentId());

        if (result == null) {
            return 0;
        }

        if (object.getLevel() == 2) {
            result.setErTime2(result.getErTime2() + object.getTime());
        }

        if (object.getLevel() == 3) {
            result.setErTime3(result.getErTime3() + object.getTime());
        }


        return mapper.updateByPrimaryKeySelective(result);
    }

    /**
     * 通过学员ID来获取考试结果
     *
     * @param studentId 学员ID
     * @return 考试结果
     * @author JX181114 --- 郑建辉
     */
    public ExamResult getByStudentId(Integer studentId) {
        Example example = new Example(ExamResult.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("erStudentId", studentId);
        return (ExamResult) mapper.selectOneByExample(example);
    }

    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     */
    @Override
    public ExamResult getById(Integer id) {
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
    public Integer add(ExamResult object) {
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
    public Integer updateById(ExamResult object) {
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
