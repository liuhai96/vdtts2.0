package com.lsjbc.vdtts.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lsjbc.vdtts.aop.Log;
import com.lsjbc.vdtts.dao.CarDao;
import com.lsjbc.vdtts.entity.Car;
import com.lsjbc.vdtts.enums.OperateType;
import com.lsjbc.vdtts.enums.ResourceType;
import com.lsjbc.vdtts.pojo.vo.LayuiTableData;
import com.lsjbc.vdtts.service.intf.CarService;
import com.lsjbc.vdtts.service.intf.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/carControl")
public class CarControl {
    @Autowired
    private CarService carService;
    @Autowired
    private SchoolService schoolService;
    @Resource
    CarDao carDao;

    /*
     *@Description:查询驾校内教练车辆基本信息
     *@Author:刘海
     *@Param:
     *@return:
     *@Date:2020/6/8 23:22
     **/
    @RequestMapping(value = "/findCarList")
    @Log(operateType = OperateType.QUERY, resourceType = ResourceType.CAR)
    public String findCarList(String page, String limit, String cNumber, HttpServletRequest request) {

//        String tSchoolId = request.getParameter("tShoolId");//接收前端保存的驾校id

        return JSON.toJSONString(carService.findCarManageList(page, limit, cNumber, request), SerializerFeature.DisableCircularReferenceDetect);
    }


    /*
     *@Description:修改教练车的信息
     *@Author:刘海
     *@Param:[car]
     *@return:java.lang.String
     *@Date:2020/6/9 19:49
     **/
    @RequestMapping(value = "/updateCarInfo")
    @Log(operateType = OperateType.MODIFY, resourceType = ResourceType.CAR)
    public String updateCarInfo(Car car) {
        return JSON.toJSONString(carService.updateCarInfo(car));
    }

    /*
     *@Description:
     *@Author:刘海
     *@Param:[cId]
     *@return:java.lang.String
     *@Date:2020/6/9 20:48
     **/
    @RequestMapping(value = "/deleteCar")
    @Log(operateType = OperateType.DELETE, resourceType = ResourceType.CAR)//,detail = "#cId"
    public String deleteCar(int cId, HttpServletRequest request) {
        return JSON.toJSONString(carService.deleteCar(cId, request));
    }


    @RequestMapping(value = "/addCar")
    @Log(operateType = OperateType.ADD, resourceType = ResourceType.CAR)// detail = "#cId"
    public Object addCar(Car car, HttpServletRequest request) {
        return JSON.toJSONString(carService.addCar(car, request));
    }

    /*
     *@Description:
     *@Author:陈竑霖
     *@Param:
     *@return:
     *@Date:2020/6/9 1591683706914
     **/
//车辆表
    @RequestMapping(value = "/carList", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String carList(HttpServletRequest request, HttpServletResponse response, Car car) {
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String draw = request.getParameter("draw");//重绘次数 和前台对应

        LayuiTableData layuiTableData = carService.carList(car, Integer.parseInt(pageStr), Integer.parseInt(pageSizeStr));
        return JSON.toJSONString(layuiTableData);
    }

    /*
     *@Description:
     *@Author:周永哲
     *@Param:
     *@return:
     *@Date:2020/6/10 15860799877
     **/
    @RequestMapping(value = "/selectCarInfo")//初始化教练车信息表
    public String selectCarInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit,
                                Car car) {
        int page2 = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
        System.out.println(" ---carpage=" + page2);
        List<Car> list = carService.selectAllInfo(car, page2, Integer.valueOf(limit));
        int count = carService.selectCarCount(car);
        System.out.println("教练车信息初始化操作--- list=" + list + " count =" + count);
        LayuiTableData layuiData = new LayuiTableData();
        layuiData.setCode(0);
        layuiData.setData(list);
        layuiData.setCount(count);
        return JSON.toJSONString(layuiData);
    }

    @RequestMapping(value = "/insertCar")//添加教练车
    public String insertCar(HttpServletRequest request, HttpServletResponse response, Car car) {
        System.out.println(" 添加教练车 car=" + car.toString());
        int i = carDao.add(car);
        if (i > 0) {
            System.out.println("添加驾校成功");
            return "success";
        } else {
            System.out.println("添加驾校失败");
            return "failed";
        }
    }

}
