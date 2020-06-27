package com.lsjbc.vdtts.service.intf;

import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.Student;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.pojo.vo.StudentRegister;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public interface StudentService {
    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/8
     **/
    public List<Student> selectAllInfo(@Param("student") Student student, @Param("page") int page, @Param("limit") int limit);

    public int selectStudentCount(@Param("student") Student student);

    public int resetPwd(String studentId);

    public int insertstudent(Student student);

    public int updatestudent(Student student);

    public int deletestudent(Student student);

    public Integer studentExamCount1();

    public Integer studentExamCount2();

    public Integer studentExamCount3();

    public Integer studentExamCount4();

    /*
     *@Description:
     *@Author:陈竑霖
     *@Param:
     *@return:
     *@Date:2020/6/8 1591600767377
     **/
    public LayuiTableData selectList(Student student, int page, int pageSize);

    public int registerStudent(Student student);

    ResultData updatestudentPwd(HttpServletRequest request);

    /*
     *@Description:
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/15 22:56
     **/
    LayuiTableData findStudenList(HttpServletRequest request, String page, String limit, String sName);

    ResultData updateStudentApplyState(Integer sId);

    ResultData updateStudentTeacherId(Integer sTeacherId, Integer sId);

    /**
     * 学员登录
     *
     * @param account 账号和密码对象
     * @param request request域
     * @return 结果集合
     */
    ResultData studentLogin(Account account, HttpServletRequest request);

    /**
     * 学员注册流程
     *
     * @param register 注册提供的信息对象
     * @param map      ModelAndView中的属性键值对
     * @param request  Request域
     * @return 跳转的路径
     * @author JX181114 --- 郑建辉
     */
    String studentRegister(StudentRegister register, Map<String, Object> map, HttpServletRequest request);

    /**
     * 学员修改手机号流程
     *
     * @param request Request域
     * @param phone 要修改的新手机号
     * @param code 验证码
     * @return 修改结果
     * @author JX181114 --- 郑建辉
     */
    ResultData studentUpdatePhone(HttpServletRequest request,String phone,String code);

    ResultData AddFace(String base64, int sId);//学生加脸  李浪

    ResultData FaceLogin(HttpServletRequest request, String base64);//刷脸登录 李浪
}
