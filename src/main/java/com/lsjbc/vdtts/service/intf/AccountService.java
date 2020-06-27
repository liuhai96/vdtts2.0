package com.lsjbc.vdtts.service.intf;

import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.AdminAccount;
import com.lsjbc.vdtts.entity.Menuitem;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AccountService {
    public LayuiTableData findAccount(String account);
    public int addStudentAccount(Account account);//加入学生账号
    public Account accountRepetition(String account);//账号查重
    public Account addAccountData(Account account);//加入数据
    public ResultData UserLogin(Account account, HttpServletRequest request);//登录
    public ResultData verifyPass(Account account);//密码验证
    public ResultData updatePass(Account account);//修改密码

    //修改驾校密码刘海
    ResultData updateSchoolPwd(String oldPwd,String newPwd,String repeatPwd,HttpServletRequest request);//修改驾校密码

    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/14
     **/
    public int updateAdminAccount(AdminAccount account);
    public AdminAccount adminLogin(AdminAccount account);
    public List<Menuitem> adminList(int roleId);
}
