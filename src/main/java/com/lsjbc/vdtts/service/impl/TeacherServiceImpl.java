package com.lsjbc.vdtts.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsjbc.vdtts.constant.EvaluateType;
import com.lsjbc.vdtts.dao.EvaluateDao;
import com.lsjbc.vdtts.dao.SchoolDao;
import com.lsjbc.vdtts.dao.StudentDao;
import com.lsjbc.vdtts.dao.TeacherDao;
import com.lsjbc.vdtts.dao.mapper.AccountMapper;
import com.lsjbc.vdtts.dao.mapper.StudentMapper;
import com.lsjbc.vdtts.dao.mapper.TeacherMapper;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.School;
import com.lsjbc.vdtts.entity.Student;
import com.lsjbc.vdtts.entity.Teacher;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.pojo.vo.TeacherDetail;
import com.lsjbc.vdtts.service.intf.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service(TeacherServiceImpl.NAME)
public class TeacherServiceImpl implements TeacherService {

    /**
     * Bean名
     */
    public static final String NAME = "TeacherService";


    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private StudentMapper studentMapper;

    @Resource(name = TeacherDao.NAME)
    private TeacherDao teacherDao;

    @Resource(name = EvaluateDao.NAME)
    private EvaluateDao evaluateDao;

    @Resource(name = SchoolDao.NAME)
    private SchoolDao schoolDao;

    @Resource(name = StudentDao.NAME)
    private StudentDao studentDao;

    @Override
    /*
     *@Description:查询各个驾校的教练基本信息
     *@Author:刘海
     *@Param:[start, pageSize, tSchoolId]
     *@return:com.lsjbc.vdtts.pojo.vo.LayuiTableData
     *@Date:2020/6/8 19:02
     **/
    public LayuiTableData findTeacherList(String page, String limit, String tName, HttpServletRequest request) {


        int pageSize = Integer.parseInt(limit);
        int start = (Integer.parseInt(page) - 1) * pageSize;//计算从数据库第几条开始查
        School school = (School) request.getSession().getAttribute("school");
        ArrayList<Teacher> teacherList = teacherMapper.findTeacherList(start, pageSize, tName, school.getSId());
        int teachCount = teacherMapper.findTeacherCount(tName, school.getSId());
        LayuiTableData LayuiTableData = new LayuiTableData();
        LayuiTableData.setCode(0);
        LayuiTableData.setMsg("查询成功");
        LayuiTableData.setCount(teachCount);
        LayuiTableData.setData(teacherList);
        return LayuiTableData;//返回前端所需要的数据类型
    }


    @Resource
    private AccountMapper accountMapper;
    @Override
    /*
     *@Description:
     *@Author:刘海
     *@Param:[teacher, teacherAccount]
     *@return:com.lsjbc.vdtts.pojo.vo.LayuiTableData
     *@Date:2020/6/7 22:55
     **/
    public LayuiTableData addTeacher(Teacher teacher, Account teacherAccount,HttpServletRequest request) {
        Account rerultAccount = accountMapper.findAccount(teacherAccount.getAAccount());
        School school = (School) request.getSession().getAttribute("school");
        LayuiTableData LayuiTableData = new LayuiTableData();
        teacher.setTSchoolId(school.getSId());
        if(null==rerultAccount){
            teacherAccount.setAType("teacher");
            int num = accountMapper.addAccount(teacherAccount);
            teacher.setTAccountId(teacherAccount.getAId());
            int num1 = teacherMapper.addTeacher(teacher);
            if(num>0&&num1>0){
                LayuiTableData.setCode(1);
            }else{
                LayuiTableData.setCode(2);
            }
        }else{
            LayuiTableData.setCode(0);
        }
        return LayuiTableData;
    }
    /*
     *@Description:
     *@Author:陈竑霖
     *@Param:
     *@return:
     *@Date:2020/6/8 1591587038161
     **/
    @Override
    public LayuiTableData teacherList(Teacher teacher, int page, int pageSize) {
        int start = (page - 1) * pageSize;//计算出起始查询位置
        if(start<0){
            start=0;
        }
        List<Teacher> list = teacherMapper.teacherlist(teacher, start, pageSize);
        int count = teacherMapper.teacherlistcount(teacher);

        LayuiTableData layuiTableData = new LayuiTableData();
        if (list.size() > 0) {
            layuiTableData.setCode(0);
            layuiTableData.setMsg("");
            layuiTableData.setCount(count);
            layuiTableData.setData(list);
            System.out.println(teacher);
        } else {
            layuiTableData.setCode(1);
            layuiTableData.setMsg("查询失败");
        }
        return layuiTableData;
    }


