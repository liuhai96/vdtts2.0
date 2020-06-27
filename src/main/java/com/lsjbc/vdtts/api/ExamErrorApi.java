package com.lsjbc.vdtts.api;

import com.lsjbc.vdtts.pojo.vo.ExamQuestionWithEeId;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.impl.ExamErrorServiceImpl;
import com.lsjbc.vdtts.service.intf.ExamErrorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: ExamErrorApi
 * @Description: 关于模拟考试错题的一些接口
 * @Datetime: 2020/6/9   14:35
 * @Author: JX181114 - 郑建辉
 */
@CrossOrigin
@RestController
@RequestMapping("api/exam")
public class ExamErrorApi {

    @Resource(name = ExamErrorServiceImpl.NAME)
    private ExamErrorService examErrorService;

    /**
     * 根据模拟考试ID，来获取错题集合
     *
     * @param studentId 模拟考试Id
     * @return 错题集合
     * @author JX181114 --- 郑建辉
     */
    @GetMapping("error/{level}/{studentId}")
    public ResultData getErrorQuestion(@PathVariable("level") Integer level, @PathVariable("studentId") Integer studentId) {

        ResultData resultData = null;

        try {
            List<ExamQuestionWithEeId> questionList = examErrorService.getErrorQuestionByStudentId(level, studentId);

            resultData = ResultData.success("questions", questionList);

        } catch (Exception e) {
            e.printStackTrace();
            resultData = ResultData.error(e.getMessage());
        }

        return resultData;
    }


    /**
     * 重新做错题，并正确之后，向数据库发起删除指定错题的申请
     *
     * @param id 错题记录ID
     * @return 删除结果
     * @author JX181114 --- 郑建辉
     */
    @DeleteMapping("error")
    public void deleteErrorQuestion(Integer id,Integer level) {
        examErrorService.deleteErrorQuestionByRecordId(id,level);
    }
}
