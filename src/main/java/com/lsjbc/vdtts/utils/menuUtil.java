package com.lsjbc.vdtts.utils;


import com.lsjbc.vdtts.entity.FrontMenu;
import com.lsjbc.vdtts.entity.Menuitem;

import java.util.ArrayList;
import java.util.List;

public class menuUtil {

    public static List<Menuitem> toTree(List<Menuitem> treeList, int pid) {
        List<Menuitem> retList = new ArrayList<Menuitem>();
        for (Menuitem parent : treeList) {
            if (pid == parent.getMParentId()) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }

    private static Menuitem findChildren(Menuitem parent, List<Menuitem> treeList) {
        for (Menuitem child : treeList) {
            if (parent.getMId()==child.getMParentId()) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(findChildren(child, treeList));
            }
        }
        return parent;
    }

}
