package com.jk.service;

import com.jk.model.CarEntity;
import com.jk.model.CarTypeEntity;
import com.jk.model.TreeEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping("/error")
public class UserServiceFallback implements UserServiceFeign{


    @Override
    public List<CarEntity> findCarList() {

        System.out.println("熔断处理,方法查询汽车集合");

        return null;
    }

    @Override
    public List<TreeEntity> findTreeList() {
        return null;
    }

    @Override
    public void deleteCar(Integer id) {

    }

    @Override
    public List<CarTypeEntity> findCarType() {
        return null;
    }

    @Override
    public void saveCar(CarEntity carEntity) {

    }

    @Override
    public CarEntity findCarById(Integer id) {
        return null;
    }

    @Override
    public void updateCar(CarEntity carEntity) {

    }

    @Override
    public String shello(String name) {

        System.out.println("进入了 hello方法 熔断器");

        return "请求失败 ,请检查网络";
    }

    @Override
    public Object save(Integer userId, Integer productId) {

        System.out.println("进入了 保存订单 熔断器类");

        return null;
    }
}
