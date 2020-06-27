package com.lsjbc.vdtts.api;

import com.github.pagehelper.Page;
import com.lsjbc.vdtts.pojo.vo.LayuiPageData;
import com.lsjbc.vdtts.pojo.vo.TeacherDetail;
import com.lsjbc.vdtts.service.impl.TeacherServiceImpl;
import com.lsjbc.vdtts.service.intf.TeacherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: TeacherApi
 * @Description:
 * @Datetime: 2020/6/15   20:46
 * @Author: JX181114 - 郑建辉
 */
@CrossOrigin
@RestController
@RequestMapping("api/teacher")
public class TeacherApi {

    @Resource(name = TeacherServiceImpl.NAME)
    private TeacherService teacherService;

    /**
     * 根据姓名和性别来查询教练信息
     *
     * @param name 姓名
     * @param sex  性别
     * @param page 页数
     * @return 教练信息集合
     */
    @GetMapping("info")
    public LayuiPageData<TeacherDetail> getTeacherDetails(String name, String sex, Integer page) {

        Page<TeacherDetail> pageInfo = teacherService.getTeacherDetailPageByNameAndSex(name, sex, page);

        LayuiPageData<TeacherDetail> pageData = new LayuiPageData<>();

        pageData.setCount(pageInfo.getTotal());

        pageData.setPages(pageInfo.getPages());

        pageData.setList(pageInfo.getResult());

        return pageData;
    }
}
