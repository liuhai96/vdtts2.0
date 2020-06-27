package com.lsjbc.vdtts.pojo.vo;

import com.lsjbc.vdtts.entity.ExamError;
import com.lsjbc.vdtts.entity.ExamSimulateRecord;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ExamSimulateRecordAdd
 * @Description: 前端向后端提交新的模拟考试成绩时提交的对象
 * @Datetime: 2020/6/8   10:37
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ExamSimulateRecordAdd {

    /**
     * 前端传入的学员ID
     */
    @Min(value = 0, message = "学员ID不能小于1")
    private Integer studentId;

    /**
     * 前端传入的模拟考试分数
     */
    @Min(value = -1, message = "模拟考试成绩不得小于0")
    @Max(value = 101, message = "模拟考试成绩不得大于100")
    private Integer score;

    /**
     * 前端传入的科目等级
     */
    private Integer level;

    /**
     * 错题ID集合
     */
    private Integer[] errorQuestions;

    /**
     * 根据现有数据返回ExamError错题集合
     *
     * @param record 考试记录(主键不得为空的考试记录)
     * @return 错题集合
     * @author JX181114 --- 郑建辉
     */
    public List<ExamError> createErrorList(ExamSimulateRecord record) {

        //如果传上来的分数为满分或错题表为空，则表示没有错题
        if(score==100|errorQuestions==null||errorQuestions.length==0){
            return new ArrayList<>();
        }


        List<ExamError> examErrors = new ArrayList<>(0);

        //遍历错题ID，生成错题对象
        try {
            for(int index = 0;index<errorQuestions.length;index++){
                examErrors.add(ExamError.builder().eeStudentId(record.getEsrStudentId()).eeLevel(this.level).eeQuestionId(errorQuestions[index]).build());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ArrayList<>(0);
        }
        return examErrors;
    }

    /**
     * 根据现有对象，创建出一个ExamSimulateRecord对象
     *
     * @return ExamSimulateRecord对象
     * @author JX181114 --- 郑建辉
     */
    public ExamSimulateRecord createRecord() {
        return ExamSimulateRecord.builder().esrLevel(this.level).esrStudentId(this.studentId).esrScore(this.score).build();
    }
}
