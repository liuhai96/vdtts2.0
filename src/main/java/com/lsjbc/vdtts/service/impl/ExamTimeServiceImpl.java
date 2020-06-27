package com.lsjbc.vdtts.service.impl;

import com.lsjbc.vdtts.dao.CarDao;
import com.lsjbc.vdtts.dao.ExamResultDao;
import com.lsjbc.vdtts.dao.ExamTimeDao;
import com.lsjbc.vdtts.dao.StudentDao;
import com.lsjbc.vdtts.dao.mapper.ExamTimeMapper;
import com.lsjbc.vdtts.entity.Car;
import com.lsjbc.vdtts.entity.ExamTime;
import com.lsjbc.vdtts.entity.Student;
import com.lsjbc.vdtts.pojo.vo.ExamTimeNew;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.intf.ExamTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ExamTimeServiceImpl
 * @Description:
 * @Datetime: 2020/6/14   23:57
 * @Author: JX181114 - 郑建辉
 */
@Service(ExamTimeServiceImpl.NAME)
public class ExamTimeServiceImpl implements ExamTimeService {

    /**
     * Bean名
     */
    public static final String NAME = "ExamTimeService";

    @Resource(name = ExamResultDao.NAME)
    private ExamResultDao examResultDao;

    @Resource(name = ExamTimeDao.NAME)
    private ExamTimeDao examTimeDao;

    @Resource(name = StudentDao.NAME)
    private StudentDao studentDao;

    @Resource(name = CarDao.NAME)
    private CarDao carDao;

    @Autowired
    private ExamTimeMapper examTimeMapper;

    /*
     *@Description:查看学时记录
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/15 18:35
     **/
    @Override
    public LayuiTableData findStudentExamNotes(String page, String limit, HttpServletRequest request ) {
        LayuiTableData layuiTableData = new LayuiTableData();
        Student student = (Student) request.getSession().getAttribute("student");
        int pageSize = Integer.parseInt(limit);
        int start = (Integer.parseInt(page)-1)*pageSize;//计算从数据库第几条开始查
        ArrayList<ExamTime> examTimeArrayList = examTimeMapper.findStudentExamNotes(student.getSId(),start,pageSize);
        int count = examTimeMapper.findStudentExamNotesCount(student.getSId());
        layuiTableData.setCount(count);
        layuiTableData.setData(examTimeArrayList);
        return layuiTableData;
    }


    /**
     * 更新学员的学时
     *
     * @param info 新学时信息
     * @return Ajax结构体
     * @author JX181114 --- 郑建辉
     */
    @Override
    public ResultData updateLearnTime(ExamTimeNew info) {

        ResultData rd = new ResultData();

        Integer carCount = getCarCountByStudentId(info.getStudentId());

        Boolean half = false;

        //检测学时是否减半
        switch (carCount) {
            case -1:
                rd.appendMessage("您未分配教练，时长减半，请联系驾校为你分配教练", "-");
                half = true;
                break;
            case -2:
                rd.appendMessage("您分配的教练尚未拥有教练车，时长减半", "-");
                half = true;
                break;
            default:
                break;
        }

        //检测是否允许学习
        Boolean effective = examResultDao.allowLearn(info.getStudentId(), info.getLevel());

        ExamTime examTime = info.generateBean(half, effective);

        //新增学时记录
        examTimeDao.add(examTime);

        if (effective) {
            //如果是有效学时
            //就更新学时总时长
            examResultDao.updateTimeByStudentId(info);
        } else {
            //如果是无效学时，不操作，返回信息给前端
            rd.appendMessage("未满足学习资格，或已通过考试，此时视为无效学时", "-");
        }


        return rd;
    }


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
    @Override
    public Integer getCarCountByStudentId(Integer studentId) {

        Integer teacherId = studentDao.getTeacherIdByStudentId(studentId);

        if (teacherId == null) {
            return -1;
        }

        List<Car> carList = carDao.getCarByTeacherId(teacherId);

        if (carList == null || carList.size() == 0) {
            return -2;
        }

        return carList.size();
    }
}
