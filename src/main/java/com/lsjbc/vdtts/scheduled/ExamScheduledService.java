package com.lsjbc.vdtts.scheduled;

import com.lsjbc.vdtts.pojo.dto.QuestionBank;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: ExamSchduledService
 * @Description: 自动更新题库的定时任务
 * @Datetime: 2020/6/6   23:17
 * @Author: JX181114 - 郑建辉
 */
@Service
public class ExamScheduledService {

    @Resource(name = QuestionBank.NAME)
    private QuestionBank bank;

    /**
     * 从每个月的1号开始，每隔两天的凌晨3点15分会自动更新一次内存中的考卷
     *
     * @author JX181114 --- 郑建辉
     */
    @Async
    @Scheduled(cron = "0 30 3 1/2 * ?")
    public void autoUpdateQuestionBank() {
        bank.init();
    }


}
