package com.lsjbc.vdtts.controller;

import com.alibaba.fastjson.JSON;
import com.lsjbc.vdtts.aop.Log;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.Student;
import com.lsjbc.vdtts.enums.OperateType;
import com.lsjbc.vdtts.enums.ResourceType;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.intf.AccountService;
import com.lsjbc.vdtts.service.intf.StudentService;
import com.lsjbc.vdtts.utils.Tool;
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
@RequestMapping("/studentController")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @Autowired
    private AccountService accountService;
    private  Student student = new Student();

    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/8 15860799877
     **/
    @RequestMapping(value = "/selectStudentInfo")//初始化学员信息表
    public Object selectadmininfo(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "page") String page ,@RequestParam(value = "limit") String limit,
                                  Student student) {
        int page2 = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
        System.out.println(" ---page2="+page2);
        List<Student> list= studentService.selectAllInfo(student,page2,Integer.valueOf(limit));
        int count =studentService.selectStudentCount(student);
        System.out.println("学员信息初始化操作--- list="+list+" count ="+count);
        LayuiTableData layuiData = new LayuiTableData();
        layuiData.setCode(0);
        layuiData.setData(list);
        layuiData.setCount(count);
        return JSON.toJSONString(layuiData);
    }

    @RequestMapping(value = "/resetPwd")//重置密码
    public String resetPwd(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "accountId") String accountId ) {
        System.out.println(" accountId:"+accountId);
        int i=studentService.resetPwd(accountId);
        String res = "";
        if(i>0){
            res="success";
            System.out.println("学员重置密码成功");
            return res;
        }else {
            res="failed";
            System.out.println("学员重置密码失败");
            return res;
        }
    }

    @RequestMapping(value = "/insertstudent")//添加学员
    public String insertadmin(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "account") String account ,@RequestParam(value = "pwd") String pwd ,
                              @RequestParam(value = "sex") String sex ,@RequestParam(value = "role") String role) {
        System.out.println("添加操作--- account:"+account+" pwd:"+pwd+" sex:"+sex+" role:" +role);
        student.setSchoolName(account);
        int i=studentService.insertstudent(student);
        String res = "";
        if(i>0){
            res="success";
            System.out.println("学员添加成功");
            return res;
        }else {
            res="failed";
            System.out.println("学员添加失败");
            return res;
        }
    }
