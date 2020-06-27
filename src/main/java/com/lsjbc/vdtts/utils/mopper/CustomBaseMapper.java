package com.lsjbc.vdtts.utils.mopper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @ClassName: CustomBaseMapper
 * @Description: 这是一个通用Mapper接口，通用Mapper接口只支持单表查询
 * 这个接口不能被Spring的IOC容器扫描到
 * 这个接口不能被Spring的IOC容器扫描到
 * 这个接口不能被Spring的IOC容器扫描到
 * 多表查询请不要继承这个接口
 * 多表查询请不要继承这个接口
 * 多表查询请不要继承这个接口
 * @Datetime: 2020/6/6   13:02
 * @Author: JX181114 - 郑建辉
 */
public interface CustomBaseMapper<T> extends Mapper, MySqlMapper<T> {

    /**
     * 如果要使用通用Mapper
     * 需要在实体类上加上几个注解
     * 在类名上方使用注解：  @Table(name = "`xxxxx`")   -----xxxxx是数据库要查询的表名
     * 在表示主键的成员变量上使用注解：   @Id   和   @GeneratedValue(strategy = GenerationType.IDENTITY)    ----标明这个变量在数据库中是主键
     * 在数据库中存在的字段上使用注解：   @Column(name = "`xxxxx`")   -----xxxxx是变量在数据库中对应的字段名
     * 在数据库中不存在的字段上使用注解：  @Transient    -----表示数据库中没有这个字段(不加会抛出异常)
     *
     * 通用Mapper中 xxxxByExample(Example example)的简单语法如下
     * Example example = new Example(要操作的类名.class);
     *
     * 查询指定字段：example.selectProperties("xx1","xx2","xx3","xx5"..........);
     * xx1,xx2,xx3,xx4,xx5是成员变量名，不是表中字段名
     * xx1,xx2,xx3,xx4,xx5是成员变量名，不是表中字段名
     * xx1,xx2,xx3,xx4,xx5是成员变量名，不是表中字段名
     *
     * 排序
     * example.orderBy("xxx").desc();    ---按照某个成员变量降序排序
     * example.orderBy("xxx").asc();    ---按照某个成员变量升序排序
     * xxx是成员变量名，不是表中字段名
     * xxx是成员变量名，不是表中字段名
     * xxx是成员变量名，不是表中字段名
     *
     * 条件查询：
     * 第一步：Example.Criteria criteria = example.createCriteria();
     * 第二步：调用方法(这里以and条件举例，如果是or条件也是一样的orXXXXXXXXX("xxx", value))
     * criteria.andEqualTo("xxx", value);    ---xxx变量值==value
     * criteria.andGreaterThanOrEqualTo("xxx", value)    ---xxx变量值>=value
     * criteria.andGreaterThan("xxx", value)    ---xxx变量值>value
     * criteria.andLessThanOrEqualTo("xxx", value)    ---xxx变量值<=value
     * criteria.andLessThan("xxx", value)    ---xxx变量值<value
     * xxx是成员变量名，不是表中字段名
     * xxx是成员变量名，不是表中字段名
     * xxx是成员变量名，不是表中字段名
     *
     * 具体接口的使用方法见：https://mapperhelper.github.io/all/
     * 接口使用范例见：https://www.jianshu.com/p/336c71c68a52
     */


}
