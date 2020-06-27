package com.lsjbc.vdtts.controller;

import com.alibaba.fastjson.JSON;
import com.lsjbc.vdtts.entity.Notice;
import com.lsjbc.vdtts.service.intf.NoticeService;
import com.lsjbc.vdtts.utils.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author LiLang9725
 * @date 2020/6/16 9:30
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/dynamicIssue")
    public ModelAndView DynamicIssue(Notice notice){
        ModelAndView modelAndView = new ModelAndView();
        notice.setNTime(new Tool().getDate("yyyy/MM/dd"));
        System.out.println(JSON.toJSONString(notice));
        modelAndView.addObject("layuiTableData",noticeService.addnotice(notice));
        modelAndView.setViewName("/back/industry-trends");
        return modelAndView;
    }

}
