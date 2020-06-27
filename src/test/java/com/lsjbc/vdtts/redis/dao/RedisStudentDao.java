package com.lsjbc.vdtts.redis.dao;

import com.lsjbc.vdtts.redis.entity.RedisStudent;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: RedisStudentDao
 * @Description:
 * @Datetime: 2020/6/18   13:47
 * @Author: JX181114 - 郑建辉
 */
@Repository
public class RedisStudentDao {

    public RedisStudentDao(){
        studentMap.put(1,new RedisStudent(1,"111","111"));
        studentMap.put(1,new RedisStudent(2,"222","222"));
        studentMap.put(1,new RedisStudent(3,"333","333"));
        studentMap.put(1,new RedisStudent(4,"444","444"));
        studentMap.put(1,new RedisStudent(5,"555","555"));
    }


    public static Map<Integer, RedisStudent> studentMap = new ConcurrentHashMap<>();

    public static RedisStudent getStudentById(Integer id){
        System.out.println("从数据库中根据ID来查询字符串");

        RedisStudent str = studentMap.get(id);

        if(str!=null){
            return str;
        }

        throw new NullPointerException("无法根据ID找到字符串记录");
    }


}
