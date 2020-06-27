package com.lsjbc.vdtts.dao.mapper;

import com.lsjbc.vdtts.entity.FrontMenu;

import java.util.ArrayList;

public interface FrontMenuMapper {
    public ArrayList<FrontMenu> selectMenuList(Integer identityId);
}
