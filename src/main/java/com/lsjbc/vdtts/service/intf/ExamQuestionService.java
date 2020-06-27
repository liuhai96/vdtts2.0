package com.lsjbc.vdtts.service.intf;

import com.github.pagehelper.PageInfo;
import com.lsjbc.vdtts.pojo.dto.PageDTO;
import com.lsjbc.vdtts.pojo.vo.ResultData;

public interface ExamQuestionService {
    ResultData insertExamQuestion(String level);

    PageInfo findExamQuestion(PageDTO pageDTO);

    ResultData findAnswer(String questionId);
}
