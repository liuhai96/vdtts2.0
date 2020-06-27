package com.lsjbc.vdtts.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsjbc.vdtts.dao.mapper.LogMapper;
import com.lsjbc.vdtts.entity.TbLog;
import com.lsjbc.vdtts.enums.OperateType;
import com.lsjbc.vdtts.enums.ResourceType;
import com.lsjbc.vdtts.utils.ElParser;
import com.lsjbc.vdtts.utils.NetUtil;
import com.lsjbc.vdtts.utils.SpringContextHolder;
import com.lsjbc.vdtts.utils.Tool;
import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @Description: 操作aop
 * @Author:
 * @Date: 2020/6/5 18:40
 */

@Aspect
@Component
public class LogAop {
    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Autowired
    private LogMapper logMapper;
    @Pointcut("@annotation(com.lsjbc.vdtts.aop.Log)")
    public void entranceAspect() {
        logger.info("操作日志-进入切面");
    }

    @AfterReturning("entranceAspect()")
    public void setActivity(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        logger.info("操作日志-触发切面方法，参数:{}", Arrays.toString(args));
        try {
            TbLog operationLog = buildOperationLog(joinPoint);
            logger.info("插入日志{}", operationLog);
            logMapper.insertSelective(operationLog);
//            SpringContextHolder.getBean()
//            SpringContextHolder.getBean(Buffer.class).put(JSON.toJSONString(operationLog));
        } catch (Throwable e) {
            logger.warn("操作日志-异常", e);
        }

    }

    private TbLog buildOperationLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attributes.getRequest().getSession();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();

        //解析注解
        ElParser parser = ElParser.getInstance();

        if (argNames != null && args != null && argNames.length == args.length) {
            for (int i = 0; i < argNames.length; i++) {
                parser.setVar(argNames[i], args[i]);
            }
        }

        //获取注解属性
        Log log = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Log.class);
        OperateType operateType = log.operateType();
        ResourceType resourceType = log.resourceType();
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append(operateType.getName());
        contentBuilder.append(resourceType.getName());
        Tool tool = new Tool();
        if (StringUtils.isNotEmpty(log.detail())) {

            String resourceId = parser.eval(log.detail(), String.class);
            CustomBaseMapper mapper = SpringContextHolder.getBean(resourceType.getMapper());
            JSONObject data = (JSONObject) JSON.toJSON(mapper.selectByPrimaryKey(resourceId));
            String name = data.getString("name");
            if (StringUtils.isNotEmpty(name)) {
                contentBuilder.append("(").append(name).append(")");
            }
        }
        return TbLog.builder().actionType(operateType.toString()).content(contentBuilder.toString())
                .createTime(tool.getDate("yyyy-MM-dd HH:mm:ss"))
                .ipAddress(NetUtil.getHostAddress())
                .operatorNo(String.valueOf(session.getAttribute("account"))).operatorName(String.valueOf(session.getAttribute("name"))).build();
    }
}
