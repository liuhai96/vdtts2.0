package com.lsjbc.vdtts.service.intf;

import com.github.pagehelper.Page;
import com.lsjbc.vdtts.entity.ExamSimulateRecord;
import com.lsjbc.vdtts.pojo.vo.ExamSimulateRecordAdd;

/**
 * @ClassName: ExamSimulateRecordService
 * @Description: 模拟考试成绩的Service层
 * @Datetime: 2020/6/8   10:59
 * @Author: JX181114 - 郑建辉
 */
public interface ExamSimulateRecordService {

    /**
     * 根据VO对象，来生成一个新的模拟考试成绩记录
     *
     * @param object VO对象
     * @return 受影响的条数
     * @author JX181114 --- 郑建辉
     */
    Integer insertNewData(ExamSimulateRecordAdd object);

    /**
     * 以学员ID，为搜索条件进行分页搜索模拟考试成绩
     *
     * @param studentId 学员ID
     * @param level     科目等级
     * @param page      要查询的分页页数
     * @param limit     要查询的单页数量
     * @return 分页信息
     * @author JX181114 --- 郑建辉
     */
    Page<ExamSimulateRecord> getRecord(Integer studentId, Integer level, Integer page, Integer limit);
}
