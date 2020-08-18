package com.jk.service;

import com.jk.model.CarEntity;
import com.jk.model.CarTypeEntity;
import com.jk.model.TreeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "tb-provider-two",fallback = UserServiceFallback.class)
public interface UserServiceFeign extends UserService{

    @RequestMapping("/selectCarList")
    List<CarEntity> findCarList();

    @RequestMapping("/findTreeList")
    List<TreeEntity> findTreeList();

    @RequestMapping("/deleteCar")
    void deleteCar(@RequestParam Integer id);

    @RequestMapping("/findCarType")
    List<CarTypeEntity> findCarType();

    @RequestMapping("/saveCar")
    void saveCar(CarEntity carEntity);

    @RequestMapping("/findCarById")
    CarEntity findCarById(@RequestParam Integer id);

    @RequestMapping("/updateCar")
    void updateCar(CarEntity carEntity);
}
