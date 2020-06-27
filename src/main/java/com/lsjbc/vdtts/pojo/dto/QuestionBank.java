package com.lsjbc.vdtts.pojo.dto;

import com.lsjbc.vdtts.entity.ExamQuestion;
import com.lsjbc.vdtts.service.impl.ExamTestServiceImpl;
import com.lsjbc.vdtts.service.intf.ExamTestService;
import lombok.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName: QuestionBank
 * @Description: 题库对象
 * @Datetime: 2020/6/6   23:35
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Component(QuestionBank.NAME)
public class QuestionBank {

    public static final String NAME = "QuestionBank";

    /**
     * 科目一题库
     */
    private Map<Integer, List<ExamQuestion>> bank1;

    /**
     * 科目四题库
     */
    private Map<Integer, List<ExamQuestion>> bank4;

    /**
     * 随机数
     */
    private Random ra = new Random();

    @Resource(name = ExamTestServiceImpl.NAME)
    private ExamTestService service;

    /**
     * 题库中的数据会非常庞大，如果用户点击题库时再去获取题库，很耗时间
     * 这里我们可以在项目启动时，把题库和答案遍历出来，保存在内存中
     * 后期设置一个定时任务，定时的去更新内存中的题库
     * 极其耗时，上线时才放开
     *
     * @author JX181114 --- 郑建辉
     */
    @Async
    @PostConstruct
    public void init() {
//        bank1 = service.generateText(1);
//        bank4 = service.generateText(4);
        System.out.println("正在更新内存中的题库");
        System.out.println("更新内存中的题库完成");
    }

    /**
     * 重置题库
     *
     * @param level 科目等级
     * @author JX181114 --- 郑建辉
     */
    public void resetBank(Integer level) {
        if (level == 1) {
            bank1 = service.generateText(1);
        } else {
            bank4 = service.generateText(4);
        }
    }

    /**
     * 判断是否拥有线程试卷
     *
     * @param level 科目等级
     * @return 有;true  没有:false
     */
    public boolean hasQuestion(Integer level) {
        List<ExamQuestion> questionList = getRandomQuestion(level);

        return questionList.size() > 0;
    }

    /**
     * 获取一张特定科目的考卷
     *
     * @param level 科目等级，如果时科一：1，如果时科四：4
     * @return 一张特定科目的考卷
     * @author JX181114 --- 郑建辉
     */
    public List<ExamQuestion> getRandomQuestion(Integer level) {
        switch (level) {
            case 1:
                return getRandomQuestion1();
            case 4:
                return getRandomQuestion4();
            default:
                return new ArrayList<ExamQuestion>(0);
        }
    }

    /**
     * 获取科一题目
     *
     * @return 一张考卷
     * @author JX181114 --- 郑建辉
     */
    private List<ExamQuestion> getRandomQuestion1() {
        if (bank1.size() == 0) {
            return new ArrayList<ExamQuestion>(0);
        }
        if (bank1.size() == 1) {
            return bank1.get(0);
        }
        Integer randomNumber = ra.nextInt(bank1.size());
        return bank1.get(randomNumber);
    }

    /**
     * 获取科四题目
     *
     * @return 一张考卷
     * @author JX181114 --- 郑建辉
     */
    private List<ExamQuestion> getRandomQuestion4() {
        if (bank4.size() == 0) {
            return new ArrayList<ExamQuestion>(0);
        }
        if (bank4.size() == 1) {
            return bank4.get(0);
        }
        Integer randomNumber = ra.nextInt(bank4.size());
        return bank4.get(randomNumber);
    }
}
