package com.lsjbc.vdtts.service.impl;

import com.lsjbc.vdtts.constant.ExamText;
import com.lsjbc.vdtts.dao.ExamAnswerDao;
import com.lsjbc.vdtts.dao.ExamQuestionDao;
import com.lsjbc.vdtts.entity.ExamQuestion;
import com.lsjbc.vdtts.service.intf.ExamTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName: ExamTestServiceImpl
 * @Description: 模拟考试的Service层
 * @Datetime: 2020/6/6   22:35
 * @Author: JX181114 - 郑建辉
 */
@Service(ExamTestServiceImpl.NAME)
public class ExamTestServiceImpl implements ExamTestService {

    /**
     * Bean名
     */
    public static final String NAME = "ExamTestService";

    @Resource(name = ExamQuestionDao.NAME)
    private ExamQuestionDao examQuestionDao;

    @Resource(name = ExamAnswerDao.NAME)
    private ExamAnswerDao examAnswerDao;

    /**
     * 生成特定科目的考卷
     * 注意
     * 这个操作非常耗时，能不用尽量不要使用
     *
     * @param level 科目等级，如果是科一，传入1，如果是科四，传入4
     * @return Map<考卷编号, 考题集合>
     * @author JX181114 --- 郑建辉
     */
    @Override
    public Map<Integer, List<ExamQuestion>> generateText(Integer level) {
        //先获取出特定科目所有的题目
        List<ExamQuestion> list = examQuestionDao.getAll(level);

        //生成考卷
        return list.size() > ExamText.NUMBER_OF_QUESTIONS_ON_A_TEST ? generateManyTest(list) : generateOneTest(list);
    }

    /**
     * 通过题库生成一张考卷
     *
     * @param list 考题集合
     * @return Map<考卷编号, 考题集合>
     * @author JX181114 --- 郑建辉
     */
    private Map<Integer, List<ExamQuestion>> generateOneTest(List<ExamQuestion> list) {

        list.forEach(item->{
            item.setAnswers(examAnswerDao.getByQuestionId(item.getEqId()));
        });

        Map<Integer, List<ExamQuestion>> map = new HashMap<>(1);
        map.put(0, list);
        return map;
    }

    /**
     * 通过题库生成多张考卷
     * 步骤
     * 为了不获取重复的题目，把集合转换为数组
     * 读取出数组长度，一张考卷一共100题，可以通过数组长度获取考卷的数量
     * 生成多张考卷，并不断的从题目数组中随机获取一题没有考过的题目存入
     * 注意
     * 当题目数量为整数百时，例如100，200，300，400时，所有的题目都会在考卷上
     * 当题目数量为零碎的数时，例如123，456，798时，会分别有23，56，98道题不会出现在考卷上
     *
     * @param list 考题集合
     * @return Map<考卷编号, 考题集合>
     * @author JX181114 --- 郑建辉
     */
    private Map<Integer, List<ExamQuestion>> generateManyTest(List<ExamQuestion> list) {
        //为了不获取重复的题目，把集合转换为数组
        ExamQuestion[] questions = list.toArray(new ExamQuestion[]{});

        //获取数组的长度
        Integer questionCount = questions.length;

        //算出能生成的考卷总数
        Integer examCount = questionCount / ExamText.NUMBER_OF_QUESTIONS_ON_A_TEST;

        //生成空考卷
        Map<Integer, List<ExamQuestion>> map = new HashMap<>(examCount);
        for (int i = 0; i < examCount; i++) {
            map.put(i, new ArrayList<>(ExamText.NUMBER_OF_QUESTIONS_ON_A_TEST));
        }

        //生成随机数获取对象
        Random random = new Random();

        //获取出每一套考卷
        for (List<ExamQuestion> value : map.values()) {

            /**
             * 考卷的内记录的题目不满一百题时并且存在剩余题目时进入循环
             * 如果questionCount=1时进入下一次循环，在random.nextInt(questionCount-1);
             * 步骤时会抛出异常，因为nextInt()中的值必须大于1
             * 当考卷内的题目达到上限时，或者只剩下最后一道题时，会结束循环
             */
            while (value.size() < ExamText.NUMBER_OF_QUESTIONS_ON_A_TEST && questionCount > 1) {

                //获取出随机的一个数组下标，并取出考题放入考卷
                Integer index = random.nextInt(questionCount - 1);
                ExamQuestion question = questions[index];
                value.add(question);

                //读取考题的答案，并存入考题中
                question.setAnswers(examAnswerDao.getByQuestionId(question.getEqId()));

                /**
                 * 当前数组长度内的所有元素都没有被使用过
                 * 获取到index地址的元素时，取出元素
                 * 把最后一个元素覆盖到index地址的
                 * 同时时长度减一，下次遍历就不会遍历到最后一个元素
                 * 数组内的元素始终都是未被使用过的
                 * 获取出最后一道考题，放入当前下标，填补考题空缺
                 */
                questions[index] = questions[questionCount - 1];

                //使数组的长度-1，过滤掉最后一条数据
                questionCount--;
            }

            /**
             * 如果走到了这一步，说明这张考卷上的题目满了，或者是只剩下最后一道题目没存入
             * 这里进行判断，如果考卷没满，并且拥有最后一道题，直接存入考卷中
             */
            if (questionCount == 1 && value.size() < 100) {
                value.add(questions[0]);
            }
        }

        return map;
    }
}
