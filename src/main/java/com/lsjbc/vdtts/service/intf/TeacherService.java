package com.lsjbc.vdtts.service.intf;

import com.github.pagehelper.Page;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.Teacher;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.pojo.vo.TeacherDetail;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TeacherService {
    LayuiTableData findTeacherList(String page, String limit, String tName, HttpServletRequest request);

    LayuiTableData addTeacher(Teacher teacher, Account teacherAccount,HttpServletRequest request);

    LayuiTableData deleteTeacher(int tId, HttpServletRequest request);

    LayuiTableData updateTeacherInfo(Teacher teacher);

    LayuiTableData findTeacher(HttpServletRequest request);

    LayuiTableData updateTeacherApplyState(String tTeach,int tId);

    LayuiTableData updateTeacherAccountLockState(String tTeach, int tId);

    ResultData updateTeacherLimit(Teacher teacher);

    /*
     *@Description:
     *@Author:陈竑霖
     *@Param:
     *@return:
     *@Date:2020/6/8 1591607662706
     **/
    LayuiTableData teacherList(Teacher teacher, int page, int pageSize);
    //教练查询身份证
    public ResultData checksSfz (HttpServletRequest request);
    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/11
     **/
    List<Teacher> selectAllInfo(@Param("teacher") Teacher teacher, @Param("page") int page, @Param("limit") int limit);

    int selectTeacherCount(@Param("teacher") Teacher teacher);

    ResultData UpdatePhone(Teacher teacher);//修改联系方式

    //首页展示教练 --李浪
    ResultData HomePageShow(@Param("e") Teacher teacher, int page, int pageSize);

    /**
     * 根据姓名和性别来分页查询教练对象
     *
     * @param name 姓名
     * @param sex  性别
     * @param page 分页页数
     * @return 分页记录
     * @author JX181114 --- 郑建辉
     */
    Page<TeacherDetail> getTeacherDetailPageByNameAndSex(String name, String sex, Integer page);

    /**
     * 根据姓名和性别来分页查询教练对象
     *
     * @param name 姓名
     * @param sex  性别
     * @param page 分页页数
     * @return 分页记录
     * @author JX181114 --- 郑建辉
     */
    Page<Teacher> getTeacherPageByNameAndSex(String name, String sex, Integer page);

    /**
     * 根据主键获取教练信息
     *
     * @param id 主键
     * @return 对象
     * @author JX181114 --- 郑建辉
     */
    Teacher zjhGetObjectByTeacherId(Integer id);
}

