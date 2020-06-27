package com.lsjbc.vdtts.service.impl;

import com.lsjbc.vdtts.dao.LinkDao;
import com.lsjbc.vdtts.dao.mapper.LinkMapper;
import com.lsjbc.vdtts.entity.Link;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.intf.LinkService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LiLang9725
 * @date 2020/6/10 11:28
 */
@Service(LinkServiceImpl.NAME)
public class LinkServiceImpl implements LinkService {

    /**
     * Bean名
     */
    public static final String NAME = "LinkServive";

    @Resource
    private LinkMapper linkMapper;

    @Resource(name = LinkDao.NAME)
    private LinkDao linkDao;

    @Override
    /*
     *@Description:
     *@Author:李浪_191019
     *@Param:[link]
     *@return:java.util.List<com.lsjbc.vdtts.entity.Link>
     *@Date:2020/6/10 13:54
     **/
    public List<Link> drivingSchoolBlogroll(Link link) {//驾校友情链接
        return linkMapper.getBlogroll(link, 2, 10);
    }

    @Override
    public ModelAndView HomePageBlogroll(Link link, ModelAndView modelAndView) {
        //获取实名认证
        modelAndView.addObject("approve", linkMapper.getBlogroll(link, 0, 2));
        //获取友情链接
        modelAndView.addObject("blogroll", linkMapper.getBlogroll(link, 2, 10));
        return modelAndView;
    }

    /**
     * 获取页面底部的友情链接
     *
     * @return 友情链接集合
     * @author JX181114 --- 郑建辉
     */
    @Override
    public List<Link> getFooterFriendLink() {
        return linkDao.getAll();
    }
    @Override
    /*
     *@Description:加友情链接
     *@Author:李浪_191019
     *@Param:[link]
     *@return:com.lsjbc.vdtts.pojo.vo.ResultData
     *@Date:2020/6/17 0:49
     **/
    public ResultData addLink(Link link){
        ResultData resultData;
        if(linkMapper.addLink(link) > 0){
            resultData = ResultData.success("\n添加友情链接成功\n\n是否返回上一层！\n");
            resultData.setCode(1);
        } else {
            resultData = ResultData.success("\n添加失败，请检查你的网络！\n\n    是否返回上一层！\n");
            resultData.setCode(0);
        }
        return resultData;
    }
    @Override
    /*
     *@Description:查
     *@Author:李浪_191019
     *@Param:[link, page, limit]
     *@return:com.lsjbc.vdtts.pojo.vo.LayuiTableData
     *@Date:2020/6/17 0:49
     **/
    public LayuiTableData selectLink(Link link, int page, int limit){
        int start = (page - 1) * limit;//计算出起始查询位置
        if(start<0){
            start=0;
        }
        LayuiTableData layuiTableData = new LayuiTableData();
        List<Link> links = linkMapper.getBlogroll(link,start,limit);
        if(links.size() > 0){
            layuiTableData.setData(links);
            layuiTableData.setCode(0);
            layuiTableData.setCount(linkMapper.getCount(link));
        } else {
            layuiTableData.setCode(1);
        }
        return layuiTableData;
    }
    @Override
    /*
     *@Description:修改友情链接
     *@Author:李浪_191019
     *@Param:[link]
     *@return:com.lsjbc.vdtts.pojo.vo.ResultData
     *@Date:2020/6/17 0:49
     **/
    public ResultData Update(Link link){
        ResultData resultData;
        if(linkMapper.updateLink(link) > 0){
            resultData = ResultData.success("修改成功，是否需要返回？\n");
            resultData.setCode(1);
        } else {
            resultData = ResultData.success("修改失败，请检查你的网络\n\n     是否需要返回？\n");
            resultData.setCode(0);
        }
        return resultData;
    }
    @Override
    public ResultData Delete(Link link){
        ResultData resultData;
        if(linkMapper.deleteLink(link) > 0){
            resultData = ResultData.success("删除成功\n");
            resultData.setCode(1);
        } else {
            resultData = ResultData.success("删除失败，请检查你的网络！\n\n");
            resultData.setCode(0);
        }
        return resultData;
    }
}
