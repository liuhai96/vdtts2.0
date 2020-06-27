package com.lsjbc.vdtts.controller;

import com.alibaba.fastjson.JSON;
import com.lsjbc.vdtts.entity.Link;
import com.lsjbc.vdtts.service.intf.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LiLang9725
 * @date 2020/6/16 16:12
 */
@Controller
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @RequestMapping(value = "/addLink")
    @ResponseBody
    public String AddLink(Link link){
        return JSON.toJSONString(linkService.addLink(link));
    }
    @RequestMapping(value = "/selectLink")
    @ResponseBody
    public String selectLink(Link link,int page,int limit){
        return JSON.toJSONString(linkService.selectLink(link,page,limit));
    }
    @RequestMapping(value = "/updateLink")
    @ResponseBody
    public String UpdateLink(Link link){
        return JSON.toJSONString(linkService.Update(link));
    }
    @RequestMapping(value = "/deleteLink")
    @ResponseBody
    public String DeleteLink(Link link){
        return JSON.toJSONString(linkService.Delete(link));
    }
}
