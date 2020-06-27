package com.lsjbc.vdtts.service.impl;

import com.lsjbc.vdtts.dao.TransManageDao;
import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.TransManage;
import com.lsjbc.vdtts.service.intf.TransManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransManageServiceImpl implements TransManageService {

    @Autowired
    private TransManageDao transManageDao;
    @Override
    public TransManage findTransManage(Account account) {
        return transManageDao.findTransManage(account);
    }
}
