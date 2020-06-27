package com.lsjbc.vdtts.redis.dao;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ListDao
 * @Description:
 * @Datetime: 2020/6/18   13:14
 * @Author: JX181114 - 郑建辉
 */
@Getter
@Setter
@ToString
@Component
public class ListDao {

    public ListDao(){
        listMap.put(1,Arrays.asList(1,2,3,4,5));
        listMap.put(2,Arrays.asList(6,7,8,9,10));
        listMap.put(3,Arrays.asList(11,12,13,14,15));
        listMap.put(4,Arrays.asList(16,17,18,19,20));
        listMap.put(5,Arrays.asList(21,22,23,24,25));
    }

    public static Map<Integer,List<Integer>> listMap = new ConcurrentHashMap<>();

    public static List<Integer> getStringById(Integer id){

        System.out.println("从数据库中根据ID来查询集合");

        List<Integer> list = listMap.get(id);

        if(list!=null){
            return list;
        }

        throw new NullPointerException("无法根据ID找到字符串记录");
    }


}
