package com.lsjbc.vdtts.controller;


import com.alibaba.fastjson.JSON;
import com.lsjbc.vdtts.entity.Notice;
import com.lsjbc.vdtts.entity.Student;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.service.intf.NoticeService;
import com.lsjbc.vdtts.service.intf.StudentService;
import com.lsjbc.vdtts.service.intf.sfzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *@Description:
 *@Author:陈竑霖
 *@Param:
 *@return:
 *@Date:2020/6/8 1591597466103
 **/
//@SuppressWarnings("all")
@Controller
@RequestMapping("/pipeControl")
public class pipeControl
{
		@Autowired
		private StudentService studentService;
		@Autowired
	    private NoticeService noticeService;
	@Autowired
	private sfzService sfzService;
//学员表查看
		@RequestMapping(value = "/studentList",produces = {"application/json;charset=UTF-8"})
		@ResponseBody
		public String studentList(HttpServletRequest request, HttpServletResponse response,Student student) {
			String pageStr = request.getParameter("page");//页码
			String pageSizeStr = request.getParameter("limit");//每页记录数
			String draw = request.getParameter("draw");//重绘次数 和前台对应

			LayuiTableData layuiTableData = studentService.selectList(student, Integer.parseInt(pageStr), Integer.parseInt(pageSizeStr));
			return JSON.toJSONString(layuiTableData);
		}

    //	公告表查看
    @RequestMapping(value = "/noticeList",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String noticeList(HttpServletRequest request, HttpServletResponse response, Notice notice) {
	    String pageStr = request.getParameter("page");//页码
	    String pageSizeStr = request.getParameter("limit");//每页记录数
	    String draw = request.getParameter("draw");//重绘次数 和前台对应

	    LayuiTableData layuiTableData = noticeService.noticeList(notice, Integer.parseInt(pageStr), Integer.parseInt(pageSizeStr));
	    return JSON.toJSONString(layuiTableData);
    }

	@RequestMapping(value = "/deletenotice",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public  LayuiTableData deletenotice(int nId){
		LayuiTableData layuiTableData =noticeService.deletenotice(nId);
		return layuiTableData;
	}

	@RequestMapping(value = "/addnotice")
	@ResponseBody
	public LayuiTableData addnotice(Notice notice){
        notice.setNType("notice");
		LayuiTableData layuiTableData =noticeService.addnotice(notice);
		return layuiTableData;
	}

	@RequestMapping(value = "/sfzdiscern",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String sfzdiscern(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
		response.setContentType("text/html;charset=utf-8");
		System.out.println("asdasdsadasdasdasdasdasdasdasdasdasdasdasd");
		return sfzService.sfzdiscern(request,response,file);
	}
}

