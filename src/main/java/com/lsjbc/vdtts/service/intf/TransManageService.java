package com.lsjbc.vdtts.service.intf;

import com.lsjbc.vdtts.entity.Account;
import com.lsjbc.vdtts.entity.TransManage;

public interface TransManageService {
    public TransManage findTransManage(Account account);
}
