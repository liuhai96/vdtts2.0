package com.lsjbc.vdtts.service.intf;

import com.github.pagehelper.Page;
import com.lsjbc.vdtts.entity.Evaluate;
import org.springframework.stereotype.Service;

/**
 * @ClassName: EvaluateService
 * @Description:
 * @Datetime: 2020/6/16   0:38
 * @Author: JX181114 - 郑建辉
 */
@Service
public interface EvaluateService {

    /**
     * 分页获取评价
     *
     * @param type 评价对象身份
     * @param id   评价对象ID
     * @param page 页数
     * @return 20个长度的评价列表
     * @author JX181114 --- 郑建辉
     */
    Page<Evaluate> getEvaluateByTypeAndId(String type, Integer id, Integer page);

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
