package com.jk.service;

import com.jk.model.CarEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface UserService {

    @RequestMapping("/findCarList")
    List<CarEntity> findCarList();

    @RequestMapping("/shello")
    String shello(@RequestParam  String name);

    @RequestMapping("/save")
    Object save(@RequestParam Integer userId, @RequestParam Integer productId);
}
