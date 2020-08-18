package com.jk.controller;


import com.jk.model.CarEntity;
import com.jk.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController implements UserServiceFeign {

    @Resource
    private UserService userService;



    @RequestMapping("/selectCarList")
    public List<CarEntity> selectCarList() {

        return userService.selectCarList();
    }

}
