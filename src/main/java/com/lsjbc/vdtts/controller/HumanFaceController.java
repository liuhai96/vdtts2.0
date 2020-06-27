package com.lsjbc.vdtts.controller;

import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.impl.StudentServiceImpl;
import com.lsjbc.vdtts.utils.baidu.baiduTools.face.SearchFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**人脸识别控制层
 * @author LiLang9725
 * @date 2020/6/21 15:38
 */
@Controller
@RequestMapping
public class HumanFaceController {
    @Autowired
    private StudentServiceImpl studentService;

    @RequestMapping("/addFace")
    /*
     *@Description:加人脸到第三方数据库
     *@Author:李浪_191019
     *@Param:[base64, sId]//传参 图片转base64,学生id
     *@return:java.lang.String
     *@Date:2020/6/22 20:57
     **/
    public ModelAndView AddFace(String base64, int sId){
        ModelAndView modelAndView = new ModelAndView();
        ResultData resultData = studentService.AddFace(base64,sId);
        modelAndView.addObject("result2", resultData);
        modelAndView.setViewName("/pages/student/add-student-face");
        return modelAndView;
    }


    @Autowired
    private SearchFace searchFace;
    @RequestMapping(value = "/lookFace")
    /*
     *@Description://人脸识别登录
     *@Author:李浪_191019
     *@Param:[base64]
     *@return:java.lang.String
     *@Date:2020/6/22 21:57
     **/
    public String lookAtTheFace(String base64, HttpServletRequest request){
        request.getSession().setAttribute("result",studentService.FaceLogin(request,base64));
        try {
            if (request.getSession().getAttribute("account").toString() != null){
                return  "redirect:/student/main"; //redirect:重定向到index的后台
            } else {
                return  "/pages/index/student_login"; //redirect:重定向到学生登录的后台
            }
        } catch (Exception e){
            return  "/pages/index/student_login";//redirect:重定向到学生登录的后台
        }
    }

}
