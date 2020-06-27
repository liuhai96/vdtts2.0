package com.lsjbc.vdtts.dao.mapper;

import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.ExamParam;
import com.lsjbc.vdtts.entity.School;
import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 *@Description:
 *@Author:陈竑霖
 *@Param:
 *@return:
 *@Date:2020/6/8 1591600836022
 **/
public interface SchoolMapper extends CustomBaseMapper<School> {



    //修改审核状态
	public int updateschoolInfo(School school);
	public List<School> findschool(@Param("e") School school);
	//修改禁止招生
	public int punishcall(@Param("sId") int sId);
	//修改解禁招生
	public int unbindcall(@Param("sId") int sId);
	//修改禁止登录
	public int punishlogon(@Param("sId") int sId);
	//修改解禁登录
	public int unbindlogon(@Param("sId") int sId);

	/*
	 *@Description:
	 *@Author:刘海
	 *@Param:
	 *@return:
	 *@Date:2020/6/13 15:54
	 **/
	int updateSchoolBasicInfo(School school);

	School findSchoolInfo(Integer sId);

	int updateSchoolPwd(@Param("aId") int aId,@Param("aPassword") String aPassword);

	String findSchoolPwd(@Param("aId") Integer aId);
	//刘海


	/*
	 *@Description:
	 *@Author:李浪
	 *@Param:
	 *@return:
	 *@Date:2020/6/13 1592034621788
	 **/
	int addSchool(School school);
	School findAccount(Integer sAccountId);

	/*
	 *@Description:查学校表
	 *@Author:陈竑霖
	 *@Param:
	 *@return:
	 *@Date:2020/6/8 1591600836022
	 **/
	public int schoolcount(@Param("e") School school);
	public List<School> schoolList(@Param("e") School school, @Param("start") int start, @Param("pageSize") int pageSize);
	/*
	 *@Description:
	 *@Author:周永哲
	 *@Param:
	 *@return:
	 *@Date:2020/6/10
	 **/
	public List<School> selectAllInfo(@Param("school") School school, @Param("page") int page, @Param("limit") int limit);
	public int selectSchoolCount(@Param("school") School school);
	public int deleteSchool(String schoolId);
	public int insertSchool(School school);
	public int insertSchoolAccount(Account account);
	public int updateSchool(School school);
	public List<School> selectStudentCount();
	public List<ExamParam> selectParamInfo(@Param("examParam") ExamParam examParam, @Param("page") int page, @Param("limit") int limit);

}
