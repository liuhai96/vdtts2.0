package com.lsjbc.vdtts.service.intf;

import com.github.pagehelper.PageInfo;
import com.lsjbc.vdtts.pojo.dto.PageDTO;

public interface LogService {
    PageInfo findLogList(PageDTO pageDTO);
}
