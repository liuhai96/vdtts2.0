package com.lsjbc.vdtts.enums;

import com.lsjbc.vdtts.dao.mapper.*;
import com.lsjbc.vdtts.utils.mopper.CustomBaseMapper;

/**
 * @Description: 资源类型
 * @Author:
 * @Date: 2020/6/5 18:22
 */
public enum ResourceType {
    OPERATION_LOG("操作日志", LogMapper.class),
    CAR("教练车", CarMapper.class),
    School("驾校", SchoolMapper.class),
    Teacher("教练", TeacherMapper.class),
    ExamResult("考试情况",ExamResultMapper.class),
    Student("学员",StudentMapper.class);
    private String name;
    private Class<? extends CustomBaseMapper> mapper;

    public String getName() {
        return name;
    }

    public Class<? extends CustomBaseMapper> getMapper() {
        return mapper;
    }

    ResourceType(String name, Class<? extends CustomBaseMapper> mapper) {
        this.name = name;
        this.mapper = mapper;
    }

    public static ResourceType getTarget(String value) {
        ResourceType[] resourceTypes = ResourceType.values();
        ResourceType target = null;
        for (ResourceType resourceType : resourceTypes) {
            if (value.equals(resourceType.getName())) {
                target = resourceType;
                break;
            }
        }
        return target;
    }
}
