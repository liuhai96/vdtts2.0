package com.lsjbc.vdtts.api;

import com.github.pagehelper.Page;
import com.lsjbc.vdtts.pojo.vo.LayuiPageData;
import com.lsjbc.vdtts.pojo.vo.SchoolDetail;
import com.lsjbc.vdtts.service.impl.SchoolServiceImpl;
import com.lsjbc.vdtts.service.intf.SchoolService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: SchoolApi
 * @Description:
 * @Datetime: 2020/6/12   15:09
 * @Author: JX181114 - 郑建辉
 */
@CrossOrigin
@RestController
@RequestMapping("api/school")
public class SchoolApi {

    @Resource(name = SchoolServiceImpl.NAME)
    private SchoolService schoolService;

    /**
     * 根据姓名分页查询驾校信息
     *
     * @param name 驾校名字
     * @param page 页数
     * @return 驾校信息集合
     */
    @GetMapping("info")
    public LayuiPageData<SchoolDetail> getSchoolDetails(String name, Integer page) {
        Page<SchoolDetail> pageInfo = schoolService.getSchoolDetailPageByName(name, page);

        LayuiPageData<SchoolDetail> pageData = new LayuiPageData<>();

        pageData.setCount(pageInfo.getTotal());

        pageData.setPages(pageInfo.getPages());

        pageData.setList(pageInfo.getResult());

        return pageData;
    }

}
