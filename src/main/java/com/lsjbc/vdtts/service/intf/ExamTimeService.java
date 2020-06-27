package com.lsjbc.vdtts.service.intf;

import com.lsjbc.vdtts.entity.Student;
import com.lsjbc.vdtts.pojo.vo.ExamTimeNew;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: ExamTimeService
 * @Description:
 * @Datetime: 2020/6/14   23:55
 * @Author: JX181114 - 郑建辉
 */
public interface ExamTimeService {


    /*
     *@Description:查询学员学时记录
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/15 18:54
     **/
    LayuiTableData findStudentExamNotes(String page,String limit, HttpServletRequest request );

    /**
     * 更新学员的学时
     *
     * @param info 新学时信息
     * @return Ajax结构体
     * @author JX181114 --- 郑建辉
     */
    ResultData updateLearnTime(ExamTimeNew info);

    /**
     * 根据学员的ID来获取对应的教练车数量
     * 步骤
     * 先去获取学员所分配的教练ID
     * 再通过教练ID去获取教练车ID
     *
     * @param studentId 学员ID
     * @return 如果有教练车，会直接返回教练车数量，如果没有分配教练，会返回-1，如果所分配的教练没有分配教练车，返回-2
     * @author JX181114 --- 郑建辉
     */



    Integer getCarCountByStudentId(Integer studentId);
}
