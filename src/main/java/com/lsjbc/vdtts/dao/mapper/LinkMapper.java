package com.lsjbc.vdtts.dao.mapper;

import com.lsjbc.vdtts.entity.Link;
import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LiLang9725
 * @date 2020/6/10 11:16
 */

public interface LinkMapper extends CustomBaseMapper<Link> {
    public List<Link> getBlogroll(@Param("e") Link link, @Param("start")int start, @Param("size")int size);
    int addLink(@Param("e")Link link);
    int getCount(@Param("e") Link link);
    int updateLink(@Param("e") Link link);
    int deleteLink(@Param("e") Link link);
}
