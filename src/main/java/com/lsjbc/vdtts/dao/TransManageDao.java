package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.TransManageMapper;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.TransManage;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Repository
public class TransManageDao implements BaseDao<TransManage> {

    @Resource
    private TransManageMapper transManageMapper;
    @Override
    public TransManage getById(Integer id) {
        return (TransManage) transManageMapper.selectByPrimaryKey(id);
    }

    public TransManage findTransManage(Account account) {
        TransManage object = TransManage.builder().tmAccountId(account.getAId()).build();
        return (TransManage) transManageMapper.selectOne(object);
    }

    @Override
    public Integer add(TransManage object) {
        return null;
    }

    @Override
    public Integer updateById(TransManage object) {
        return null;
    }

    @Override
    public Integer deleteById(Integer id) {
        return null;
    }
}
