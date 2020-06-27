package com.lsjbc.vdtts.service.impl;

import com.lsjbc.vdtts.dao.mapper.CarMapper;
import com.lsjbc.vdtts.entity.Car;
import com.lsjbc.vdtts.entity.School;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.service.intf.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service(CarServiceImpl.NAME)
@Transactional
public class CarServiceImpl implements CarService {

    /**
     * Bean名
     */
    public static final String NAME = "CarService";

    @Resource
    private CarMapper carMapper;

    /*
     *@Description:查询教练车
     *@Author:刘海
     *@Param:[start, pageSize, cSchoolId]
     *@return:com.lsjbc.vdtts.pojo.vo.LayuiTableData
     *@Date:2020/6/9 14:35
     **/
    @Override
    public LayuiTableData findCarManageList(String page, String limit,String cNumber, HttpServletRequest request) {
        LayuiTableData layuiTableData = new LayuiTableData();
        System.out.println("page"+page);
        System.out.println("page"+limit);
        School school = (School) request.getSession().getAttribute("school");
        int pageSize = Integer.parseInt(limit);
        int start = (Integer.parseInt(page)-1)*pageSize;//计算从数据库第几条开始查
        int carCount = carMapper.findCarCount(cNumber,school.getSId());
        ArrayList<Car> carList = carMapper.findCarManageList(start,pageSize,cNumber,school.getSId());
        System.out.println("carList"+carList);
        layuiTableData.setCount(carCount);
        layuiTableData.setCode(0);
        layuiTableData.setData(carList);
        return layuiTableData;
    }

    @Override
    public LayuiTableData updateCarInfo(Car car) {
        LayuiTableData layuiTableData = new LayuiTableData();
        if(car.getCTeacherId()==0){
            layuiTableData.setCode(0);
        }else{
            int num = carMapper.updateCarInfo(car);
            if(num>0){
                layuiTableData.setCode(1);
            }else{
                layuiTableData.setCode(2);
            }

        }
        return layuiTableData;
    }

    @Override
    public LayuiTableData deleteCar(int cId, HttpServletRequest request) {
        School school = (School) request.getSession().getAttribute("school");
        LayuiTableData layuiTableData = new LayuiTableData();
        int num = carMapper.deleteCar(cId);
        int num1 = carMapper.updateCarInfomatian(school.getSId(),cId);
        if(num>0){
            layuiTableData.setCode(1);
        }else{
            layuiTableData.setCode(0);
        }
        return layuiTableData;
    }

    @Override
    public LayuiTableData addCar(Car car,HttpServletRequest request) {
        School school = (School) request.getSession().getAttribute("school");
        car.setCSchoolId(school.getSId());
        LayuiTableData layuiTableData = new LayuiTableData();
        int num = carMapper.addCar(car);
        if(num>0){
            layuiTableData.setCode(1);
        }
        return layuiTableData;
    }

    /*
     *@Description:
     *@Author:陈竑霖
     *@Param:
     *@return:
     *@Date:2020/6/8 1591587038161
     **/
    @Override
    public LayuiTableData carList(Car car, int page, int pageSize) {
        int start = (page - 1) * pageSize;//计算出起始查询位置
        if(start<0){
            start=0;
        }
        List<Car> list = carMapper.finecarlist(car, start, pageSize);
        int count = carMapper.carlistcount(car);

        LayuiTableData layuiTableData = new LayuiTableData();
        if (list.size() > 0) {
            layuiTableData.setCode(0);
            layuiTableData.setMsg("");
            layuiTableData.setCount(count);
            layuiTableData.setData(list);
            System.out.println(car);
        } else {
            layuiTableData.setCode(1);
            layuiTableData.setMsg("查询失败");
        }
        return layuiTableData;
    }


    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/11
     **/
    @Override
    public List<Car> selectAllInfo(Car car, int page, int limit) {
        List<Car> selectAllInfo = carMapper.selectAllInfo(car,page,limit);
        return selectAllInfo;
    }

    @Override
    public int selectCarCount(Car car) {
        int selectCount = carMapper.selectCarCount(car);
        return selectCount;
    }
}
