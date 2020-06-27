package com.lsjbc.vdtts.dao.mapper;

import com.lsjbc.vdtts.entity.ExamTime;
import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface ExamTimeMapper extends CustomBaseMapper<ExamTime> {
    public ArrayList<ExamTime> findStudentExamNotes(@Param("etStudentId") Integer etStudentId,@Param("start") Integer start,@Param("pageSize") Integer pageSize);
    int findStudentExamNotesCount(Integer etStudentId);
}