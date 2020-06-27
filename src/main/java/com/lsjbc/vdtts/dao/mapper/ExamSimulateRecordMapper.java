package com.lsjbc.vdtts.dao.mapper;

import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;
import com.lsjbc.vdtts.entity.ExamSimulateRecord;

import java.util.List;

/**
 * @ClassName: ExamSimulateRecordMapper
 * @Description: 模拟考试答案表(单表操作Mapper)
 * @Datetime: 2020/6/8   9:50
 * @Author: JX181114 - 郑建辉
 */
public interface ExamSimulateRecordMapper extends CustomBaseMapper<ExamSimulateRecord> {
    List<ExamSimulateRecord> getExamSimulate(ExamSimulateRecord record);
}
