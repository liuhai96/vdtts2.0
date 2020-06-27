package com.lsjbc.vdtts.service.impl;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsjbc.vdtts.constant.EvaluateType;
import com.lsjbc.vdtts.dao.*;
import com.lsjbc.vdtts.dao.mapper.CarMapper;
import com.lsjbc.vdtts.dao.mapper.SchoolMapper;
import com.lsjbc.vdtts.dao.mapper.StudentMapper;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.ExamParam;
import com.lsjbc.vdtts.entity.School;
import com.lsjbc.vdtts.entity.Student;
import com.lsjbc.vdtts.pojo.dto.CarCount;
import com.lsjbc.vdtts.pojo.dto.PageDTO;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.PowerSchool;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.pojo.vo.SchoolDetail;
import com.lsjbc.vdtts.service.intf.SchoolService;
import com.lsjbc.vdtts.utils.Tool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("all")
@Service(SchoolServiceImpl.NAME)
public class SchoolServiceImpl implements SchoolService
{

	/**
	 * Bean名
	 */
	public static final String NAME = "SchoolService";

//	@Resource(name = SchoolMapper.NAME
	@Resource
	private SchoolMapper schoolMapper;

	@Resource
	private StudentMapper studentMapper;

	@Resource(name = SchoolDao.NAME)
	private SchoolDao schoolDao;

	@Resource(name = CarDao.NAME)
	private CarDao carDao;

	@Resource
	private CarMapper carMapper;

	@Resource(name = StudentDao.NAME)
	private StudentDao studentDao;

	@Resource(name = EvaluateDao.NAME)
	private EvaluateDao evaluateDao;

	@Resource(name = TeacherDao.NAME)
	private TeacherDao teacherDao;

	/*
	 *@Description:
	 *@Author:陈竑霖
	 *@Param:
	 *@return:
	 *@Date:2020/6/8 1591587038161
	 **/
	@Override
	public LayuiTableData schoolList(School school, int page, int pageSize)
	{
		int start = (page - 1) * pageSize;//计算出起始查询位置
		if (start < 0)
		{
			start = 0;
		}
		List<School> list = schoolMapper.schoolList(school, start, pageSize);
		int count = schoolMapper.schoolcount(school);

		LayuiTableData layuiData = new LayuiTableData();
		if (list.size() > 0)
		{
			layuiData.setCode(0);
			layuiData.setMsg("");
			layuiData.setCount(count);
			layuiData.setData(list);
			System.out.println(school);
		} else
		{
			layuiData.setCode(1);
			layuiData.setMsg("查询失败");
		}
		return layuiData;
	}

	@Override
	public int schoolCount(School school)
	{
		return schoolMapper.schoolcount(school);
	}

	@Override
	public List<School> schoolMessageList(School school, int stripStart, int stripEnd)
	{
		return schoolMapper.schoolList(school, stripStart, stripEnd);
	}

	/*
	 *@Description:修改审核状态
	 *@Author:陈竑霖
	 *@Param:[teacher]
	 *@return:com.lsjbc.vdtts.pojo.vo.LayuiTableData
	 *@Date:2020/6/9 15:26
	 **/
	@Override
	public LayuiTableData updateschoolInfo(School school)
	{
		LayuiTableData layuiTableData = new LayuiTableData();
		int num = schoolMapper.updateschoolInfo(school);
		if (num > 0)
		{
			layuiTableData.setCode(1);
		}
		return layuiTableData;
	}

	@Override
	public LayuiTableData findschool(School school)
	{
		LayuiTableData layuiTableData = new LayuiTableData();
		List<School> schoolList = schoolMapper.findschool(school);
		layuiTableData.setData(schoolList);
		return layuiTableData;
	}

	//修改处罚招生
	@Override
	public LayuiTableData punishcall(int sId)
	{

		LayuiTableData layuiTableData = new LayuiTableData();
		int num = schoolMapper.punishcall(sId);
		if (num > 0)
		{
			layuiTableData.setCode(1);
		} else
		{
			layuiTableData.setCode(0);
		}
		return layuiTableData;
	}

	//修改解禁招生
	@Override
	public LayuiTableData unbindcall(int sId)
	{
		LayuiTableData layuiTableData = new LayuiTableData();
		int num = schoolMapper.unbindcall(sId);
		if (num > 0)
		{
			layuiTableData.setCode(1);
		} else
		{
			layuiTableData.setCode(0);
		}
		return layuiTableData;
	}

