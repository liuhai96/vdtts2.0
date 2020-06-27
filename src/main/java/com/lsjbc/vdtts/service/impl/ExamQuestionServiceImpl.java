package com.lsjbc.vdtts.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsjbc.vdtts.dao.ExamAnswerDao;
import com.lsjbc.vdtts.dao.ExamErrorDao;
import com.lsjbc.vdtts.dao.ExamQuestionDao;
import com.lsjbc.vdtts.dao.mapper.ExamQuestionMapper;
import com.lsjbc.vdtts.entity.ExamAnswer;
import com.lsjbc.vdtts.entity.ExamQuestion;
import com.lsjbc.vdtts.interceptor.ExamEnity;
import com.lsjbc.vdtts.pojo.dto.PageDTO;
import com.lsjbc.vdtts.pojo.dto.QuestionBank;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.intf.ExamQuestionService;
import com.lsjbc.vdtts.service.intf.ExamTestService;
import com.lsjbc.vdtts.utils.GetExamQuestion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {

    @Resource
    private ExamQuestionMapper examQuestionMapper;

    @Resource(name = ExamQuestionDao.NAME)
    private ExamQuestionDao examQuestionDao;

    @Resource(name = ExamAnswerDao.NAME)
    private ExamAnswerDao examAnswerDao;

    @Resource(name = ExamErrorDao.NAME)
    private ExamErrorDao examErrorDao;

    @Resource(name = QuestionBank.NAME)
    private QuestionBank questionBank;

    @Resource(name = ExamTestServiceImpl.NAME)
    private ExamTestService examTestService;

    @Override
    public ResultData insertExamQuestion(String level) {
        ResultData resultData = null;
        if(level.equals("0")){
            resultData = ResultData.error(-1,"请选择需要更新的科目");
        }else {
            List<ExamEnity> examEnitityList = GetExamQuestion.getRequest1(level);
            examQuestionDao.deleteAll(Integer.parseInt(level));
            examAnswerDao.deleteAll(Integer.parseInt(level));
            examErrorDao.deleteByLevel(Integer.parseInt(level));

            // todo 考虑分片
//        List<ExamQuestion> examQuestionList = examEnitityList.stream().map(examEnity -> {
//            return ExamQuestion.builder()
//                    .eqPic(examEnity.getUrl())
//                    .eqQuestion(examEnity.getQuestion())
//                    .eqLevel(Integer.parseInt(level))
//                    .build();
//        }).collect(Collectors.toList());
//        examQuestionDao.addAll(examQuestionList);
//        List<ExamAnswer> examAnswerList = examQuestionList.stream().flatMap(e -> {
//            ExamEnity enity = examEnitityList.stream().filter(q -> StringUtils.equals(e.getEqQuestion(), q.getQuestion())
//                    && StringUtils.equals(e.getEqPic(), q.getUrl())).collect(Collectors.toList()).get(0);
//            return enity.generateAnswer(e.getEqId()).stream();
//        }).collect(Collectors.toList());
//        examAnswerDao.addAll(examAnswerList);
            for(int i=0;i<examEnitityList.size();i++) {
                ExamEnity examEnity = examEnitityList.get(i);

                //插入题目
                ExamQuestion examQuestion = ExamQuestion.builder()
                        .eqQuestion(examEnity.getQuestion())
                        .eqLevel(Integer.parseInt(level))
                        .build();
                if (examEnity.getUrl() != "") {
                    examQuestion.setEqPic(examEnity.getUrl());
                }
                int num =  examQuestionDao.add(examQuestion);

                List<ExamAnswer> answers = examEnity.generateAnswer(examQuestion);

                int num1 =examAnswerDao.addAll(answers);
                if(num1>0&&num>0){
                    resultData = ResultData.success(1,"题库更新成功");
                }
            }

        }
        //重新生成对应科目的考卷
        questionBank.resetBank(Integer.parseInt(level));
        return resultData;
    }

    @Override
    public PageInfo findExamQuestion(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit(), true);
        return new PageInfo<>(examQuestionMapper.selectAll());
    }


    @Override
    public ResultData findAnswer(String questionId){
        ResultData resultData = null;
        List answerList = examAnswerDao.getByQuestionId(Integer.parseInt(questionId));
        resultData = ResultData.success("查询成功","answerList",answerList);
        return resultData;
    }


}

