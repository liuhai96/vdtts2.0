package com.lsjbc.vdtts.controller;

import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.AdminAccount;
import com.lsjbc.vdtts.entity.Menuitem;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.intf.AccountService;
import com.lsjbc.vdtts.utils.Tool;
import com.lsjbc.vdtts.utils.menuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/*
 *@Description:
 *@Author:李浪_191019
 *@Param:account 含有登录账号密码的登录实例类
 *@return:aType表明，aId登录成功后的登录者Id,
 *@Date:2020/6/8 1:30
 **/
@RestController
@RequestMapping(value = "/adminControl")
public class AdminControl {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/userLogin")
    @ResponseBody
    public ResultData UserLogin(HttpServletRequest request, HttpServletResponse response, Account account) {
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("text/html;charset=utf-8");
        account.setAPassword(new Tool().createMd5(account.getAPassword()));//转MD5码（加密）
        return accountService.UserLogin(account, request);
    }

    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/14 15860799877
     **/
    @RequestMapping(value = "/adminLogin")
    public String adminLogin(HttpServletRequest request, HttpServletResponse response, AdminAccount account) {
//        account.setAPassword(new Tool().createMd5(account.getAPassword()));//转MD5码（加密）
        System.out.println("管理员登录--- account:" + account.getAcAccount() + " pwd:" + account.getAcPassword());
        AdminAccount adminLog = accountService.adminLogin(account);
        String res = "";
        if (adminLog != null) {
            System.out.println("管理员登录成功：admin=" + adminLog);
            HttpSession session = request.getSession();
            session.setAttribute("admin", adminLog);
            res = "success";
        } else {
            System.out.println("账号或密码错误！");
            res = "failed";
        }
        return res;
    }

    @RequestMapping(value = "/adminList")
    public List adminList(HttpServletRequest request) {
        AdminAccount adminAccount = (AdminAccount) request.getSession().getAttribute("admin");
        System.out.println("adminAccount=" + adminAccount.toString());
        List<Menuitem> menuitemList = accountService.adminList(adminAccount.getRoleId());
        List<Menuitem> mList = new ArrayList<>();
        for (Menuitem menu : menuitemList) {
            menu.setId(menu.getMId());
            menu.setTitle(menu.getMName());
            menu.setType("0");
            menu.setHref(menu.getMUrl());
            if (menu.getMParentId() != 0) {
                menu.setType("1");
                menu.setOpenType("_iframe");
            }
            mList.add(menu);
        }
        List menuList = menuUtil.toTree(menuitemList, 0);
        return menuList;
    }

    @RequestMapping(value = "/updatePwd")
    public String updatePwd(HttpServletRequest request, HttpServletResponse response, AdminAccount adminAccount) {
        int updateaAccount = accountService.updateAdminAccount(adminAccount);
        String res = "";
        if (updateaAccount > 0) {
            res = "success";
            request.getSession().invalidate();//清空session中的信息
            System.out.println("修改管理员信息成功");
            return res;
        } else {
            res = "failed";
            System.out.println("修改管理员信息失败");
            return res;
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, String logout) {
        if (logout != null && !logout.equals("")) {
            request.getSession().invalidate();//清空session中的信息
            System.out.println("账号退出成功");
            return "success";
        } else {
            System.out.println("账号退出失败");
            return "failed";
        }
    }
}
