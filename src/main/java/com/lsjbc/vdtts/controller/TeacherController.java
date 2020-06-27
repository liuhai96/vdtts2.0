package com.lsjbc.vdtts.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lsjbc.vdtts.aop.Log;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.Student;
import com.lsjbc.vdtts.entity.Teacher;
import com.lsjbc.vdtts.enums.OperateType;
import com.lsjbc.vdtts.enums.ResourceType;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.intf.StudentService;
import com.lsjbc.vdtts.service.intf.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/teacherController")
public class TeacherController {

    /*
     *@Description：将查询的数据返回前端界面展示
     *@Author:刘海
     *@Param:[request, response]
     *@return:java.lang.String
     *@Date:2020/6/7 11:56
     **/

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/findTeacherList")
    @Log(operateType = OperateType.MODIFY, resourceType = ResourceType.Teacher)
    public String findTeacherList(String page, String limit, String tName, HttpServletRequest request) {
        LayuiTableData layuiTableData = teacherService.findTeacherList(page, limit, tName, request);
        return JSON.toJSONString(layuiTableData, SerializerFeature.DisableCircularReferenceDetect);
    }


    /*
     *@Description:添加教练基本信息
     *@Author:刘海
     *@Param:Teacher teacher  Account teacherAccount
     *@return:
     *@Date:2020/6/7 23:09
     **/
    @RequestMapping(value = "/addTeacher")
    @Log(operateType = OperateType.ADD, resourceType = ResourceType.Teacher)
    public String addTeacher(Teacher teacher, Account teacherAccount, HttpServletRequest request) {
        LayuiTableData LayuiTableData = teacherService.addTeacher(teacher, teacherAccount, request);
        return JSON.toJSONString(LayuiTableData);
    }


    /*
     *@Description:删除教练信息
     *@Author:刘海
     *@Param:[teacher]
     *@return:java.lang.String
     *@Date:2020/6/8 16:21
     **/
    @RequestMapping(value = "/deleteTeacher")
    @Log(operateType = OperateType.DELETE, resourceType = ResourceType.Teacher)
    public String deleteTeacher(String tId, HttpServletRequest request) {
        System.out.println("tId" + tId);
        LayuiTableData layuiTableData = teacherService.deleteTeacher(Integer.parseInt(tId), request);
        return JSON.toJSONString(layuiTableData);
    }


    /*
     *@Description:修改教练的基本信息
     *@Author:刘海
     *@Param:[teacher]
     *@return:java.lang.String
     *@Date:2020/6/8 21:49
     **/
    @RequestMapping(value = "/updateTeacherInfo")
    @Log(operateType = OperateType.MODIFY, resourceType = ResourceType.Teacher)
    public String updateTeacherInfo(Teacher teacher) {
        LayuiTableData layuiTableData = teacherService.updateTeacherInfo(teacher);
        return JSON.toJSONString(layuiTableData);
    }

    /*
     *@Description:修改教练本月毕业人数
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/21 20:37
     **/
    @RequestMapping(value = "/updateTeacherLimit")
    @Log(operateType = OperateType.MODIFY, resourceType = ResourceType.Teacher)
    public ResultData updateTeacherLimit(Teacher teacher) {
        return teacherService.updateTeacherLimit(teacher);
    }

    /*
     *@Description:
     *@Author:陈竑霖
     *@Param:
     *@return:
     *@Date:2020/6/8 1591607352442
     **/
    //教练表查看
    @RequestMapping(value = "/teacherList", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String teacherList(HttpServletRequest request, HttpServletResponse response, Teacher teacher) {
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String draw = request.getParameter("draw");//重绘次数 和前台对应

        LayuiTableData layuiTableData = teacherService.teacherList(teacher, Integer.parseInt(pageStr), Integer.parseInt(pageSizeStr));
        return JSON.toJSONString(layuiTableData);
    }

    @RequestMapping(value = "/findTeacher")
    public String findTeacher(HttpServletRequest request) {
        return JSON.toJSONString(teacherService.findTeacher(request));
    }

    @RequestMapping(value = "/updateTeacherApplyState")
    public Object updateTeacherApplyState(String tId, String tTeach) {
        return JSON.toJSONString(teacherService.updateTeacherApplyState(tTeach, Integer.parseInt(tId)));
    }

    @RequestMapping(value = "/updateTeacherAccountLockState")
    public Object updateTeacherAccountLockState(String tLock, String tId) {
        return JSON.toJSONString(teacherService.updateTeacherAccountLockState(tLock, Integer.parseInt(tId)));
    }

    @RequestMapping(value = "/stuTableData")//教练查找学生信息
    public String StuTabelData(int page, int limit, Student student, HttpServletRequest request) {
        LayuiTableData layuiTableData = studentService.selectList(student, page, limit);
        if (student.getSName() == null || student.getSName().equals(""))
            request.getSession().setAttribute("studentCount", layuiTableData.getCount());
        return JSON.toJSONString(layuiTableData);
    }


    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/10 15860799877
     **/
    @RequestMapping(value = "/selectTeacherInfo")//初始化教练信息表
    public String selectTeacherInfo(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit,
                                    Teacher teacher) {
        int page2 = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
        System.out.println(" ---carpage=" + page2);
        List<Teacher> list = teacherService.selectAllInfo(teacher, page2, Integer.valueOf(limit));
        int count = teacherService.selectTeacherCount(teacher);
        System.out.println("教练信息初始化操作--- list=" + list + " count =" + count);
        LayuiTableData layuiData = new LayuiTableData();
        layuiData.setCode(0);
        layuiData.setData(list);
        layuiData.setCount(count);
        return JSON.toJSONString(layuiData);
    }

    @RequestMapping(value = "updatePhone")
    /*
     *@Description:教练修改联系方式
     *@Author:李浪_191019
     *@Param:[teacher]
     *@return:java.lang.String
     *@Date:2020/6/13 16:28
     **/
    @ResponseBody
    public String UpdatePhone(Teacher teacher) {
        return JSON.toJSONString(teacherService.UpdatePhone(teacher));
    }

    @RequestMapping(value = "showTeacher")
    /*
     *@Description:首页教练展示
     *@Author:李浪_191019
     *@Param:[teacher, page, limit]
     *@return:java.lang.String
     *@Date:2020/6/15 1:48
     **/
    @ResponseBody
    public String ShowTeacher(Teacher teacher, @RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        return JSON.toJSONString(teacherService.HomePageShow(teacher, page, limit));
    }


    @RequestMapping(value = "/checksSfz")
    @ResponseBody
    public ResultData checksSfz(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        return teacherService.checksSfz(request);
    }
}
