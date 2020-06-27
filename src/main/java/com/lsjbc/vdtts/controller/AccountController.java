package com.lsjbc.vdtts.controller;

import com.alibaba.fastjson.JSON;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.intf.AccountService;
import com.lsjbc.vdtts.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiLang9725
 * @date 2020/6/13 23:39
 */
/*
 *@Description:
 *@Author:李浪_191019
 *@Param:account 含有登录账号密码的登录实例类
 *@return:aType表明，aId登录成功后的登录者Id,
 *@Date:2020/6/8 1:30
 **/
@Controller
@RequestMapping
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/userLogin")
    @ResponseBody
    /*
     *@Description:登录
     *@Author:李浪_191019
     *@Param:[request, response, account]
     *@return:com.lsjbc.vdtts.pojo.vo.ResultData
     *@Date:2020/6/13 23:36
     **/
    public ResultData UserLogin(HttpServletRequest request, HttpServletResponse response, Account account) {
        response.setContentType("text/html;charset=utf-8");
        System.out.println(JSON.toJSONString(account));
        return accountService.UserLogin(account, request);
    }

    @RequestMapping(value = "/verifyAdmin")
    @ResponseBody
    /*
     *@Description:密码验证
     *@Author:李浪_191019
     *@Param:[account]
     *@return:java.lang.String
     *@Date:2020/6/13 23:35
     **/
    public String VerifyAdmin(Account account) {
        account.setAPassword(new Tool().createMd5(account.getAPassword()));
        return JSON.toJSONString(accountService.verifyPass(account));
    }

    @RequestMapping(value = "/changePassword")
    @ResponseBody
    public String changePassword(Account account) {
        account.setAPassword(new Tool().createMd5(account.getAPassword()));
        return JSON.toJSONString(accountService.updatePass(account));
    }

    /*
     *@Description:修改驾校密码
     *@Author:刘海
     *@Param:[oldPwd, newPwd, repeatPwd, request]
     *@return:com.lsjbc.vdtts.pojo.vo.ResultData
     *@Date:2020/6/16 23:39
     **/
    @RequestMapping(value = "/updateSchoolPwd")
    @ResponseBody
    public ResultData updateSchoolPwd(String oldPwd, String newPwd, String repeatPwd, HttpServletRequest request) {
        return accountService.updateSchoolPwd(oldPwd, newPwd, repeatPwd, request);
    }

    @RequestMapping(value = "/transfer")
    /*
     *@Description: 主要用于界面中转
     *@Author:李浪_191019
     *@Param:[logo]
     *@return:org.springframework.web.servlet.ModelAndView
     *@Date:2020/6/18 14:40
     **/
    public String Transfer(String logo, HttpServletRequest request) {
        String goal;//去的目的地
        switch (logo) {
            case "institutionIndex"://去机构首页
                goal = "/pages/backhomepage/index";
                break;
            case "schoolIn"://驾校入驻
                goal = "/pages/homepage/driving-in/driving-in";
                break;
            case "logout"://注销登录
            case "exit"://退出
                request.getSession().invalidate();
            case "institutionLogin"://去机构登录页
                goal = "/pages/index/institution";
                break;
            case "alterpass"://学生修改信息页
                goal = "/pages/student/student_password";
                break;
            default://回系统首页
                goal = "redirect:/index";//后台重定向到首页
                break;
        }
        return goal;
    }
}
