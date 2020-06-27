package com.lsjbc.vdtts.controller;

import com.lsjbc.vdtts.service.intf.FrontMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/frontMenuController")
public class FrontMenuController {
    @Autowired
    private FrontMenuService frontMenuService;
    @RequestMapping(value = "selectMenuList")
    public List selectMenuList(HttpServletRequest request){
        System.out.println("frontMenuService.selectMenuList(1)>>>>"+frontMenuService.selectMenuList(request));
        return frontMenuService.selectMenuList(request);
    }
}
