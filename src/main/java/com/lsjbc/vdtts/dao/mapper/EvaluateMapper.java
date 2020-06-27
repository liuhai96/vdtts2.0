package com.lsjbc.vdtts.dao.mapper;

import com.lsjbc.vdtts.entity.Evaluate;
import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: EvaluateMapper
 * @Description: 评价表(单表操作Mapper)
 * @Datetime: 2020/6/12   11:27
 * @Author: JX181114 - 郑建辉
 */
public interface EvaluateMapper extends CustomBaseMapper<Evaluate> {
    public List<Evaluate> selectEvaluate(@Param("e")Evaluate evaluate);//查看评价 李浪

    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/17
     **/
    public int schoolEvaluate (Evaluate evaluate);
    public int teacherEvaluate (Evaluate evaluate);
}
