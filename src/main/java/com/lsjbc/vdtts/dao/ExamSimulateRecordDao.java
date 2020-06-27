package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.ExamSimulateRecordMapper;
import com.lsjbc.vdtts.entity.ExamSimulateRecord;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: ExamSimulateRecordDao
 * @Description: 模拟考试记录表(单表操作)
 * @Datetime: 2020/6/8   9:52
 * @Author: JX181114 - 郑建辉
 */
@Repository(ExamSimulateRecordDao.NAME)
public class ExamSimulateRecordDao implements BaseDao<ExamSimulateRecord> {

    /**
     * Bean名
     */
    public static final String NAME = "ExamSimulateRecordDao";

    @Resource
    private ExamSimulateRecordMapper mapper;

    /**
     * 根据学员的ID和科目等级来获取模拟考试记录
     *
     * @param studentId 学员ID
     * @param level 科目ID
     * @return 模拟考试记录
     * @author JX181114 --- 郑建辉
     */
    public List<ExamSimulateRecord> getByStudentId(Integer studentId,Integer level){

        Example example = new Example(ExamSimulateRecord.class);
        example.orderBy("esrId").desc();

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("esrStudentId",studentId);
        if(level!=-1){
            criteria.andEqualTo("esrLevel",level);
        }

        return mapper.selectByExample(example);
    }

    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     */
    @Override
    public ExamSimulateRecord getById(Integer id) {
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
     * @author JX181114 --- 郑建辉
     */
    @Override
    public Integer add(ExamSimulateRecord object) {
        //插入时，会把对象中所有非空的值插入
        return mapper.insert(object);
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
    public Integer updateById(ExamSimulateRecord object) {
        return null;
    }

    /**
     * 根据主键来删除记录
     *
     * @param id 主键
     * @return 受影响条数
     * @author JX181114 --- 郑建辉
     */
    @Override
    public Integer deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }
}
