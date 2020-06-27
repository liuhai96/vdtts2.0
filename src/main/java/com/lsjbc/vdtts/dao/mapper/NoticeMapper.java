package com.lsjbc.vdtts.dao.mapper;

import com.lsjbc.vdtts.entity.Notice;
import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LiLang9725
 * @date 2020/6/10 20:00
 */
public interface NoticeMapper extends CustomBaseMapper<Notice> {
    public List<Notice> getNoticeOrLaw(@Param("e") Notice notice, @Param("currentTime") String currentTime);


    /*
     *@Description:公告表查询
     *@Author:陈竑霖
     *@Param:
     *@return:
     *@Date:2020/6/8 1591604044696
     **/
    List<Notice> noticeList(@Param("e") Notice notice, @Param("start") int start, @Param("pageSize") int pageSize);

    int noticeListCount(@Param("e") Notice notice);
    //删除公告
    public int deletenotice(@Param("nId") int nId);
    //新增公告
    public int addnotice(Notice notice);
}
