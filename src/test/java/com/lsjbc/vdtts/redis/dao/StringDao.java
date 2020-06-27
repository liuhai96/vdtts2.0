package com.lsjbc.vdtts.redis.dao;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: StringDao
 * @Description:
 * @Datetime: 2020/6/18   11:47
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@ToString
@Repository
public class StringDao {

    public StringDao(){
        stringMap.put(1,"这是第一个字符串");
        stringMap.put(2,"这是第二个字符串");
        stringMap.put(3,"这是第三个字符串");
        stringMap.put(4,"这是第四个字符串");
        stringMap.put(5,"这是第五个字符串");
    }

    public static Map<Integer,String> stringMap = new ConcurrentHashMap<>();

    public static String getStringById(Integer id){

        System.out.println("从数据库中根据ID来查询字符串");

        String str = stringMap.get(id);

        if(str!=null){
            return str;
        }

        throw new NullPointerException("无法根据ID找到字符串记录");
    }

}
