package com.lsjbc.vdtts.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.lsjbc.vdtts.pojo.dto.PageDTO;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.service.intf.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("logControl")
@RestController
public class LogControl {
    @Autowired
    private LogService logService;
    @RequestMapping(value = "/findLogList")
    public String findLogList(PageDTO pageDTO){
        LayuiTableData layuiTableData = new LayuiTableData();
        PageInfo pageInfo = logService.findLogList(pageDTO);
        System.out.println(pageInfo.getList());
        layuiTableData.setCount(Integer.parseInt(String.valueOf(pageInfo.getTotal())));
        layuiTableData.setData(pageInfo.getList());
        return JSON.toJSONString(layuiTableData);
    }
}
