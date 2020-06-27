package com.lsjbc.vdtts.service.impl;

import com.lsjbc.vdtts.dao.ExamErrorDao;
import com.lsjbc.vdtts.service.intf.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: AsyncServiceImpl
 * @Description: 异步Service层的实现类
 * @Datetime: 2020/6/26   10:19
 * @Author: JX181114 - 郑建辉
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    @Resource(name = ExamErrorDao.NAME)
    private ExamErrorDao examErrorDao;

    /**
     * 预读用户的错题
     *
     * @param studentId 学员ID
     */
    @Override
    @Async
    public void readExamError(Integer studentId) {
        examErrorDao.getByStudentId(studentId, 1);
        examErrorDao.getByStudentId(studentId, 4);
    }

}
