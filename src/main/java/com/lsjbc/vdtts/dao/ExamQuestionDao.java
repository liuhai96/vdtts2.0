package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.constant.CustomTime;
import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.ExamQuestionMapper;
import com.lsjbc.vdtts.entity.ExamQuestion;
import com.lsjbc.vdtts.service.BaseRedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: ExamQuestionDao
 * @Description: 题库表Dao层(单表操作)
 * @Datetime: 2020/6/6   16:48
 * @Author: JX181114 - 郑建辉
 */
@Repository(value = ExamQuestionDao.NAME)
@Slf4j
public class ExamQuestionDao extends BaseRedisClient implements BaseDao<ExamQuestion> {

    /**
     * Bean名
     */
    public static final String NAME = "ExamQuestionDao";

    @Resource
    private ExamQuestionMapper mapper;

    /**
     * 从数据库中读取科一/科四的所有题目
     * 注意
     * 这个操作非常耗时，能不用尽量不要使用
     *
     * @param level 科目等级，如果是科一，传入1，如果是科四，传入4
     * @return 某一个科目的所有题目
     * @author JX181114 --- 郑建辉
     */
    public List<ExamQuestion> getAll(Integer level) {

        if(level==null){
            return new ArrayList<>(0);
        }

        //优先从redis中获取
        List<ExamQuestion> all = getAllFromRedis(level);

        //如果获取到，并且集合中有数据，说明读取正常，直接返回
        if(all!=null&&all.size()>0){
            return all;
        }

        //从数据库中查询数据
        Example example = new Example(ExamQuestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("eqLevel",level);
        all = mapper.selectByExample(example);

        //把数据库中的数据保存到redis中
        setAllToRedis(all,level);

        return all;
    }

    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     * @author JX181114 --- 郑建辉
     */
    @Override
    public ExamQuestion getById(Integer id) {

        ExamQuestion object = getFromRedisById(id);

        if(object!=null){
            return object;
        }

        object = (ExamQuestion) mapper.selectByPrimaryKey(id);

        setToRedisById(object);

        return object;
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
    public Integer add(ExamQuestion object) {
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
    public Integer updateById(ExamQuestion object) {
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
     * Redis的默认超时时间
     * 测试时使用30天
     * 上线时使用1小时或10分钟
     */
    private Long defaultRedisSaveTime = CustomTime.SECOND_OF_30_DAY;

    /**
     * 读取redis中所有的数据
     *
     * @param level 科目等级，如果是科目一,传入1  如果是科目四，传入4
     * @return 正常会返回集合，如果出现未捕获的异常，返回null
     * @author JX181114 --- 郑建辉
     */
    private List<ExamQuestion> getAllFromRedis(Integer level) {
        List<ExamQuestion> result = new ArrayList<>();

        try {
            List<Object> source = lGet("exam:question:course"+level+":all", 0, -1);
            source.forEach(item -> {
                Integer id = (Integer) item;

                ExamQuestion element = getFromRedisById(id);

                if (element != null) {
                    result.add(element);
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

        return result;
    }

    /**
     * 把数据库中的所有数据写入redis中
     *
     * @param list 对象集合
     * @level list 对象集合
     * @return 如果集合为空返回-1
     * @author JX181114 --- 郑建辉
     */
    private Integer setAllToRedis(List<ExamQuestion> list,Integer level) {
        if (list == null) {
            return -1;
        }

        try {
            list.forEach(item -> {
                lSet("exam:question:course"+level+":all", item.getEqId(), defaultRedisSaveTime);
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return setToRedisById(list);
    }

    /**
     * 把多个对象以主键为索引推入redis中
     *
     * @param list 对象集合
     * @return 失败个数，如果集合为空，会返回-1
     * @author JX181114 --- 郑建辉
     */
    private Integer setToRedisById(List<ExamQuestion> list) {

        if (list == null) {
            return -1;
        }

        //声明原子变量，做失败的计数器
        AtomicInteger failCount = new AtomicInteger();

        list.forEach(item -> {
            boolean success = setToRedisById(item);

            //如果失败了，计数器+1
            if (!success) {
                failCount.incrementAndGet();
            }
        });

        return failCount.get();
    }

    /**
     * 把单个对象以主键为索引推入redis中
     *
     * @param object 对象，注意，这个对象主键属性不得为空
     * @return 结果，成功：true，失败：false
     * @author JX181114 --- 郑建辉
     */
    private boolean setToRedisById(ExamQuestion object) {
        try {
            hset("exam:question:id:" + object.getEqId(), "question", object.getEqQuestion(), defaultRedisSaveTime);
            hset("exam:question:id:" + object.getEqId(), "pic", object.getEqPic(), defaultRedisSaveTime);
            hset("exam:question:id:" + object.getEqId(), "level", object.getEqLevel(), defaultRedisSaveTime);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 从redis中根据主键读取单个对象
     *
     * @param id 主键
     * @return 对象，如果找不到会返回null
     * @author JX181114 --- 郑建辉
     */
    private ExamQuestion getFromRedisById(Integer id) {
        ExamQuestion result = null;
        try {
            //读取数据
            String question = String.valueOf(hget("exam:question:id:" + id, "question"));
            String pic = String.valueOf(hget("exam:question:id:" + id, "pic"));
            Integer level = (Integer) hget("exam:question:id:" + id, "level");

            //装填数据并实例化对象
            result = ExamQuestion.builder().eqId(id).eqQuestion(question).eqPic(pic).eqLevel(level).build();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public void deleteAll(Integer level){
        del("exam:question:course"+level+":all");
        Example example = new Example(ExamQuestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("eqLevel",level);
        mapper.deleteByExample(example);
    }


    public Integer addAll(List<ExamQuestion> examQuestiones){
        return mapper.insertList(examQuestiones);
    }
}
