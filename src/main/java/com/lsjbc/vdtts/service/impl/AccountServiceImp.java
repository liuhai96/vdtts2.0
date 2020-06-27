package com.lsjbc.vdtts.service.impl;

import com.lsjbc.vdtts.dao.StudentDao;
import com.lsjbc.vdtts.dao.TransManageDao;
import com.lsjbc.vdtts.dao.mapper.*;
import com.lsjbc.vdtts.entity.*;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.intf.AccountService;
import com.lsjbc.vdtts.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiLang9725
 * @date 2020/6/9 1:39
 */
@Service
@Transactional
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private EvaluateMapper evaluateMapper;
    @Autowired
    private TransManageDao transManageDao;

    @Resource(name = StudentDao.NAME)
    private StudentDao studentDao;


    @Override
    public LayuiTableData findAccount(String account) {
        return null;
    }

    @Override
    /*
     *@Description:
     *@Author:李浪_191019
     *@Param:[account]
     *@return:int
     *@Date:2020/6/9 1:54
     **/
    public int addStudentAccount(Account account){
        return accountMapper.addAccount(account);
    }

    @Override
    /*
     *@Description:
     *@Author:李浪_191019
     *@Param:[account]
     *@return:com.lsjbc.vdtts.entity.Account
     *@Date:2020/6/9 11:06
     **/
    public Account accountRepetition(String account){
        return accountMapper.findAccount(account);
    }

    @Override
    /*
     *@Description:加登录数据
     *@Author:李浪_191019
     *@Param:[account]
     *@return:com.lsjbc.vdtts.entity.Account
     *@Date:2020/6/12 0:14
     **/
    public Account addAccountData(Account account){
        Tool tool = new Tool();
        for(int i = 0;i < 5;i++){
            String aAccount = tool.getRandCode(tool.getRandom(6,11),null);//随机获取6~11位账号
            if(accountMapper.findAccount(aAccount) == null) {//随机账号查重
                account.setAAccount(aAccount);
                accountMapper.addAccount(account);//加登录数量
                break;
            }
        }
        return account;
    }
    @Override
    public ResultData UserLogin(Account account, HttpServletRequest request){
        String nextJsp = "";//下一个界面的路径
        Tool tool = new Tool();
        String notify = "";//弹窗通知信息
        ResultData resultData = ResultData.success();
        account.setAPassword(tool.createMd5(account.getAPassword()));
        account = accountMapper.UserLogin(account);
        try{
//            request.getSession().setAttribute("account", account);
            request.getSession().setAttribute("aId", account.getAId());
            request.getSession().setAttribute("aType", account.getAType());
        }catch (Exception e){}
        if(account != null){ //登录成功时
            try {
                switch (account.getAType()) {
                    case "school": //驾校登录界面地址
                        School school = schoolMapper.findAccount(account.getAId());
                        if(school.getSLock().equals("false")){
                            resultData.setMsg("驾校已被锁定登录");
                            nextJsp = "transfer?logo= ";
                        }else{
                            request.getSession().setAttribute("school", school);
                            request.getSession().setAttribute("account",account.getAAccount());
                            request.getSession().setAttribute("name",school.getSName());
                            request.getSession().setAttribute("userHead",school.getSImageUrl());
                            resultData.setMsg("登录成功!");
                            nextJsp = "transfer?logo=institutionIndex";//前端jsp地址
                        }
                        break;
                    case "teacher"://教练登录界面地址
                        //教练的对象
                        Teacher teacher = teacherMapper.findAccount(account);
                        if(teacher.getTLock().equals("true")){
                            resultData.setMsg("您的账号已被锁定，请找驾校询问具体原因");
                            nextJsp = "transfer?logo=institutionLogin";
                        }else{
                            request.getSession().setAttribute("teacher", teacher);
                            request.getSession().setAttribute("account",account.getAAccount());
                            request.getSession().setAttribute("name",teacher.getTName());
                            request.getSession().setAttribute("userHead",teacher.getTPic());
                            //教练评价
                            Evaluate evaluate = new Evaluate();
                            evaluate.setEToId(teacher.getTId());
                            evaluate.setEType("teacher");
                            request.getSession().setAttribute("evaluate", evaluateMapper.selectEvaluate(evaluate));
                            nextJsp = "transfer?logo=institutionIndex";//前端jsp地址
                            resultData.setMsg("登录成功");
                        }
                        break;
                    case "manage":
                        TransManage transManage = transManageDao.findTransManage(account);
                        if(transManage!=null){
                            request.getSession().setAttribute("manage",transManage);
                            request.getSession().setAttribute("account",account.getAAccount());
                            request.getSession().setAttribute("name",transManage.getTmName());
                            request.getSession().setAttribute("userHead","/image/sch.jpg");
                            resultData.setMsg("登录成功");
                            nextJsp = "transfer?logo=institutionIndex";//前端jsp地址
                        }else{
                            resultData.setMsg("未找到该运管信息");
                            nextJsp = "transfer?logo=institutionLogin";
                        }
                        break;
                }
            } catch (Exception e){
                resultData.setMsg("入驻待审核！");
                nextJsp = "transfer?logo=institutionLogin";//前端jsp地址
            }
        } else {//登录失败
            resultData.setMsg("登录失败，请核对账号");
            nextJsp = "transfer?logo=institutionLogin";//前端jsp地址
        }
        resultData.put("url",nextJsp);
        return resultData;
    }

    @Override
    /*
     *@Description:
     *@Author:李浪_191019
     *@Param:[account] 密码验证
     *@return:com.lsjbc.vdtts.pojo.vo.ResultData
     *@Date:2020/6/13 14:34
     **/
    public ResultData verifyPass(Account account){  //有改动，原来的true和false在js里不识别
        account = accountMapper.selectAccount(account);
        System.out.println(account);
        if(account != null) return ResultData.success("1");
        else return ResultData.success("-1");
    }

    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/11
     **/
    @Override
    public ResultData updatePass(Account account){
        if(accountMapper.updateAccount(account) > 0)
            return ResultData.success("true");
        else return ResultData.success("false");
    }

    /*
     *@Description:
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/17 0:27
     **/
    @Override
    public  ResultData updateSchoolPwd(String oldPwd,String newPwd,String repeatPwd,HttpServletRequest request) {
        School school = (School) request.getSession().getAttribute("school");
        String passwoed  = accountMapper.findSchoolPwd(school.getSAccountId());
        ResultData resultData = null;
             if(newPwd.equals(repeatPwd)){
                 if(oldPwd.equals(passwoed)){
                     int num = accountMapper.updateSchoolPwd(newPwd,school.getSAccountId());
                     if(num>0){
                         resultData = ResultData.success(1,"修改密码成功");
                     }else{
                         resultData = ResultData.success(-1,"未找到该驾校信息");
                     }
                 }else{
                     resultData = ResultData.success(-1,"旧密码输入错误");
                 }
             }else{
                 resultData = ResultData.success(-1,"两次密码输入不同");
             }
        return resultData;
    }

    @Override
    public int updateAdminAccount(AdminAccount adminAccount) {
        int updateaccount = accountMapper.updateAdminAccount(adminAccount);
        return updateaccount;
    }
    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/14
     **/
    @Override
    public AdminAccount adminLogin(AdminAccount account) {
        AdminAccount adminLogin = accountMapper.adminLogin(account);
        return adminLogin;
    }

    @Override
    public List<Menuitem> adminList(int roleId) {
        List<Menuitem> adminList = accountMapper.adminList(roleId);
        return adminList;
    }
}
