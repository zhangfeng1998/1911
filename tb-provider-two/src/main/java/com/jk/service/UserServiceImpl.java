package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.CarEntity;
import com.jk.model.CarTypeEntity;
import com.jk.model.TreeEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;


    @RequestMapping("/findCarList")
    public List<CarEntity> findCarList() {

        return userMapper.selectCarList();
    }

    @Override
    @RequestMapping("/shello")
    public String shello(@RequestParam  String name) {
        return "名字是:"+name;
    }

    @Override
    @RequestMapping("/save")
    public Object save(@RequestParam Integer userId, @RequestParam Integer productId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("orderId", 2);
        map.put("userId", userId);
        map.put("productId", productId);
        map.put("orderNo", "20200815103622123");
        map.put("orderName", "沐浴露");
        map.put("orderPrice", 59);
        map.put("createTime", "2020-08-16 20:39");
        return map;
    }


    @RequestMapping("/findTreeList")
    public List<TreeEntity> findTreeList() {
        Integer id = 0;

        return findNodes(id);
    }


    @RequestMapping("/deleteCar")
    public void deleteCar(Integer id) {
        userMapper.deleteCar(id);
    }


    @RequestMapping("/findCarType")
    public List<CarTypeEntity> findCarType() {
        return userMapper.findCarType();
    }


    @RequestMapping("/saveCar")
    public void saveCar(@RequestBody CarEntity carEntity) {
        userMapper.saveCar(carEntity);
    }


    @RequestMapping("/findCarById")
    public CarEntity findCarById(Integer id) {
        return userMapper.findCarById(id);
    }


    @RequestMapping("/updateCar")
    public void updateCar(@RequestBody  CarEntity carEntity) {
        userMapper.updateCar(carEntity);
    }

    private List<TreeEntity> findNodes(Integer id){
        List<TreeEntity> treeBeans = userMapper.findTreeChildren(id);
        for (TreeEntity treeBean : treeBeans) {
            List<TreeEntity> findNodes = findNodes(treeBean.getId());
            if (findNodes == null || findNodes.size() <=0) {
                treeBean.setSelectable("true");
            }else{
                treeBean.setNodes(findNodes);
            }
        }
        return treeBeans;
    }

}
