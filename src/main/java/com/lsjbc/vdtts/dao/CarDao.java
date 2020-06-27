package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.BaseDao;
import com.lsjbc.vdtts.dao.mapper.CarMapper;
import com.lsjbc.vdtts.entity.Car;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CarDao
 * @Description:
 * @Datetime: 2020/6/12   13:32
 * @Author: JX181114 - 郑建辉
 */
@Repository(CarDao.NAME)
public class CarDao implements BaseDao<Car> {

    /**
     * Bean名
     */
    public static final String NAME = "CarDao";

    @Resource
    private CarMapper mapper;

    /**
     * 获取指定驾校的总教练车辆数
     *
     * @param schoolId 驾校ID
     * @return 总教练车辆数
     * @author JX181114 --- 郑建辉
     */
    public Integer getCountBySchoolId(Integer schoolId) {
        Example example = new Example(Car.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cSchoolId", schoolId);

        return mapper.selectCountByExample(example);
    }

    /**
     * 获取教练的所有教练车辆
     *
     * @param teacherId 教练ID
     * @return 教练车集合
     * @author JX181114 --- 郑建辉
     */
    public List<Car> getCarByTeacherId(Integer teacherId) {
        Example example = new Example(Car.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cTeacherId", teacherId);

        return mapper.selectByExample(example);
    }

    /**
     * 通过主键来获取一个对象
     *
     * @param id 主键
     * @return 对象
     */
    @Override
    public Car getById(Integer id) {
        return null;
    }

    /**
     * 新增对象
     * 注意
     * 调用这个方法，会自动的向对象中注入主键
     * 所以返回的不是主键，而是受影响条数
     *
     * @param object 对象
     * @return 受影响条数
     */
    @Override
    public Integer add(Car object) {
        return mapper.addCar(object);
    }

    /**
     * 根据ID来更新对象
     * 注意
     * 传入的obj对象中主键不得为空
     * 否则会抛出异常
     *
     * @param object 对象
     * @return 受影响条数
     */
    @Override
    public Integer updateById(Car object) {
        return null;
    }

    /**
     * 根据主键来删除记录
     *
     * @param id 主键
     * @return 受影响条数
     */
    @Override
    public Integer deleteById(Integer id) {
        return null;
    }
}
