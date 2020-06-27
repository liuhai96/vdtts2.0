package com.lsjbc.vdtts.dao.mapper;

import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.Teacher;
import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TeacherMapper extends CustomBaseMapper<Teacher> {
    public int findTeacherCount(@Param("tName") String tName,@Param("tSchoolId") Integer tSchoolId);
    public ArrayList<Teacher> findTeacherList(@Param("start") int start, @Param("pageSize") int pageSize, @Param("tName") String tName ,@Param("tSchoolId") Integer tSchoolId);
    public int addTeacher(Teacher teacher);

    public Teacher findAccountId(@Param("tId") int tId);

    public int deleteTeacher(@Param("tId") int tId);

    public int updateTeacherInfo(Teacher teacher);

    public ArrayList<Teacher> findTeacher(@Param("tSchoolId") int tSchoolId);

    public int updateTeacherApplyState(@Param("tTeach") String tTeach, @Param("tId")int tId);

    public int updateTeacherAccountLockState(@Param("tLock") String tLock, @Param("tId")int tId);

    /*
     *@Description:
     *@Author:陈竑霖
     *@Param:
     *@return:
     *@Date:2020/6/8 1591607995566
     **/
    public int teacherlistcount(@Param("e") Teacher teacher);
    public List<Teacher> teacherlist(@Param("e") Teacher teacher, @Param("start") int start, @Param("pageSize") int pageSize);
    public Teacher findAccount(@Param("e")Account account);//李浪 登录用的查找
    //教练归属驾校查找
    public Teacher fteacher(@Param("tTeacherId")Integer  tTeacherId);
    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/11
     **/
    public List<Teacher> selectAllInfo(@Param("teacher") Teacher teacher, @Param("page") int page, @Param("limit") int limit);
    public int selectTeacherCount(@Param("teacher") Teacher teacher);
    public int teacherUpdate(@Param("e")Teacher teacher);//教练修改统一方法

    List<Teacher> homePageShow(@Param("e")Teacher teacher,Integer page,Integer  pageSize);

}
