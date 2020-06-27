package com.lsjbc.vdtts.processor;

import com.alibaba.fastjson.JSONObject;
import com.lsjbc.vdtts.buffer.DataProcessor;
import com.lsjbc.vdtts.dao.mapper.LogMapper;
import com.lsjbc.vdtts.entity.TbLog;
import com.lsjbc.vdtts.utils.SpringContextHolder;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 操作日志数据处理器
 * @Author:
 * @Date: 2020/6/6 13:18
 */

public class OperationLogProcessor implements DataProcessor<String> {

    private final Logger logger = LoggerFactory.getLogger(OperationLogProcessor.class);

    private LogMapper operationLogMapper = SpringContextHolder.getBean(LogMapper.class);

    private ExecutorService executors = Executors.newFixedThreadPool(5);

    @Override
    public void process(List<String> data) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }
        executors.submit(() -> {
            logger.info("操作日志-存入数据:{}", data);
            try {
                data.forEach(d -> operationLogMapper.insert(JSONObject.parseObject(d, TbLog.class)));
            } catch (Throwable e) {
                logger.error("操作日志-存入数据失败", e);
            }

        });
    }
}
