package com.lsjbc.vdtts.api;

import com.lsjbc.vdtts.dao.ExamResultDao;
import com.lsjbc.vdtts.entity.ExamResult;
import com.lsjbc.vdtts.utils.CustomTimeUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: ExamResultApi
 * @Description:
 * @Datetime: 2020/6/23   20:50
 * @Author: JX181114 - 郑建辉
 */
@CrossOrigin
@RestController
@RequestMapping("api/exam")
public class ExamResultApi {

    @Resource(name = ExamResultDao.NAME)
    private ExamResultDao resultDao;

    /**
     * 获取考试结果和学时记录
     *
     * @param studentId
     * @return 考试结果和学时记录
     * @author JX181114 --- 郑建辉
     */
    @GetMapping("result/{sid}")
    public ExamResult getUserExamResult(@PathVariable("sid") Integer studentId) {
        ExamResult result = resultDao.getByStudentId(studentId);
        result.setErTime2String(CustomTimeUtils.turnSecondsToString(result.getErTime2()));
        result.setErTime3String(CustomTimeUtils.turnSecondsToString(result.getErTime3()));
        return result;
    }
}