	//修改处罚登录
	@Override
	public LayuiTableData punishlogon(int sId)
	{

		LayuiTableData layuiTableData = new LayuiTableData();
		int num = schoolMapper.punishlogon(sId);
		if (num > 0)
		{
			layuiTableData.setCode(1);
		} else
		{
			layuiTableData.setCode(0);
		}
		return layuiTableData;
	}

	//修改解禁登录
	@Override
	public LayuiTableData unbindlogon(int sId)
	{
		LayuiTableData layuiTableData = new LayuiTableData();
		int num = schoolMapper.unbindlogon(sId);
		if (num > 0)
		{
			layuiTableData.setCode(1);
		} else
		{
			layuiTableData.setCode(0);
		}
		return layuiTableData;
	}

	/**
	 * 根据主键获取学校信息
	 *
	 * @param id 主键
	 * @return 对象
	 * @author 陈竑霖
	 */
	@Override
	public School getSchoolBySchoolId(Integer id) {
		return schoolDao.getById(id);
	}

	//驾校查询身份证
	@Override
	public ResultData insSfz( HttpServletRequest request)
	{
		ResultData resultData = null;
		Integer sSchoolId = Integer.parseInt(request.getParameter("schoolId"));
		Student student =(Student) request.getSession().getAttribute("student");
		if (student != null)
		{ //查询

			if (student.getSSchoolId() == null)
			{
				int num =studentMapper.inschool(student,sSchoolId);
					resultData = ResultData.error(1, "该学员已经报名成功");
				} else
				{
					resultData = ResultData.error(2, "该学员已报名驾校，不能再报名");
				}


		}else{
			resultData = ResultData.error(3, "未该有此学员信息请先去注册");
		}
		return resultData;
	}

    @Override//驾驶入驻
    public ResultData schoolToProduct(School school, String id){
        ResultData resultData = ResultData.success();
        if(schoolMapper.addSchool(school) > 0){
            resultData.put("result","恭喜！"+school.getSName()+" 已经成入驻本平台\n\n" +
                    "你的平台管理账号为："+id+
                "\n\n在审核通过后，您就可以在本平台上管理您的驾校了");
        } else {
            resultData.put("result","很遗憾！未知原因导致"+school.getSName()+"未能成功入驻本平台\n\n" +
                    "请重试或者联系我们的工作人员！给您带来的不便敬请谅解！");
        }
        return resultData;
    }


	@Override
	public ResultData findSchoolInfo(HttpServletRequest request, HttpServletResponse response) {
		School school = (School) request.getSession().getAttribute("school");
		school = schoolMapper.findSchoolInfo(school.getSId());
		ResultData resultData = null;
		if (school != null) {
			resultData = ResultData.success("school", school);
		} else {
			resultData = ResultData.error(-1, "系统出错请稍后尝试");
		}
		return resultData;
	}

	/**
	 * 根据驾校的的名字，来分页查询数据
	 *
	 * @param name 驾校名称
	 * @param page 页数
	 * @return 分页对象
	 */
	@Override
	public Page<School> getSchoolPageByName(String name, Integer page) {
		Page<School> pageInfo = PageHelper.startPage(page, 6, true);
		schoolDao.getByNameLike(name);
		return pageInfo;
	}

	/**
	 * 根据驾校的的名字，来分页查询数据
	 *
	 * @param name 驾校名称
	 * @param page 页数
	 * @return 分页对象
	 */
	@Override
	public Page<SchoolDetail> getSchoolDetailPageByName(String name, Integer page) {

		Page<School> schools = getSchoolPageByName(name, page);

		Page<SchoolDetail> details = new Page<>();
		details.setTotal(schools.getTotal());
		details.setPages(schools.getPages());

		schools.getResult().stream().forEach(item -> {
			Integer schoolId = item.getSId();
			SchoolDetail detail = SchoolDetail.generateDetail(item);
			detail.setScore(evaluateDao.getAvgByTypeAndId(EvaluateType.TYPE_SCHOOL, schoolId));
			detail.setCarCount(carDao.getCountBySchoolId(schoolId));
			detail.setTeacherCount(teacherDao.getCountBySchoolId(schoolId));
			detail.setStudentCount(studentDao.getStudentCountBySchoolId(schoolId));
			details.getResult().add(detail);
		});

		return details;
	}


