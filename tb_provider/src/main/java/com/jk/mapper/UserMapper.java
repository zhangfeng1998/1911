package com.jk.mapper;

import com.jk.model.CarEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select c.*,tct.name as typeName from t_car c left join t_car_type tct on c.typeId = tct.id")
    List<CarEntity> selectCarList();
}
