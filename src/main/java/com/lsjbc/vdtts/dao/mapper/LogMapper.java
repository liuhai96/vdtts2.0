package com.lsjbc.vdtts.dao.mapper;

import com.lsjbc.vdtts.entity.TbLog;
import com.lsjbc.vdtts.pojo.dto.PageDTO;
import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper extends CustomBaseMapper<TbLog> {
    /**
     * 批量插入
     * @param operationLogs 待插入的集合
     * @return 成功条数
     */
    int batchInsert(@Param("operationLogs") List<TbLog> operationLogs);

    /**
     * 搜索
     * @param dateRangeDTO 时间范围条件
     * @return 操作日志集合
     */
    List<TbLog> selectByDateRange(PageDTO dateRangeDTO);
}