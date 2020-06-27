package com.lsjbc.vdtts.service.intf;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.ExamParam;
import com.lsjbc.vdtts.entity.School;
import com.lsjbc.vdtts.pojo.dto.PageDTO;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.PowerSchool;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.pojo.vo.SchoolDetail;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 *@Description:
 *@Author:陈竑霖
 *@Param:
 *@return:
 *@Date:2020/6/8 1591601046266
 **/
public interface SchoolService {
    /*
     *@Description:查表
     *@Author:陈竑霖
     *@Param:
     *@return:
     *@Date:2020/6/13 1592020689329
     **/
    LayuiTableData schoolList(School school, int page, int pageSize);
    //修改审核状态
    public LayuiTableData findschool(School school);
    public LayuiTableData updateschoolInfo(School school);
    //修改处罚招生
    public LayuiTableData punishcall(int sId);
    //修改解禁招生
    public LayuiTableData unbindcall(int sId);
	//修改处罚登录
	public LayuiTableData punishlogon(int sId);
	//修改解禁登录
	public LayuiTableData unbindlogon(int sId);
    //查询身份证
    public ResultData insSfz( HttpServletRequest request);
    //根据主键获取驾校信息
    School getSchoolBySchoolId(Integer id);



    int schoolCount(School school);//李浪写  查找数据条数

    List<School> schoolMessageList(School school, int stripStart, int stripEnd);//李浪写

    ResultData schoolToProduct(School school, String id);//学校进驻平台 李浪写

    ResultData findSchoolInfo(HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据驾校的的名字，来分页查询数据
     *
     * @param name 驾校名称
     * @param page 页数
     * @return 分页对象
     */
    Page<School> getSchoolPageByName(String name, Integer page);

    /**
     * 根据驾校的的名字，来分页查询数据
     *
     * @param name 驾校名称
     * @param page 页数
     * @return 分页对象
     */
    Page<SchoolDetail> getSchoolDetailPageByName(String name, Integer page);

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





    ResultData updateSchoolPwd(HttpServletRequest request);
    ResultData updateSchoolBasicInfo(School school);

    /**
     * 获取5个最有实力的驾校
     *
     * @return
     * @author JX181114 --- 郑建辉
     */
    List<PowerSchool> getFiveMostPowerfulSchool();


    /*
     *@Description:微信小程序用
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/22 21:14
     **/

    PageInfo findSchool(PageDTO pageDTO);

}