	/*
	 *@Description:
	 *@Author:周永哲
	 *@Param:
	 *@return:
	 *@Date:2020/6/10
	 **/
	@Override
	public List<School> selectAllInfo(School school, int page, int limit) {
		List<School> selectAllInfo = schoolMapper.selectAllInfo(school,page,limit);
		return selectAllInfo;
	}

	@Override
	public int selectSchoolCount(School school) {
		int selectCount = schoolMapper.selectSchoolCount(school);
		return selectCount;
	}

	@Override
	public int deleteSchool(String schoolId) {
		int deleteSchool = schoolMapper.deleteSchool(schoolId);
		return deleteSchool;
	}

	@Override
	public int insertSchool(School school) {
		int insertSchool = schoolMapper.insertSchool(school);
		return insertSchool;
	}

	@Override
	public int insertSchoolAccount(Account account) {
		int insertSchoolAccount = schoolMapper.insertSchoolAccount(account);
		return insertSchoolAccount;
	}

	@Override
	public int updateSchool(School school) {
		int updateSchool = schoolMapper.updateSchool(school);
		return updateSchool;
	}

	@Override
	public ResultData updateSchoolPwd(HttpServletRequest request) {
		ResultData resultData = null;
		Tool tool = new Tool();
		School school = (School) request.getSession().getAttribute("school");
		String pwd = schoolMapper.findSchoolPwd(school.getSAccountId());
		String  oldPwd = tool.createMd5(request.getParameter("oldPwd"));
		String  newPwd = tool.createMd5(request.getParameter("newPwd"));
		String  repeatPwd = tool.createMd5(request.getParameter("repeatPwd"));
		System.out.println("pwd"+pwd);
		if(newPwd.equals(repeatPwd)){
			if(oldPwd.equals(pwd)){
				int num = schoolMapper.updateSchoolPwd(school.getSAccountId(),newPwd);
				resultData = ResultData.error(1,"密码修改成功");
			}else{
				resultData = ResultData.error(-1,"旧密码输入错误");
			}
		}else{
			resultData = ResultData.error(-1,"新密码与重复输入密码不同");
		}
		return resultData;
	}

	@Override
	public List<School> selectStudentCount() {
		List<School> selectStudentCount = schoolMapper.selectStudentCount();
		return selectStudentCount;
	}

	@Override
	public List<ExamParam> selectParamInfo(ExamParam examParam, int page, int limit) {
		List<ExamParam> selectParamInfo = schoolMapper.selectParamInfo(examParam,page,limit);
		return selectParamInfo;
	}


	@Override
	public ResultData updateSchoolBasicInfo(School school) {
		 ResultData resultData = null;
		 if(school.getSImageUrl().equals("")){
		 	school.setSImageUrl(null);
		 }
		 if(school.getSAddress().equals("")){
		 	school.setSAddress(null);
		 }
		 if(school.getSPhone().equals("")){
		 	school.setSPhone(null);
		 }
		 int num = schoolMapper.updateByPrimaryKeySelective(school);
		 if(num>0){
		 	resultData = ResultData.success(1,"修改驾校基本信息成功");
		 }else {
			 resultData = ResultData.error(-1,"未找到改驾校信息");
		 }
		 return resultData;
	}

	/**
	 * 获取5个最有实力的驾校
	 *
	 * @return
	 * @author JX181114 --- 郑建辉
	 */
	@Override
	public List<PowerSchool> getFiveMostPowerfulSchool() {

		List<CarCount> carCounts = carMapper.getFiveMostPowerfulSchool();

		List<PowerSchool> schoolList = carCounts.stream().map(item->{
			School school = schoolDao.getById(item.getSchoolId());
			return PowerSchool.builder().name(school.getSName()).count(item.getCount()).build();
		}).collect(Collectors.toList());

		return schoolList;
	}


	/*
	 *@Description:微信小程序查看驾校
	 *@Author:刘海
	 *@Param:
	 *@return:
	 *@Date:2020/6/22 21:17
	 **/
	@Override
	public PageInfo findSchool(PageDTO pageDTO) {
		System.out.println("11111111111111111111111111");
		PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit(), true);
		System.out.println(schoolMapper.selectAll());
		return new PageInfo<>(schoolMapper.selectAll());
	}
}
