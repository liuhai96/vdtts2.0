package com.lsjbc.vdtts.service.impl;

import com.lsjbc.vdtts.dao.mapper.FrontMenuMapper;
import com.lsjbc.vdtts.entity.FrontMenu;
import com.lsjbc.vdtts.entity.School;
import com.lsjbc.vdtts.entity.Teacher;
import com.lsjbc.vdtts.entity.TransManage;
import com.lsjbc.vdtts.service.intf.FrontMenuService;
import com.lsjbc.vdtts.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class FrontMenuServiceImpl implements FrontMenuService {
    @Autowired
    private FrontMenuMapper frontMenuMapper;
    @Override
    public List selectMenuList(HttpServletRequest request) {

        Integer identityId = null;
        School school = (School) request.getSession().getAttribute("school");
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        TransManage transManage  = (TransManage) request.getSession().getAttribute("manage");
        if(school!=null){
            identityId = school.getSIdentityId();
        }else if(teacher!=null){
            identityId = teacher.getTIdentityId();
            System.out.println("teacher:=>>>>>"+teacher);
        }else if(transManage!=null){
            identityId = transManage.getTmIdentityId();
        }
        ArrayList<FrontMenu> tabList = frontMenuMapper.selectMenuList(identityId);
        List<FrontMenu> mList = new ArrayList<>();
        for (FrontMenu menu : tabList) {
            menu.setId(menu.getFrMenuId());
            menu.setTitle(menu.getFrMenuName());
            menu.setType("0");
            menu.setHref(menu.getFrUrl());
            if (menu.getFrParentId() != 0) {
                menu.setType("1");
                menu.setOpenType("_iframe");
            }
            mList.add(menu);
        }
        List menuItemList = TreeUtil.toTree(tabList, 0);
        return menuItemList;
    }
}
