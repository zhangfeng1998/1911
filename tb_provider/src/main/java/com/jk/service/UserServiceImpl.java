package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.BookBean;
import com.jk.model.CarEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public List<CarEntity> selectCarList() {
        return userMapper.selectCarList();
    }
}
