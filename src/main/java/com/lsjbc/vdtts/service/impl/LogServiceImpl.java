package com.lsjbc.vdtts.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsjbc.vdtts.dao.mapper.LogMapper;
import com.lsjbc.vdtts.pojo.dto.PageDTO;
import com.lsjbc.vdtts.service.intf.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;
    @Override
    public PageInfo findLogList(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit(), true);
        return new PageInfo<>(logMapper.selectAll());
    }
}
