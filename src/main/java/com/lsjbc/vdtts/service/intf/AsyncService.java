package com.lsjbc.vdtts.service.intf;

/**
 * @ClassName: AsyncService
 * @Description: 异步Service层
 * @Datetime: 2020/6/26   10:18
 * @Author: JX181114 - 郑建辉
 */
public interface AsyncService {

    /**
     * 预读用户的错题
     *
     * @param studentId 学员ID
     */
    void readExamError(Integer studentId);
}
