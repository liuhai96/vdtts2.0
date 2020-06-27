package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.constant.CustomTime;
import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.ExamErrorMapper;
import com.lsjbc.vdtts.entity.ExamError;
import com.lsjbc.vdtts.entity.ExamQuestion;
import com.lsjbc.vdtts.service.BaseRedisClient;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ExamErrorDao
 * @Description:
 * @Datetime: 2020/6/9   1:10
 * @Author: JX181114 - 郑建辉
 */
@Repository(ExamErrorDao.NAME)
public class ExamErrorDao extends BaseRedisClient implements BaseDao<ExamError> {

    public static final String NAME = "ExamErrorDao";

    @Resource
    private ExamErrorMapper mapper;

    /**
     * 根据等级删除来删除错题记录
     *
     * @param level 科目等级
     * @return 受影响条数
     * @author JX181114 --- 郑建辉
     */
    public Integer deleteByLevel(Integer level) {
        Example example = new Example(ExamError.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("eeLevel", level);
        int row = mapper.deleteByExample(example);

        if(row>0){
            del("exam:error:course"+level);
        }

        return row;
    }

    /**
     * 根据学员来查询错题
     *
     * @param studentId 学员ID
     * @return 错题集合
     * @author JX181114 --- 郑建辉
     */
    public List<ExamError> getByStudentId(Integer studentId, Integer level) {

        List<ExamError> result =  getFromRedisByStudentAndLevel(studentId, level);

        if(result!=null&&result.size()>0){
            return result;
        }

        Example example = new Example(ExamError.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("eeStudentId", studentId);
        criteria.andEqualTo("eeLevel", level);

        result = mapper.selectByExample(example);

        result.forEach(item->setToRedisByIdAndLevel(item));

        return result;
    }

    /**
     * 批量插入
     *
     * @param list 要插入的记录集合
     * @return 受影响条数
     * @author JX181114 --- 郑建辉
     */
    public Integer add(List<ExamError> list) {

        if (list == null || list.size() == 0) {
            return 0;
        }

        Integer studentId = list.get(0).getEeStudentId();
        Integer level = list.get(0).getEeLevel();
        List<ExamError> oldErrorList = getByStudentId(studentId,level);

        list.forEach(item->add(item));

        return list.size();
    }

    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     */
    @Override
    public ExamError getById(Integer id) {
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
    public Integer add(ExamError object) {

        Integer row = mapper.insertSelective(object);

        if(row>=1) {
            setToRedisByIdAndLevel(object);
        }
        return row;
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
    public Integer updateById(ExamError object) {
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
     * 根据主键和科目来删除记录
     * @param id 主键
     * @param level 科目
     * @return 受影响条数
     * @author JX181114 --- 郑建辉
     */
    public Integer deleteByIdAndLevel(Integer id,Integer level) {
        Integer row = mapper.deleteByPrimaryKey(id);

        if(row>0){
            ExamError error = getFromRedisByLevelAndId(id,level);

            del("exam:error:course"+level+":id:"+id);

            lRemove("exam:error:course"+level+":student:"+error.getEeStudentId(),1,id);
        }

        return row;
    }


    /**
     * Redis的默认超时时间
     * 测试时使用30天
     * 上线时使用1小时或10分钟
     */
    private Long defaultRedisSaveTime = CustomTime.SECOND_OF_30_DAY;

    /**
     * 把单个对象以主键和科目等级为索引推入redis中
     *
     * @param object 对象，注意，这个对象主键属性不得为空
     * @return 结果，成功：true，失败：false
     * @author JX181114 --- 郑建辉
     */
    private boolean setToRedisByIdAndLevel(ExamError object) {
        try {
            hset("exam:error:course"+object.getEeLevel()+":id:"+object.getEeId(),"id",object.getEeId(),defaultRedisSaveTime);
            hset("exam:error:course"+object.getEeLevel()+":id:"+object.getEeId(),"questionId",object.getEeQuestionId(),defaultRedisSaveTime);
            hset("exam:error:course"+object.getEeLevel()+":id:"+object.getEeId(),"level",object.getEeLevel(),defaultRedisSaveTime);
            hset("exam:error:course"+object.getEeLevel()+":id:"+object.getEeId(),"studentId",object.getEeStudentId(),defaultRedisSaveTime);
            lSet("exam:error:course"+object.getEeLevel()+":student:"+object.getEeStudentId(),object.getEeId(),defaultRedisSaveTime);
        } catch (Exception e) {
            return false;
        }
        return true;
    }



    /**
     * 从redis中根据主键和科目等级读取单个对象
     *
     * @param id 主键
     * @return 对象，如果找不到会返回null
     * @author JX181114 --- 郑建辉
     */
    private ExamError getFromRedisByLevelAndId(Integer id,Integer level) {
        ExamError result = null;
        try {
            //读取数据
            Integer resQuestionId = (Integer) hget("exam:error:course"+level+":id:"+id,"questionId");
            Integer resStudentId = (Integer) hget("exam:error:course"+level+":id:"+id,"studentId");

            //装填数据并实例化对象
            result = ExamError.builder().eeId(id).eeLevel(level).eeQuestionId(resQuestionId).eeStudentId(resStudentId).build();
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 从redis中根据学员ID和科目等级来查询出错题记录
     * @param studentId
     * @param level
     * @return
     */
    private List<ExamError> getFromRedisByStudentAndLevel(Integer studentId,Integer level){

        List<ExamError> result = new ArrayList<>();

        try {
            List<Object> source = lGet("exam:error:course"+level+":student:"+studentId, 0, -1);
            source.forEach(item -> {
                Integer id = (Integer) item;

                ExamError element = getFromRedisByLevelAndId(id,level);

                if (element != null) {
                    result.add(element);
                }
            });
        } catch (Exception e) {
            return null;
        }

        return result;
    }
}
