package com.lsjbc.vdtts.service.intf;

import com.lsjbc.vdtts.pojo.vo.ExamQuestionWithEeId;

import java.util.List;

/**
 * @ClassName: ExamErrorService
 * @Description: 模拟考试错题集的Service层
 * @Datetime: 2020/6/9   14:42
 * @Author: JX181114 - 郑建辉
 */
public interface ExamErrorService {

    /**
     * 根据学员ID，来查找错题集合
     *
     * @param level     科目等级
     * @param studentId 学员ID
     * @return 错题集合
     * @author JX181114 --- 郑建辉
     */
    List<ExamQuestionWithEeId> getErrorQuestionByStudentId(Integer level, Integer studentId);

    /**
     * 根据错题ID来删除记录
     *
     * @param id 错题ID
     * @param level 所属科目
     * @return 受影响条数
     * @author JX181114 --- 郑建辉
     */
    Integer deleteErrorQuestionByRecordId(Integer id,Integer level);

}
