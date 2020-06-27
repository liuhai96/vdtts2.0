package com.lsjbc.vdtts.controller;

import com.github.pagehelper.PageInfo;
import com.lsjbc.vdtts.pojo.dto.PageDTO;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.intf.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("examQuestionController")
public class ExamQuestionController {

    @Autowired
    ExamQuestionService examQuestionService;
    @RequestMapping(value = "/examQuestion")
    public ResultData examQuestion(String level) {
        return examQuestionService.insertExamQuestion(level);
    }

    @RequestMapping(value = "/findExamQuestion")
    public LayuiTableData findExamQuestion(PageDTO pageDTO){
        LayuiTableData layuiTableData = new LayuiTableData();
        PageInfo pageInfo = examQuestionService.findExamQuestion(pageDTO);
        layuiTableData.setCount(Integer.parseInt(String.valueOf(pageInfo.getTotal())));
        layuiTableData.setData(pageInfo.getList());
        return layuiTableData;
    }

    @RequestMapping(value = "/findAnswer")
    public ResultData findAnswer(String questionId){

        return  examQuestionService.findAnswer(questionId);
    }
}
