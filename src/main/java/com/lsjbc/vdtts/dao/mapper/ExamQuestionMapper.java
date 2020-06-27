package com.lsjbc.vdtts.dao.mapper;

import com.lsjbc.vdtts.entity.ExamQuestion;
import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: ExamQuestionMapper
 * @Description:
 * @Datetime: 2020/6/6   14:53
 * @Author: JX181114 - 郑建辉
 */

public interface ExamQuestionMapper extends CustomBaseMapper<ExamQuestion> {
   int insertExamQuestion(@Param("examQuestionList") List examQuestionList);

}
