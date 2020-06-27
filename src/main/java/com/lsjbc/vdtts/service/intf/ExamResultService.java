package com.lsjbc.vdtts.service.intf;

import com.lsjbc.vdtts.entity.Student;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;

import javax.servlet.http.HttpServletRequest;

public interface ExamResultService {
    /*
     *@Description:
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/15 11:20
     **/
    public LayuiTableData selectStudentExamList(String page, String limit, String sName, HttpServletRequest request);
    public ResultData arringeExam(int erId,int erStudentId,int examSujectId,int teacherId);
    public ResultData enterResults(int erId,int studentId,int examSujectId,int erScore);
    public ResultData StudentRecordDate(Student student);//学员成数据  李浪
}
