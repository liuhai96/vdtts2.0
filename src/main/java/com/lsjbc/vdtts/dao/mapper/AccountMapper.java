package com.lsjbc.vdtts.dao.mapper;

import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.AdminAccount;
import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;
import com.lsjbc.vdtts.entity.Menuitem;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface AccountMapper extends CustomBaseMapper<Account> {



    public Account findAccount(@Param("aAccount") String aAccount);

    public int addAccount(Account account);

    public Account UserLogin(Account account);//登录方法

    public int deleteAccount(@Param("aId") int aId);

    public Account selectAccount(@Param("e") Account account);

    public int updateAccount(@Param("e") Account account);//修改数据

    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/16
     **/
    public int updateAdminAccount(AdminAccount account);
    public AdminAccount adminLogin(AdminAccount account);
    public List<Menuitem> adminList(int roleId);
    public int updateaccount(Account account);



    String findSchoolPwd(Integer aId);
    int updateSchoolPwd(String aPassword,Integer aId);//


}
