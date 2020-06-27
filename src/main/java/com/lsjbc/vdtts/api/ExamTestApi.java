package com.lsjbc.vdtts.api;

import com.lsjbc.vdtts.pojo.dto.QuestionBank;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: ExamTestApi
 * @Description: 关于模拟考试的一些Ajax接口
 * @Datetime: 2020/6/7   9:54
 * @Author: JX181114 - 郑建辉
 */

/**
 * 解决跨域问题，仅在测试时使用
 */
@CrossOrigin
@RestController
@RequestMapping("api/exam")
public class ExamTestApi {

    @Resource(name = QuestionBank.NAME)
    private QuestionBank questionBank;

    /**
     * 前端用户获取特定科目的题库
     *
     * @param level 科目等级
     * @return 结果
     * @author JX181114 --- 郑建辉
     */
    @GetMapping("text")
    public ResultData getText(Integer level) {
        if (level == null || (level != 1 && level != 4)) {
            return ResultData.error(-1, "科目代码有误，请重新输入");
        }
        return ResultData.success("question", questionBank.getRandomQuestion(level));
    }
}
