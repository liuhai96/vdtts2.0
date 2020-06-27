package com.lsjbc.vdtts.api;

import com.github.pagehelper.Page;
import com.lsjbc.vdtts.entity.ExamSimulateRecord;
import com.lsjbc.vdtts.pojo.vo.ExamSimulateRecordAdd;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.pojo.vo.ResultData;
import com.lsjbc.vdtts.service.impl.ExamSimulateRecordServiceImpl;
import com.lsjbc.vdtts.service.intf.ExamSimulateRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName: ExamSimulateRecordApi
 * @Description: 关于模拟考试成绩的一些Api接口
 * @Datetime: 2020/6/8   10:19
 * @Author: JX181114 - 郑建辉
 */
@CrossOrigin
@RestController
@RequestMapping("api/exam")
public class ExamSimulateRecordApi {

    @Resource(name = ExamSimulateRecordServiceImpl.NAME)
    private ExamSimulateRecordService examSimulateRecordService;

    /**
     * 获取用户的模拟考试成绩
     *
     * @param studentId 学员ID
     * @param page 指定页数
     * @return 每页10条的用户模拟考试成绩
     * @author JX181114 --- 郑建辉
     */
    @GetMapping("record/{studentId}")
    public LayuiTableData getStudentSimulateScore(@PathVariable("studentId") Integer studentId, Integer level, Integer page, Integer limit) {
        LayuiTableData tableData = new LayuiTableData();

        try {
            Page<ExamSimulateRecord> pageResult = examSimulateRecordService.getRecord(studentId, level, page, limit);

            tableData.setCode(0);
            tableData.setCount(pageResult.getTotal());
            tableData.setData(pageResult.getResult());
        } catch (Exception e){
            tableData.setCode(-1);
            tableData.setMsg(e.getMessage());
        }

        return tableData;
    }

    /**
     * 用户新增模拟考试记录
     *
     * @param object 数据传输类
     * @return 操作结果
     * @author JX181114 --- 郑建辉
     */
    @PutMapping("record")
    public void newRecord(@Valid ExamSimulateRecordAdd object) {
        //根据返回的受影响条数来生成不同的返回值
        examSimulateRecordService.insertNewData(object);
    }
}
