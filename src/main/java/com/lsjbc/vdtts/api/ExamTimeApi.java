package com.lsjbc.vdtts.api;

import com.lsjbc.vdtts.pojo.vo.ExamTimeNew;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.impl.ExamTimeServiceImpl;
import com.lsjbc.vdtts.service.intf.ExamTimeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: ExamTimeApi
 * @Description:
 * @Datetime: 2020/6/14   20:44
 * @Author: JX181114 - 郑建辉
 */
@CrossOrigin
@RestController
@RequestMapping("api/exam")
public class ExamTimeApi {

    @Resource(name = ExamTimeServiceImpl.NAME)
    private ExamTimeService examTimeService;

    /**
     * 更新学时
     *
     * @param info 新的学时信息
     * @return 结果
     */
    @PutMapping("time")
    public ResultData addTimeRecord(ExamTimeNew info) {
        try {
            return examTimeService.updateLearnTime(info);
        } catch (Exception e) {
            return ResultData.error(e.getMessage());
        }
    }
}