    /*
     *@Description:
     *@Author:刘海
     *@Param:[teacher]
     *@return:
     *@Date:2020/6/8 16:15
     **/

    @Override
    public LayuiTableData deleteTeacher(int tId, HttpServletRequest request) {
        LayuiTableData layuiTableData = new LayuiTableData();
        School school = (School) request .getSession().getAttribute("school");
        Teacher teacher = teacherMapper.findAccountId(tId);
        if(null!=teacher.getTAccountId()){
            int num = teacherMapper.deleteTeacher(tId);
            int num1 = accountMapper.deleteAccount(teacher.getTAccountId());
            int num2 = studentMapper.updateTeacherId(school.getSId(),teacher.getTId());
            if(num>0&&num1>0){
                layuiTableData.setCode(1);
            }else{
                layuiTableData.setCode(0);
            }
        }else{
            layuiTableData.setCode(2);
        }

        return layuiTableData;
    }


    /*
     *@Description:修改教练基本信息
     *@Author:刘海
     *@Param:[teacher]
     *@return:com.lsjbc.vdtts.pojo.vo.LayuiTableData
     *@Date:2020/6/9 15:26
     **/
    @Override
    public LayuiTableData updateTeacherInfo(Teacher teacher) {
        LayuiTableData layuiTableData = new LayuiTableData();
        int num = teacherMapper.updateTeacherInfo(teacher);
        if(num>0){
            layuiTableData.setCode(1);
        }
        return layuiTableData;
    }


    /*
     *@Description:
     *@Author:刘海
     *@Param:[tSchoolId]
     *@return:com.lsjbc.vdtts.pojo.vo.LayuiTableData
     *@Date:2020/6/9 15:27
     **/
    @Override
    public LayuiTableData findTeacher(HttpServletRequest request) {
        LayuiTableData layuiTableData = new LayuiTableData();
        School school = (School) request.getSession().getAttribute("school");
        ArrayList<Teacher> teacherList = teacherMapper.findTeacher(school.getSId());
        layuiTableData.setData(teacherList);
        return layuiTableData;
    }


    /*
     *@Description:
     *@Author:刘海
     *@Param:[cId]
     *@return:com.lsjbc.vdtts.pojo.vo.LayuiTableData
     *@Date:2020/6/9 21:39
     **/
    @Override
    public LayuiTableData updateTeacherApplyState(String tTeach,int tId) {
        LayuiTableData layuiTableData = new LayuiTableData();
        int num = teacherMapper.updateTeacherApplyState(tTeach,tId);
        if(num>0){
            layuiTableData.setCode(1);
        }else{
            layuiTableData.setCode(0);
        }
        return layuiTableData;
    }


    /*
     *@Description:
     *@Author:刘海
     *@Param:[tId]
     *@return:com.lsjbc.vdtts.pojo.vo.LayuiTableData
     *@Date:2020/6/9 22:25
     **/
    @Override
    public LayuiTableData updateTeacherAccountLockState(String tLock,int tId) {
        LayuiTableData layuiTableData = new LayuiTableData();
        int num = teacherMapper.updateTeacherAccountLockState(tLock,tId);
        if(num>0){
            layuiTableData.setCode(1);
        }else{
            layuiTableData.setCode(0);
        }
        return layuiTableData;
    }

    @Override
    public ResultData updateTeacherLimit(Teacher teacher) {
        ResultData resultData = null;
       int num = teacherMapper.updateByPrimaryKeySelective(teacher);
       if(num>0){
           resultData = ResultData.success(1,"修改本月限制人数成功");
       }
        return resultData;
    }

    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/11
     **/
    @Override
    public List<Teacher> selectAllInfo(Teacher teacher, int page, int limit) {
        List<Teacher> selectAllInfo = teacherMapper.selectAllInfo(teacher,page,limit);
        return selectAllInfo;
    }

