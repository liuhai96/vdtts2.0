package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.AccountMapper;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.School;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @ClassName: AccountDao
 * @Description:
 * @Datetime: 2020/6/16   20:27
 * @Author: JX181114 - 郑建辉
 */
@Repository(AccountDao.NAME)
public class AccountDao{

    /**
     * Bean名
     */
    public static final String NAME = "AccountDao";

    @Resource
    private AccountMapper mapper;

    /**
     * 账号登录
     *
     * @param token 用户提交的登录信息
     * @return 如果找不到，或者密码不正确，返回Null,否则正常返回对象
     * @author JX181114 --- 郑建辉
     */
    public Account login(Account token) {

        Account account = hasAccount(token.getAAccount());

        if (account == null) {
            return null;
        }

        return account.getAPassword().equals(token.getAPassword()) ? account : null;
    }

    /**
     * 判断是否存在，如果不存在就插入
     *
     * @param token 要查询的账号信息
     * @return 存在返回0，插入成功返回1
     */
    public Integer insertIfNotExist(Account token){
        Account account = hasAccount(token.getAAccount());

        if(account!=null){
            return 0;
        }

        return mapper.insertSelective(token);
    }

    /**
     * 判断账号是否存在
     *
     * @param account 账号
     * @return 存在返回对象，不存在返回null
     * @author JX181114 --- 郑建辉
     */
    public Account hasAccount(String account){
        Example example = new Example(Account.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("aAccount", account);

        return  (Account) mapper.selectOneByExample(example);
    }

    /**
     * 永哲
     * @param account
     * @return
     */
    public Integer add(Account account) {//添加账号
        return mapper.addAccount(account);
    }

}
