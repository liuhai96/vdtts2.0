package com.lsjbc.vdtts.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsjbc.vdtts.dao.EvaluateDao;
import com.lsjbc.vdtts.dao.mapper.EvaluateMapper;
import com.lsjbc.vdtts.entity.Evaluate;
import com.lsjbc.vdtts.service.intf.EvaluateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: EvaluateServiceImpl
 * @Description:
 * @Datetime: 2020/6/16   0:40
 * @Author: JX181114 - 郑建辉
 */
@Service(EvaluateServiceImpl.NAME)
public class EvaluateServiceImpl implements EvaluateService {

    public static final String NAME = "EvaluateService";

    @Resource(name = EvaluateDao.NAME)
    private EvaluateDao evaluateDao;

    @Resource
    private EvaluateMapper evaluateMapper;
    /**
     * 分页获取评价
     *
     * @param type 评价对象身份
     * @param id   评价对象ID
     * @param page 页数
     * @return 20个长度的评价列表
     * @author JX181114 --- 郑建辉
     */
    @Override
    public Page<Evaluate> getEvaluateByTypeAndId(String type, Integer id, Integer page) {

        Page<Evaluate> userPage = PageHelper.startPage(page, 20, true);

        evaluateDao.getByTypeAndId(type, id);


        return userPage;
    }


    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/17
     **/
    @Override
    public int schoolEvaluate(Evaluate evaluate) {
        int schoolEvaluate =evaluateMapper.schoolEvaluate(evaluate);
        return schoolEvaluate;
    }

    @Override
    public int teacherEvaluate(Evaluate evaluate) {
        int teacherEvaluate =evaluateMapper.teacherEvaluate(evaluate);
        return teacherEvaluate;
    }
}
