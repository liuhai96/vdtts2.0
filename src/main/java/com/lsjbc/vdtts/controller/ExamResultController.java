package com.lsjbc.vdtts.controller;

import com.alibaba.fastjson.JSON;
import com.lsjbc.vdtts.aop.Log;
import com.lsjbc.vdtts.entity.Student;
import com.lsjbc.vdtts.enums.OperateType;
import com.lsjbc.vdtts.enums.ResourceType;
import com.lsjbc.vdtts.service.intf.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/examResultController")
public class ExamResultController {
    @Autowired
    private ExamResultService examResultService;
    /*
     *@Description:查询考试情况
     *@Author:刘海
     *@Param:[request, response]
     *@return:java.lang.Object
     *@Date:2020/6/10 23:59
     **/
    @RequestMapping(value = "/selectStudentExamList")
    @Log(operateType = OperateType.QUERY, resourceType = ResourceType.ExamResult)
    public Object selectStudentExamList(String page, String limit, String sName, String studentName, HttpServletRequest request){
        System.out.println("sName>>>>>"+sName);
        System.out.println("sName>>>>>"+studentName);
        return JSON.toJSONString(examResultService.selectStudentExamList(page,limit,sName,request));
    }

    /*
     *@Description:安排学员考试
     *@Author:刘海
     *@Param:[erId, sId, examSujectId, teacherId]
     *@return:java.lang.Object
     *@Date:2020/6/10 23:59
     **/
    @RequestMapping(value = "/arringeExam")
    @Log(operateType = OperateType.MODIFY, resourceType = ResourceType.ExamResult)
    public Object arringeExam(String erId,String sId,String examSujectId,String teacherId){

        return JSON.toJSONString(examResultService.arringeExam(Integer.parseInt(erId),Integer.parseInt(sId),Integer.parseInt(examSujectId),Integer.parseInt(teacherId)));
    }


    /*
     *@Description:录入学员成绩
     *@Author:刘海
     *@Param:[erId, sId, examSujectId, erScore]
     *@return:java.lang.Object
     *@Date:2020/6/11 11:12
     **/
    @RequestMapping(value = "/enterResults")
    @Log(operateType = OperateType.MODIFY, resourceType = ResourceType.ExamResult)
    public Object enterResults(String erId,String sId,String examSujectId,String erScore){
        return JSON.toJSONString(examResultService.enterResults(Integer.parseInt(erId),Integer.parseInt(sId),Integer.parseInt(examSujectId),Integer.parseInt(erScore)));
    }

    @RequestMapping(value = "/getStudentResult")
    /*
     *@Description:获取登录学生的成绩
     *@Author:李浪_191019
     *@Param:[student]
     *@return:org.springframework.web.servlet.ModelAndView
     *@Date:2020/6/18 22:04
     **/
    public ModelAndView getStudentResult(Student student,String logo,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data",examResultService.StudentRecordDate(student));
        if(logo.equals("result"))
            modelAndView.setViewName("/pages/student/student-record");
        else if(logo.equals("period")){
            modelAndView.setViewName("/pages/student/student-period");
        }
        return modelAndView;
    }
}
