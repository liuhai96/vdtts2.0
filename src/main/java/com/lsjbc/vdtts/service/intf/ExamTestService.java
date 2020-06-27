package com.lsjbc.vdtts.service.intf;

import com.lsjbc.vdtts.entity.ExamQuestion;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExamTestService
 * @Description: 模拟考试的Service层
 * @Datetime: 2020/6/6   22:33
 * @Author: JX181114 - 郑建辉
 */
public interface ExamTestService {

    /**
     * 生成特定科目的考卷
     * 注意
     * 这个操作非常耗时，能不用尽量不要使用
     *
     * @param level 科目等级，如果是科一，传入1，如果是科四，传入4
     * @return Map<考卷编号, 考题集合>
     * @author JX181114 --- 郑建辉
     */
    Map<Integer, List<ExamQuestion>> generateText(Integer level);

}
