package com.lsjbc.vdtts.redis;

import com.lsjbc.vdtts.entity.ExamQuestion;
import com.lsjbc.vdtts.redis.dao.ListDao;
import com.lsjbc.vdtts.redis.dao.RedisStudentDao;
import com.lsjbc.vdtts.redis.dao.StringDao;
import com.lsjbc.vdtts.redis.entity.RedisStudent;
import com.lsjbc.vdtts.service.BaseRedisClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: RedisTests
 * @Description:
 * @Datetime: 2020/6/18   1:40
 * @Author: JX181114 - 郑建辉
 */
@SpringBootTest
public class RedisTests {

    @Resource(name = BaseRedisClient.NAME)
    private BaseRedisClient redisClient;

    /**
     * Redis是一个缓存中间件
     * Redis的存储结构是Key-Value
     * 可以将数据库中的数据以一种合理的结构存入缓存中，以减少对数据库的访问次数，减少数据库的压力
     * Redis中的Value可以是Java中的任何数据
     *
     * 常用的Redis格式为String，list，Hash
     *
     *
     * Hash适合存储多个类型不相同的元素
     *      Key：
     *          [Field(属性名),Value],
     *          [Field(属性名),Value],
     *          [Field(属性名),Value],
     *          [Field(属性名),Value],
     *          [Field(属性名),Value]
     */

    /**
     * Redis中的键，具有目录层级结构，每一个目录之间用英文：来进行分割
     * 下面的目录结构为：
     *  test
     *      dir1
     *          dir11
     *          dir12
     *      dir2
     *          dir3
     */
    private static final String BASE_KEY = "test";
    private static final String DIR1 = BASE_KEY + ":dir1";
    private static final String DIR1_DIR1 = DIR1 + ":dir11";
    private static final String DIR1_DIR2 = DIR1 + ":dir12";
    private static final String DIR2 = BASE_KEY + ":dir2";
    private static final String DIR2_DIR3 = DIR2 + ":dir3";

    @Test
    public void contentTest() {
//        System.out.println(testString(1));
//        System.out.println(testList(1));
//        System.out.println(testMap(1));
    }

    /**
     * Redis基本数据格式：String
     * 适用于存放一些  元素单一的对象
     * @param id
     * @return
     */
    public String testString(Integer id){

        //这个对象保存在Redis中的键
        String keyString = BASE_KEY+":string:"+id;

        //优先从Redis中取值
        String str = (String) redisClient.get(keyString);

        //如果从redis中取的到值，直接返回
        if(str!=null){
            System.out.println("已从redis中取到数据");
            return str;
        }

        //如果取不到值，就从数据库中取
        str = StringDao.getStringById(id);

        //从数据库中取值，然后把值写入redis中，设置数据在redis中的保存时间(秒),如果时间设置为负数，则无限期保存
        redisClient.set(keyString,str,60*2);

        //返回值
        return str;
    }

    /**
     * Redis基本数据格式：List
     * 适合存储多个类型相同的元素
     * @param id
     * @return
     */
    public List<Integer> testList(Integer id){

        String keyList = BASE_KEY+":list:"+id;

        /**
         * 优先从redis中取值
         * list取出来的值默认为Object，后期需要转
         * 第二个参数和第三个参数分别为要获取的元素范围
         * 如果要查询全部元素，设置范围0到-1
         */
        List<Object> sourceList = redisClient.lGet(keyList,0,-1);

        //如果从redis中取的到值，并且长度不为0，直接返回
        if(sourceList!=null&&sourceList.size()>0){
            System.out.println("已从redis中取到数据");
            //将Object转换为IntegerList
            List<Integer> resultList = sourceList.stream().map(item->turnObjectToInteger(item)).collect(Collectors.toList());
            return resultList;
        }

        //去数据库中取值
        List<Integer> resultList = ListDao.getStringById(id);


        //把数据库中取到的数据一个一个存入redis中
        for(int index=0;index<resultList.size();index++){
            //设置数据在redis中的保存时间(秒),如果时间设置为负数，则无限期保存
            redisClient.lSet(keyList,resultList.get(index),60*2);
        }



        //从数据库中取值，然后把值写入redis中，设置数据在redis中的保存时间(秒),如果时间设置为负数，则无限期保存
        return resultList;
    }

    public RedisStudent testMap(Integer id){

        String keyMap = BASE_KEY+":map:"+id;

        RedisStudent student = null;

        //先从redis中读取数据
        try {
            //读取数据
            Integer sId = turnObjectToInteger(redisClient.hget(keyMap, "id"));
            String username = String.valueOf(redisClient.hget(keyMap, "username"));
            String password = String.valueOf(redisClient.hget(keyMap, "password"));

            //装填数据并实例化对象
            student = RedisStudent.builder().id(sId).username(username).password(password).build();
        } catch (Exception e) {
            //进入这里说明redis中没有找到数据
        }

        if(student!=null){
            System.out.println("已从redis中取到数据");
            return student;
        }

        //去数据库中取值
        student = RedisStudentDao.getStudentById(id);

        /**
         * 将值写入redis中
         * 三个参数：
         * 第一个：value的保存路径
         * 第二个：变量的别名
         * 第三个：变量值
         * 第四个(可选)：变量的保存时间
         */
        redisClient.hset(keyMap, "id",student.getId(),60*2);
        redisClient.hset(keyMap, "username",student.getUsername(),60*2);
        redisClient.hset(keyMap, "password",student.getPassword(),60*2);

        return student;
    }

    /**
     * 将Object对象转换为Integer对象
     * @param obj
     * @return
     */
    public Integer turnObjectToInteger(Object obj){
        return Integer.parseInt(String.valueOf(obj));
    }

}
