package com.lsjbc.vdtts.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: BaseDao
 * @Description: Dao层的基本方法
 * @Datetime: 2020/6/6   16:54
 * @Author: JX181114 - 郑建辉
 */
public interface BaseDao<T> {

    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     */
    T getById(Integer id);

    /**
     * 新增对象
     * 注意
     * 调用这个方法，会自动的向对象中注入主键
     * 所以返回的不是主键，而是受影响条数
     *
     * @param object 对象
     * @return 受影响条数
     */
    Integer add(T object);

    /**
     * 根据ID来更新对象
     * 注意
     * 传入的obj对象中主键不得为空
     * 否则会抛出异常
     *
     * @param object 对象
     * @return 受影响条数
     */
    Integer updateById(T object);

    /**
     * 根据主键来删除记录
     *
     * @param id 主键
     * @return 受影响条数
     */
    Integer deleteById(Integer id);
}
