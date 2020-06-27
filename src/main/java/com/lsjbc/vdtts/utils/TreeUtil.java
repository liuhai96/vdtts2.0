package com.lsjbc.vdtts.utils;


import com.lsjbc.vdtts.entity.FrontMenu;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    public static List<FrontMenu> toTree(List<FrontMenu> treeList, int pid) {
        List<FrontMenu> retList = new ArrayList<FrontMenu>();
        for (FrontMenu parent : treeList) {
            if (pid == parent.getFrParentId()) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }

    private static FrontMenu findChildren(FrontMenu parent, List<FrontMenu> treeList) {
        for (FrontMenu child : treeList) {
            if (parent.getFrMenuId()==child.getFrParentId()) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(findChildren(child, treeList));
            }
        }
        return parent;
    }

}
