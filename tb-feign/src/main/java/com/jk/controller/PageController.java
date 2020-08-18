package com.jk.controller;


import com.jk.model.CarEntity;
import com.jk.model.CarTypeEntity;
import com.jk.service.UserServiceFeign;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PageController {

    @Resource
    private UserServiceFeign userServiceFeign;

    @RequestMapping("/toMain")
    public  String toMain(){
         return "main";
     }

    @RequestMapping("/findCar")
    public  String findCar(){
        return "car";
    }

    @RequestMapping("/toAddCar")
    public  String toAddCar(){
        return "addCar";
    }

   /* @RequestMapping("/editCar")
    public String editCar(Integer id, Model model){
        List<CarTypeEntity> carType = userServiceFeign.findCarType();
        model.addAttribute("carType",carType);
        CarEntity carEntity  = userServiceFeign.findCarById(id);
        model.addAttribute("carEntity",carEntity);
        return "editcar";
    }*/
}
