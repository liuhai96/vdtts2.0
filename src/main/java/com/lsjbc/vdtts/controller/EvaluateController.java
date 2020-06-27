package com.lsjbc.vdtts.controller;

import com.lsjbc.vdtts.entity.Evaluate;
import com.lsjbc.vdtts.service.intf.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiLang9725
 * @date 2020/6/14 15:29
 */
@RestController
@RequestMapping(value = "/evaluateController")
public class EvaluateController {
    @Autowired
    EvaluateService evaluateService;
    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/17 15860799877
     **/
    @RequestMapping(value = "/schoolEvaluate")
    public String schoolEvaluate(HttpServletRequest request, HttpServletResponse response,
            String examScore,String teacherScore,String palaceScore,String eContent,String eToId,String eStudentId){
            System.out.println(" examScore="+examScore+" teacherScore="+teacherScore+" palaceScore="+palaceScore
                    +" eContent="+eContent+" eToId="+eToId+" eStudentId="+eStudentId);
            int eScore =(Integer.valueOf(examScore) +Integer.valueOf(teacherScore) +Integer.valueOf(palaceScore));
            Float eScore1 =new Float(eScore*0.3);
            int eStudentId1 =Integer.valueOf(eStudentId);
            int eToId1 =Integer.valueOf(eToId);
            System.out.println("评价分数eScore1："+eScore1+" eStudentId1："+eStudentId1+" eToId："+eToId1+" eContent："+eContent);
            Evaluate evaluate = new Evaluate();
            evaluate.setEToId(eToId1);
            evaluate.setEStudentId(eStudentId1);
            evaluate.setEScore(eScore1);
            evaluate.setEContent(eContent);
            int i = evaluateService.schoolEvaluate(evaluate);
            if(i>0){
                System.out.println("驾校评论成功");
                return "success";
            }else {
                System.out.println("驾校评论失败");
                return "failed";
            }
    }
    @RequestMapping(value = "/teacherEvaluate")
    public String teacherEvaluate(HttpServletRequest request, HttpServletResponse response,
                                 String examScore,String teacherScore,String palaceScore,String eContent,String eToId,String eStudentId){
        System.out.println(" examScore="+examScore+" teacherScore="+teacherScore+" palaceScore="+palaceScore
                +" eContent="+eContent+" eToId="+eToId+" eStudentId="+eStudentId);
        int eScore =(Integer.valueOf(examScore) +Integer.valueOf(teacherScore) +Integer.valueOf(palaceScore));
        Float eScore1 =new Float(eScore*0.3);
        int eStudentId1 =Integer.valueOf(eStudentId);
        int eToId1 =Integer.valueOf(eToId);
        System.out.println("评价分数eScore1："+eScore1+" eStudentId1："+eStudentId1+" eToId："+eToId1+" eContent："+eContent);
        Evaluate evaluate = new Evaluate();
        evaluate.setEToId(eToId1);
        evaluate.setEStudentId(eStudentId1);
        evaluate.setEScore(eScore1);
        evaluate.setEContent(eContent);
        int i = evaluateService.teacherEvaluate(evaluate);
        if(i>0){
            System.out.println("教练评论成功");
            return "success";
        }else {
            System.out.println("教练评论失败");
            return "failed";
        }
    }
}
