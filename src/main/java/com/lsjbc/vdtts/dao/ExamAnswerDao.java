package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.constant.CustomTime;
import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.ExamAnswerMapper;
import com.lsjbc.vdtts.entity.ExamAnswer;
import com.lsjbc.vdtts.service.BaseRedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: ExamAnswerDao
 * @Description: 题库答案表Dao层(单表操作)
 * @Datetime: 2020/6/6   18:37
 * @Author: JX181114 - 郑建辉
 */
@Repository(value = ExamAnswerDao.NAME)
@Slf4j
public class ExamAnswerDao extends BaseRedisClient implements BaseDao<ExamAnswer> {

    /**
     * Bean名
     */
    public static final String NAME = "ExamAnswerDao";

    @Resource
    private ExamAnswerMapper mapper;

    /**
     * 通过题目ID来获取对应的答案
     *
     * @param questionId 题目ID
     * @return 题目的所有答案
     * @author JX181114 --- 郑建辉
     */
    public List<ExamAnswer> getByQuestionId(Integer questionId) {

        if (questionId == null) {
            return new ArrayList<>(0);
        }

        //优先从redis中获取
        List<ExamAnswer> answers = getFromRedisByQuestionId(questionId);

        //如果获取到，并且集合中有数据，说明读取正常，直接返回
        if (answers != null && answers.size() > 0) {
            return answers;
        }

        //从数据库中查询数据
        Example example = new Example(ExamAnswer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("eaQuestionId", questionId);
        answers = mapper.selectByExample(example);

        //把数据库中的数据保存到redis中
        setToRedisByQuestionId(answers, questionId);

        return answers;
    }


    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     */
    @Override
    public ExamAnswer getById(Integer id) {
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
    public Integer add(ExamAnswer object) {
        return mapper.insertSelective(object);
    }

    public Integer addAll(List<ExamAnswer> answers){
        return mapper.insertList(answers);
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
    public Integer updateById(ExamAnswer object) {
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
     * 根据题目ID去获取redis中的数据
     *
     * @param questionId 题目ID
     * @return 正常会返回集合，如果出现未捕获的异常，返回null
     * @author JX181114 --- 郑建辉
     */
    private List<ExamAnswer> getFromRedisByQuestionId(Integer questionId) {
        List<ExamAnswer> result = new ArrayList<>();

        try {
            List<Object> source = lGet("exam:answer:questionId:" + questionId, 0, -1);

            source.forEach(item -> {
                Integer id = (Integer) item;

                ExamAnswer element = getFromRedisById(id);

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
     * 把多个对象以题目ID为索引推入redis中
     *
     * @param list       对象集合
     * @param questionId 题目ID
     * @return 失败个数，如果集合为空，会返回-1
     * @author JX181114 --- 郑建辉
     */
    private Integer setToRedisByQuestionId(List<ExamAnswer> list, Integer questionId) {

        if (list == null) {
            return -1;
        }

        //声明原子变量，做失败的计数器
        AtomicInteger failCount = new AtomicInteger();

        list.forEach(item -> {

            boolean success = setToRedisById(item);

            //如果失败了，计数器+1,并直接进入下一次循环
            if (!success) {
                failCount.incrementAndGet();
                return;
            }

            lSet("exam:answer:questionId:" + questionId, item.getEaId(), defaultRedisSaveTime);
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
    private boolean setToRedisById(ExamAnswer object) {
        try {
            hset("exam:answer:id:" + object.getEaId(), "questionId", object.getEaQuestionId(), defaultRedisSaveTime);
            hset("exam:answer:id:" + object.getEaId(), "answer", object.getEaAnswer(), defaultRedisSaveTime);
            hset("exam:answer:id:" + object.getEaId(), "right", object.getEaRight(), defaultRedisSaveTime);
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
    private ExamAnswer getFromRedisById(Integer id) {
        ExamAnswer result = null;
        try {
            //读取数据
            Integer questionId = (Integer) hget("exam:answer:id:" + id, "questionId");
            String answer = String.valueOf(hget("exam:answer:id:" + id, "answer"));
            String right = String.valueOf(hget("exam:answer:id:" + id, "right"));

            //装填数据并实例化对象
            result = ExamAnswer.builder().eaAnswer(answer).eaId(id).eaQuestionId(questionId).eaRight(right).build();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public void deleteAll(Integer level){
        Example example = new Example(ExamAnswer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("eaLevel",level);
        mapper.deleteByExample(example);
    }
}
