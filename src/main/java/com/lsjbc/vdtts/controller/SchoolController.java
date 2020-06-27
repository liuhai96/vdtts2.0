package com.lsjbc.vdtts.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsjbc.vdtts.dao.AccountDao;
import com.lsjbc.vdtts.dao.CarDao;
import com.lsjbc.vdtts.dao.ExamParamDao;
import com.lsjbc.vdtts.dao.SchoolDao;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.Car;
import com.lsjbc.vdtts.entity.ExamParam;
import com.lsjbc.vdtts.entity.School;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.service.intf.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@SuppressWarnings("all")
@RestController
@RequestMapping("/schoolController")
public class SchoolController {
	@Autowired
	private SchoolService schoolService;
	@Resource
	private ExamParamDao examParamDao;
	@Resource
	SchoolDao schoolDao;
	@Resource
	AccountDao accountDao;
	@Resource
	CarDao carDao;
	/*
	 *@Description:
	 *@Author:周永哲
	 *@Param:
	 *@return:
	 *@Date:2020/6/10 15860799877
	 **/
	@RequestMapping(value = "/selectSchoolInfo")//初始化驾校信息表
	public String selectSchoolInfo(HttpServletRequest request, HttpServletResponse response,
								  @RequestParam(value = "page") String page , @RequestParam(value = "limit") String limit,
								  School school) {
		int page2 = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
		System.out.println(" ---carpage="+page2);
		List<School> list= schoolService.selectAllInfo(school,page2,Integer.valueOf(limit));
		int count =schoolService.selectSchoolCount(school);
		System.out.println("驾校信息初始化操作--- list="+list+" count ="+count);
//		HttpSession session =request.getSession();
//		session.setAttribute("schoolinfo",list);
		LayuiTableData layuiData = new LayuiTableData();
		layuiData.setCode(0);
		layuiData.setData(list);
		layuiData.setCount(count);
		return JSON.toJSONString(layuiData);
	}

	/*
	 *@Description:
	 *@Author:周永哲
	 *@Param:
	 *@return:
	 *@Date:2020/6/10 15860799877
	 **/
	@RequestMapping(value = "/updateSchool")//驾校信息修改
	public String updateSchool(HttpServletRequest request, HttpServletResponse response, School school ) {
		System.out.println(" School:"+school.toString());
		int i=schoolService.updateSchool(school);
		String res = "";
		if(i>0){
			res="success";
			System.out.println("修改驾校信息成功");
			return res;
		}else {
			res="failed";
			System.out.println("修改驾校信息失败");
			return res;
		}
	}
	/*
	 *@Description:
	 *@Author:周永哲
	 *@Param:
	 *@return:
	 *@Date:2020/6/10 15860799877
	 **/
	@RequestMapping(value = "insertSchool")//添加驾校
	public  String insertSchool (HttpServletRequest request, HttpServletResponse response,School school, Account account){
		System.out.println(" 添加驾校school list="+school.toString());
		System.out.println(" 添加驾校account="+account.getAAccount()+" pwd="+account.getAPassword());
//		int a = schoolService.insertSchoolAccount(account);
		int a = accountDao.add(account);
		String res = "";
		if(a>0){
			System.out.println("成功添加驾校账号，此 a_id="+account.getAId());
			school.setSAccountId(account.getAId());
//			int i = schoolService.insertSchool(school);
			int i = schoolDao.add(school);
			if(i>0){
				res="success";
				System.out.println("添加驾校成功");
			}else {
				res="failed";
				System.out.println("添加驾校失败");
			}
		}else {
			System.out.println("添加驾校失败");
			res="failed";
		}
		return res;
	}
	/*
	 *@Description:
	 *@Author:周永哲
	 *@Param:
	 *@return:
	 *@Date:2020/6/18 15860799877
	 **/
	@RequestMapping(value = "/studentCount")//学员统计
	public List<School> studentCount(HttpServletRequest request,HttpServletResponse response){
		return schoolService.selectStudentCount();
	}

	/*
	 *@Description:
	 *@Author:周永哲
	 *@Param:
	 *@return:
	 *@Date:2020/6/19 15860799877
	 **/
	@RequestMapping(value = "/selectParamInfo")//初始化参数信息表
	public LayuiTableData selectParamInfo(HttpServletRequest request, HttpServletResponse response,int page ,int limit,ExamParam examParam) {
//		System.out.println("examlist"+examParam.toString());
		Page<ExamParam> pageinfo = PageHelper.startPage(page,limit,true);
		examParamDao.selectParamInfo(examParam.getPmKey());
		LayuiTableData layuiData = new LayuiTableData();
		layuiData.setCode(0);
		layuiData.setData(pageinfo.getResult());
		layuiData.setCount(pageinfo.getTotal());
		return layuiData;
	}

	@RequestMapping(value = "/updateParam")//参数信息修改
	public String updateParam(HttpServletRequest request, HttpServletResponse response, ExamParam examParam) {
		System.out.println(" examParam:"+examParam.toString());
		int i = examParamDao.updateById(examParam);
//		int i=schoolService.updateSchool(examParam);
		String res = "";
		if(i>0){
			System.out.println("修改驾校信息成功");
			return "success";
		}else {
			System.out.println("修改驾校信息失败");
			return "failed";
		}
	}



	@RequestMapping(value = "/findSchoolList",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object findSchoolList(HttpServletRequest request, HttpServletResponse response){
		String pageStr = request.getParameter("page");//页码
		String pageSizeStr = request.getParameter("limit");//每页记录数
		//查名字
		String sName = request.getParameter("sName");

		String draw = request.getParameter("draw");//重绘次数 和前台对应

		School school = new School();
		school.setSName(sName);
		LayuiTableData layuiTableData = schoolService.schoolList(school, Integer.parseInt(pageStr), Integer.parseInt(pageSizeStr));
		return JSON.toJSONString(layuiTableData);
	}
}