    @Override
    public int selectTeacherCount(Teacher teacher) {
        int selectCount = teacherMapper.selectTeacherCount(teacher);
        return selectCount;
    }
    @Override
    public ResultData UpdatePhone(Teacher teacher){
        if(teacherMapper.teacherUpdate(teacher) > 0)
            return ResultData.success("修改成功！");
        else return ResultData.success("修改失败！");
    }

    @Override
    public ResultData HomePageShow(Teacher teacher, int page, int pageSize) {
        ResultData resultData = ResultData.success();
        resultData.put("teachers", teacherMapper.homePageShow(teacher, page, pageSize));
        return resultData;
    }

    /**
     * 根据姓名和性别来分页查询教练对象
     *
     * @param name 姓名
     * @param sex  性别
     * @param page 分页页数
     * @return 分页记录
     */
    @Override
    public Page<TeacherDetail> getTeacherDetailPageByNameAndSex(String name, String sex, Integer page) {

        Page<Teacher> pageInfo = getTeacherPageByNameAndSex(name, sex, page);

        Page<TeacherDetail> details = new Page<>();
        details.setTotal(pageInfo.getTotal());
        details.setPages(pageInfo.getPages());

        pageInfo.getResult().stream().forEach(item -> {
            TeacherDetail detail = TeacherDetail.generateDetail(item);
            detail.setScore(evaluateDao.getAvgByTypeAndId(EvaluateType.TYPE_TEACHER, item.getTId()));
            detail.setSchoolName(schoolDao.getById(item.getTSchoolId()).getSName());
            detail.setStudentCount(studentDao.getStudentCountByTeacherId(item.getTId()));

            details.getResult().add(detail);
        });

        return details;
    }

    /**
     * 根据姓名和性别来分页查询教练对象
     *
     * @param name 姓名
     * @param sex  性别
     * @param page 分页页数
     * @return 分页记录
     */
    @Override
    public Page<Teacher> getTeacherPageByNameAndSex(String name, String sex, Integer page) {
        Page<Teacher> pageInfo = PageHelper.startPage(page, 6, true);
        teacherDao.getByNameAndSex(name, sex);
        return pageInfo;
    }

    /**
     * 根据主键获取教练信息
     *
     * @param id 主键
     * @return 对象
     * @author JX181114 --- 郑建辉
     */
    @Override
    public Teacher zjhGetObjectByTeacherId(Integer id) {
        return teacherDao.getById(id);
    }

    //教练查询身份证
    @Override
//    public ResultData checksSfz(Student student, HttpServletRequest request)
//    {
//        System.out.println("setSSfz1=" + student);
//        ResultData resultData = null;
//        System.out.println("setSSfz=" + student);
//        Integer  tTeacherId = Integer.parseInt(request.getParameter("teacherId"));
//        student= studentMapper.insSfz(student);
//        System.out.println("setSSfz=" + student+"asdasd"+tTeacherId);
//        if (student != null)
//        { //查询
////
//            if (student.getSTeacherId() == null)
//            {
//                Teacher teacher=teacherMapper.fteacher(tTeacherId);
//                int num =studentMapper.updateStudentTecaherId(tTeacherId,teacher.getTSchoolId(),student);
//                resultData = ResultData.error(1, "报名教练成功");
//            } else
//            {
//                resultData = ResultData.error(2, "该学员已报名其他教练");
//            }
//        } else {
//                resultData = ResultData.error(3, "未该有此学员信息请先去注册");
//        }
//        return resultData;
//    }
    public ResultData checksSfz( HttpServletRequest request)
    {
        ResultData resultData=null;
        Integer  tTeacherId = Integer.parseInt(request.getParameter("teacherId"));
        Student student =(Student) request.getSession().getAttribute("student");
        if (student != null)
        { //查询
            if (student.getSTeacherId() == null)
            {
                Teacher teacher=teacherMapper.fteacher(tTeacherId);
                int num =studentMapper.updateStudentTecaherId(tTeacherId,teacher.getTSchoolId(),student);
                resultData = ResultData.error(1, "报名教练成功");
            } else
            {
                resultData = ResultData.error(2, "该学员已报名教练,不能再报名");
            }
        } else {
            resultData = ResultData.error(3, "未该有此学员信息请先去注册");
        }
        return resultData;
    }
}
