package com.lsjbc.vdtts.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsjbc.vdtts.constant.NoticeType;
import com.lsjbc.vdtts.dao.NoticeDao;
import com.lsjbc.vdtts.dao.mapper.NoticeMapper;
import com.lsjbc.vdtts.entity.Notice;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.service.intf.NoticeService;
import com.lsjbc.vdtts.utils.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiLang9725
 * @date 2020/6/10 20:51
 */
@Service(NoticeServiceImpl.NAME)
public class NoticeServiceImpl implements NoticeService {

    /**
     * Bean名
     */
    public static final String NAME = "NoticeSerivice";


    @Resource
    private NoticeMapper noticeMapper;

    @Resource(name = NoticeDao.NAME)
    private NoticeDao noticeDao;

    @Override
    /*
     *@Description:
     *@Author:李浪_191019
     *@Param:[notice, modelAndView]
     *@return:org.springframework.web.servlet.ModelAndView
     *@Date:2020/6/10 21:07 首页提取公告个法律法律信息
     **/
    public ModelAndView HomePageNoticeOrLaw(Notice notice, ModelAndView modelAndView) {
        Tool tool = new Tool();

        notice.setNType("law");//法律
        modelAndView.addObject("law", noticeMapper.getNoticeOrLaw(notice, null));
        int month = Integer.parseInt(tool.getDate("MM")) - 1;
        String n_time = "";
        /*只公告前一个月内的通知*/
        if (month == 0) { //当前时间1月份，取上一年12的通知
            month = 12;
            n_time = (Integer.parseInt(tool.getDate("yyyy")) - 1) + "/"
                    + month + "/" + tool.getDate("dd HH:mm:ss");
        } else if (month < 10)//当前时间2~10月
            n_time = tool.getDate("yyyy") + "/0" + month + "/" + tool.getDate("dd HH:mm:ss");
        else n_time = tool.getDate("yyyy") + "/" + month + "/" + tool.getDate("dd HH:mm:ss");//当前时间11~12月
        notice.setNTime(n_time);
        notice.setNType("notice");//通知
        modelAndView.addObject("notice", noticeMapper.getNoticeOrLaw(notice, tool.getDate("yyyy/MM/dd HH:mm:ss")));

        return modelAndView;
    }

    /**
     * 获取首页显示的通知公告
     *
     * @return 通知公告集合
     * @author JX181114 --- 郑建辉
     */
    @Override
    public List<Notice> getIndexPageNotice() {
        return getByType(NoticeType.TYPE_NOTICE, 1, 4, "");
    }

    /**
     * 获取首页显示的法律法规
     *
     * @return 法律法规集合
     * @author JX181114 --- 郑建辉
     */
    @Override
    public List<Notice> getIndexPageLaw() {
        return getByType(NoticeType.TYPE_LAW, 1, 4, "");
    }

    /**
     * 分页获取特定类型的法律法规/通知公告
     *
     * @param type 类型
     * @param page 页数
     * @return 法律法规/通知公告集合
     * @author JX181114 --- 郑建辉
     */
    @Override
    public List<Notice> getByType(String type, Integer page) {
        return getByType(type, page, 5, "");
    }

    /**
     * 分页获取特定类型的法律法规/通知公告
     *
     * @param type 类型
     * @param page 页数
     * @param name 模糊搜索
     * @return 法律法规/通知公告集合的分页对象
     * @author JX181114 --- 郑建辉
     */
    @Override
    public Page<Notice> getPageByType(String type, Integer page, String name) {
        Page<Notice> noticePage = PageHelper.startPage(page, 5, true);
        noticeDao.getNoticeByTypeOrderByIdDesc(type, name);
        return noticePage;
    }

    /**
     * 通过ID来获取通知公告/法律法规
     *
     * @param id 主键
     * @return 通知公告/法律法规
     * @author JX181114 --- 郑建辉
     */
    @Override
    public Notice getById(Integer id) {
        return noticeDao.getById(id);
    }

    /**
     * 分页获取特定类型的法律法规/通知公告
     *
     * @param type     类型
     * @param page     页数
     * @param pageSize 页面数据大小
     * @param name     模糊搜索
     * @return 法律法规/通知公告集合
     * @author JX181114 --- 郑建辉
     */
    private List<Notice> getByType(String type, Integer page, Integer pageSize, String name) {
        Page<Notice> noticePage = PageHelper.startPage(page, pageSize, true);
        noticeDao.getNoticeByTypeOrderByIdDesc(type, name);
        return noticePage.getResult();
    }

    /*
     *@Description:公告表查询
     *@Author:陈竑霖
     *@Param:
     *@return:
     *@Date:2020/6/8 1591587038161
     **/
    @Override
    public LayuiTableData noticeList(Notice notice, int page, int pageSize) {
        int start = (page - 1) * pageSize;//计算出起始查询位置
        if(start<0){
            start=0;
        }
        List<Notice> list = noticeMapper.noticeList(notice, start, pageSize);
        int count = noticeMapper.noticeListCount(notice);
        LayuiTableData layuiData = new LayuiTableData();
        if (list.size() > 0) {
            layuiData.setCode(0);
            layuiData.setMsg("");
            layuiData.setCount(count);
            layuiData.setData(list);
            System.out.println(notice);
        } else {
            layuiData.setCode(1);
            layuiData.setMsg("查询失败");
        }
        return layuiData;
    }
//删除公告
    @Override
    public LayuiTableData deletenotice(int nId) {
        LayuiTableData layuiTableData = new LayuiTableData();
        int num = noticeMapper.deletenotice(nId);
        if(num>0){
            layuiTableData.setCode(1);
        }else{
            layuiTableData.setCode(0);
        }
        return layuiTableData;
    }
    //新增公告
    @Override
    public LayuiTableData addnotice(Notice notice) {
        LayuiTableData layuiTableData = new LayuiTableData();
        int num = noticeMapper.addnotice(notice);
        if(num>0){
            layuiTableData.setCode(1);
        }
        return layuiTableData;
    }
}