//学员修改密码
    @RequestMapping(value = "/updatestudentPwd")
    @ResponseBody
    private ResultData updatestudentPwd(HttpServletRequest request){
        return studentService.updatestudentPwd(request);
    }
    @RequestMapping(value = "/updatestudent")//修改学员
    public String updateadmin(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "account") String account,@RequestParam(value = "pwd") String pwd ,
                              @RequestParam(value = "studentId") String studentId ) {

        System.out.println("修改操作--- account:"+account+" pwd:"+pwd+" studentId:"+studentId);
        student.setSAccountId(Integer.valueOf(account));
        student.setSId(Integer.valueOf(studentId));
//        student.set
        int i=studentService.updatestudent(student);
        String res = "";
        if(i>0){
            res="success";
            System.out.println("学员修改成功");
            return res;
        }else {
            res="failed";
            System.out.println("学员修改失败");
            return res;
        }
    }


    @RequestMapping(value = "/deletestudent")//删除学员
    public String deleteadmin(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "studentId") String studentId ) {
        System.out.println(" studentId:"+studentId);
        student.setSId(Integer.valueOf(studentId));
        int i=studentService.deletestudent(student);
        String res = "";
        if(i>0){
            res="success";
            System.out.println("学员删除成功");
            return res;
        }else {
            res="failed";
            System.out.println("学员删除失败");
            return res;
        }
    }

    @RequestMapping(value = "/studentExamCount")//查询各科考试人数
    public Student studentExamCount(HttpServletRequest request, HttpServletResponse response){
        Student student =Student.builder()
                        .state1(studentService.studentExamCount1())
                        .state2(studentService.studentExamCount2())
                        .state3(studentService.studentExamCount3())
                        .state4(studentService.studentExamCount4())
                        .build();
        System.err.println(student);
        return student;
    }


    @RequestMapping(value = "/studentRegister")
    @ResponseBody
    /*
     *@Description:
     *@Author:李浪_191019
     *@Param:[request, response, student, account] 学员首页注册
     *@return:java.lang.String
     *@Date:2020/6/8 22:19
     **/
    public String StudentRegister(HttpServletRequest request, HttpServletResponse response
            , Student student, Account account) {
        Tool tool = new Tool();
        LayuiTableData layuiData = new LayuiTableData();
        student.setSRegTime(tool.getDate("yyyy/MM/dd"));
        String sBirthday = student.getSSfz();
        int sex = Integer.valueOf(sBirthday.charAt(sBirthday.length()-1)+"");
        if(sex%2 == 0){
            if((sBirthday.length() == 15 && student.getSSex().equals("男")) ||
                    (sBirthday.length() == 18 && student.getSSex().equals("女")));
            else {
                layuiData.setMsg("非法证件!性别非法");
                return JSON.toJSONString(layuiData);
            }
        } else {
            if((sBirthday.length() == 15 && student.getSSex().equals("女")) ||
                    (sBirthday.length() == 18 && student.getSSex().equals("男")));
            else {
                layuiData.setMsg("非法证件!性别非法");
                return JSON.toJSONString(layuiData);
            }
        }

        int yob = Integer.valueOf(sBirthday.substring(6, 10));//出生年
        int thisYear = Integer.valueOf(tool.getDate("yyyy"));//今年
        yob = thisYear-yob;
        if(yob < 0 || yob > 110){ layuiData.setMsg("非法证件!出生日期");return JSON.toJSONString(layuiData); }//证件无效
        else if(yob < 18 || yob > 70){ layuiData.setMsg("证件未在法律年内!!");return JSON.toJSONString(layuiData); }//证件满足要求
        student.setSBirthday(yob+"/"+sBirthday.substring(10, 12)+"/"+sBirthday.substring(12, 14));
        for(int i = 0;i < 3;i++){
            String aAccount = tool.getRandCode(tool.getRandom(6,11),null);
            if(accountService.accountRepetition(aAccount) == null){
                account.setAPassword(tool.createMd5(account.getAPassword()));//转MD5码
                account.setAAccount(aAccount);
                account.setAType("student");
                if(accountService.addStudentAccount(account) > 0){
                    student.setSAccountId(account.getAId());
                    if(studentService.registerStudent(student) > 0)
                        layuiData.setMsg("      提 交 成 功!\n您的登录账号为："+aAccount);
                    else layuiData.setMsg("未知原因错误！!");
                }
                else layuiData.setMsg("未知原因错误！!");
                break;
            }
        }
        System.out.println(JSON.toJSONString(student));
        return JSON.toJSONString(layuiData);
    }

    /*
     *@Description:查询驾校内的学员
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/15 23:12
     **/
    @RequestMapping(value = "/findStudentList")
    @Log(operateType = OperateType.QUERY, resourceType = ResourceType.Student)
    public String findStudentList(HttpServletRequest request,String page,String limit,String sName){
        return JSON.toJSONString(studentService.findStudenList(request,page,limit,sName));
    }

    /*
     *@Description:修改学员的报名状态
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/16 1:31
     **/
    @RequestMapping(value = "/updateStudentApplyState")
    @Log(operateType = OperateType.MODIFY, resourceType = ResourceType.Student)
    public ResultData updateStudentApplyState(String sId){
        return studentService.updateStudentApplyState(Integer.parseInt(sId));
    }

    /*
     *@Description:修改教练Id
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/16 1:31
     **/
    @RequestMapping(value = "/updateStudentTeacherId")
    @Log(operateType = OperateType.MODIFY, resourceType = ResourceType.Student)
    public ResultData updateStudentTeacherId(String sTeacherId,String sId){
        return studentService.updateStudentTeacherId(Integer.parseInt(sTeacherId),Integer.parseInt(sId));
    }

    @RequestMapping(value = "/studentTransfer")
    public ModelAndView Transfer(String logo){
        ModelAndView modelAndView = new ModelAndView();
        String nextJsp;
        switch (logo){
            case "face" ://人脸登录中转
                nextJsp = "/pages/student/human-face";
            break;
            case"addFace"://人脸录入中转
                nextJsp = "/pages/student/add-student-face";
                break;
            default :
                nextJsp = "";
                break;
        }
        modelAndView.setViewName(nextJsp);
        return modelAndView;
    }
}
