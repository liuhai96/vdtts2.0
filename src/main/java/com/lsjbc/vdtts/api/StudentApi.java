package com.lsjbc.vdtts.api;

import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.impl.StudentServiceImpl;
import com.lsjbc.vdtts.service.intf.StudentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: StudentApi
 * @Description:
 * @Datetime: 2020/6/21   22:39
 * @Author: JX181114 - 郑建辉
 */
@CrossOrigin
@RestController
@RequestMapping("api/student")
public class StudentApi {

    @Resource(name = StudentServiceImpl.NAME)
    private StudentService studentService;

    /**
     * 修改密码
     *
     * @param request Request域
     * @param phone   新的手机号
     * @param code    验证码
     * @return 操作结果
     */
    @PutMapping("phone")
    public ResultData updatePhone(HttpServletRequest request, String phone, String code) {
        return studentService.studentUpdatePhone(request, phone, code);
    }
}
