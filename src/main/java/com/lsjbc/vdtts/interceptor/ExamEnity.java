package com.lsjbc.vdtts.interceptor;

import com.lsjbc.vdtts.entity.ExamAnswer;
import com.lsjbc.vdtts.entity.ExamQuestion;
import lombok.*;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamEnity {
    private String id;
    private String question;
    private String answer;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String explains;
    private String url;


    public List<ExamAnswer> generateAnswer(ExamQuestion examQuestion){

        ExamAnswer aExamAnswer = ExamAnswer.builder().eaAnswer(item1).eaQuestionId(examQuestion.getEqId()).eaRight("false").eaLevel(examQuestion.getEqLevel()).build();
        ExamAnswer bExamAnswer = ExamAnswer.builder().eaAnswer(item2).eaQuestionId(examQuestion.getEqId()).eaRight("false").eaLevel(examQuestion.getEqLevel()).build();
        ExamAnswer cExamAnswer = ExamAnswer.builder().eaAnswer(item3).eaQuestionId(examQuestion.getEqId()).eaRight("false").eaLevel(examQuestion.getEqLevel()).build();
        ExamAnswer dExamAnswer = ExamAnswer.builder().eaAnswer(item4).eaQuestionId(examQuestion.getEqId()).eaRight("false").eaLevel(examQuestion.getEqLevel()).build();

        switch (answer){
            case "1":
                aExamAnswer.setEaRight("true");
                break;
            case "2":
                bExamAnswer.setEaRight("true");
                break;
            case "3":
                cExamAnswer.setEaRight("true");
                break;
            case "4":
                dExamAnswer.setEaRight("true");
                break;
            case "7":
               aExamAnswer.setEaRight("true");
               bExamAnswer.setEaRight("true");
                break;
            case "8":
                aExamAnswer.setEaRight("true");
                cExamAnswer.setEaRight("true");
                break;
            case "9":
                aExamAnswer.setEaRight("true");
                dExamAnswer.setEaRight("true");
                break;
            case "10":
                bExamAnswer.setEaRight("true");
                cExamAnswer.setEaRight("true");
                break;
            case "11":
                bExamAnswer.setEaRight("true");
                dExamAnswer.setEaRight("true");
                break;
            case "12":
                cExamAnswer.setEaRight("true");
                dExamAnswer.setEaRight("true");
                break;
            case "13":
                aExamAnswer.setEaRight("true");
                bExamAnswer.setEaRight("true");
                cExamAnswer.setEaRight("true");
                break;
            case "14":
                aExamAnswer.setEaRight("true");
                bExamAnswer.setEaRight("true");
                dExamAnswer.setEaRight("true");
                break;
            case "15":
                aExamAnswer.setEaRight("true");
                cExamAnswer.setEaRight("true");
                dExamAnswer.setEaRight("true");
                break;
            case "16":
                bExamAnswer.setEaRight("true");
                cExamAnswer.setEaRight("true");
                dExamAnswer.setEaRight("true");
                break;
            case "17":
                aExamAnswer.setEaRight("true");
                bExamAnswer.setEaRight("true");
                cExamAnswer.setEaRight("true");
                dExamAnswer.setEaRight("true");
                break;
            default:
        }

        //如果是判断题
        if(StringUtils.isEmpty(item3)&&StringUtils.isEmpty(item4)) {
            return Arrays.asList(aExamAnswer,bExamAnswer);
        }


        return Arrays.asList(aExamAnswer,bExamAnswer,cExamAnswer,dExamAnswer);
    }
}
