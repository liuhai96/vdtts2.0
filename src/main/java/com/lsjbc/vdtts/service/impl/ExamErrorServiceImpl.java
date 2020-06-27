package com.lsjbc.vdtts.service.impl;

import com.lsjbc.vdtts.dao.ExamAnswerDao;
import com.lsjbc.vdtts.dao.ExamErrorDao;
import com.lsjbc.vdtts.dao.ExamQuestionDao;
import com.lsjbc.vdtts.entity.ExamError;
import com.lsjbc.vdtts.entity.ExamQuestion;
import com.lsjbc.vdtts.pojo.vo.ExamQuestionWithEeId;
import com.lsjbc.vdtts.service.intf.ExamErrorService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ExamErrorServiceImpl
 * @Description:
 * @Datetime: 2020/6/9   15:24
 * @Author: JX181114 - 郑建辉
 */
@Service("ExamErrorService")
public class ExamErrorServiceImpl implements ExamErrorService {

    /**
     * Bean名
     */
    public static final String NAME = "ExamErrorService";

    @Resource(name = ExamErrorDao.NAME)
    private ExamErrorDao examErrorDao;

    @Resource(name = ExamQuestionDao.NAME)
    private ExamQuestionDao examQuestionDao;

    @Resource(name = ExamAnswerDao.NAME)
    private ExamAnswerDao examAnswerDao;

    /**
     * 根据学员ID，来查找错题集合
     *
     * @param level     科目等级
     * @param studentId 学员ID
     * @return 错题集合
     * @author JX181114 --- 郑建辉
     */
    @Override
    public List<ExamQuestionWithEeId> getErrorQuestionByStudentId(Integer level, Integer studentId) {
        //根据ID查询出所有的错题ID
        List<ExamError> errorQuestion = examErrorDao.getByStudentId(studentId,level);

        List<ExamQuestionWithEeId> questionList = new ArrayList<>();

        for (Integer index = 0; index < errorQuestion.size(); index++) {
            ExamQuestion question = examQuestionDao.getById(errorQuestion.get(index).getEeQuestionId());

            //根据错题ID查询出错题答案
            question.setAnswers(examAnswerDao.getByQuestionId(errorQuestion.get(index).getEeQuestionId()));

            questionList.add(new ExamQuestionWithEeId(errorQuestion.get(index), question));
        }

        return questionList;
    }

    /**
     * 根据错题ID来删除记录
     *
     * @param id 错题ID
     * @param level 所属科目
     * @return 受影响条数
     * @author JX181114 --- 郑建辉
     */
    @Override
    @Async
    public Integer deleteErrorQuestionByRecordId(Integer id,Integer level) {
        return examErrorDao.deleteByIdAndLevel(id,level);
    }
}
