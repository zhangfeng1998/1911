package com.jk.mapper;

import com.jk.model.CarEntity;
import com.jk.model.CarTypeEntity;
import com.jk.model.TreeEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select c.*,tct.name as typeName from t_car c left join t_car_type tct on c.typeId = tct.id")
    List<CarEntity> selectCarList();

    @Select("select id,text,url as path,pid from t_tree where pid = #{value}")
    List<TreeEntity> findTreeChildren(Integer id);

    @Delete("delete from t_car where id = #{value}")
    void deleteCar(Integer id);

    @Select("select * from t_car_type")
    List<CarTypeEntity> findCarType();

    @Insert("insert into t_car(name,price,typeId,color,createTime,vendor) values(#{name},#{price},#{typeId},#{color},#{createTime},#{vendor})")
    void saveCar(CarEntity carEntity);

    @Select("select * from t_car where id = #{value}")
    CarEntity findCarById(Integer id);

    @Update("update t_car set name = #{name},price = #{price},typeId = #{typeId},color = #{color},createTime = #{createTime},vendor = #{vendor} where id = #{id}")
    void updateCar(CarEntity carEntity);
}
