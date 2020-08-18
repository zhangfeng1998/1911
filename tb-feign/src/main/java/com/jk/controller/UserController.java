package com.jk.controller;
import com.jk.model.CarEntity;
import com.jk.model.CarTypeEntity;
import com.jk.model.TreeEntity;
import com.jk.service.UserServiceFeign;
import com.jk.utils.Constant;
import com.jk.utils.RedisUtil;
import com.jk.utils.StringUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserServiceFeign userServiceFeign;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/saveOrder")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object saveOrder(Integer userId, Integer productId, HttpServletRequest request){
        return userServiceFeign.save(userId, productId);
    }
    //注意，方法签名一定要要和api方法一致
    public Object saveOrderFail(Integer userId, Integer productId,HttpServletRequest request){

        System.out.println("controller,保存订单降级方法");

        String value =(String) redisUtil.get(Constant.SAVE_ORDER_WARNING_KEY);

        String ipAddr = request.getRemoteAddr();

        //新启动一个线程进行业务逻辑处理
        new Thread( ()->{
            if(StringUtil.isNotEmpty(value)) {
                System.out.println("紧急短信，用户下单失败，请离开查找原因,ip地址是="+ipAddr);

                //发送一个http请求，调用短信服务 TODO
                // 写发送短信代码，带有参数发送 userId  productId

                redisUtil.set(Constant.SAVE_ORDER_WARNING_KEY, "用户保存订单失败", 1);
            }else{
                System.out.println("已经发送过短信，1分钟内不重复发送");
            }
        }).start();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("code",-1);
        map.put("message","抢购人数太多，您被挤出来了，稍等重试");
        return map;

    }


    @RequestMapping("/hello")
    public String hello(String name){

        return userServiceFeign.shello(name);
    }

    @RequestMapping("/selectCarList")
    public List<CarEntity> selectCarList(){
        List<CarEntity> carEntities = (List<CarEntity>)redisUtil.get(Constant.SELECT_USER_LIST);

        if (carEntities==null||carEntities.size()<=0||carEntities.isEmpty()){

            carEntities = userServiceFeign.findCarList();

            redisUtil.set(Constant.SELECT_USER_LIST,carEntities,30);
        }
        return carEntities;
    }

    @RequestMapping("/findTree")
    public List<TreeEntity> findTreeList(){
        return userServiceFeign.findTreeList();
    }

    @RequestMapping("/deleteCar")
    public void deleteCar(Integer id){
        String cacheKey = Constant.SELECT_USER_LIST;
        redisUtil.del(cacheKey);
        userServiceFeign.deleteCar(id);
    }

    @RequestMapping("/findCarType")
    public List<CarTypeEntity> findCarType(){

        return userServiceFeign.findCarType();
    }

    @RequestMapping("/saveCar")
    public Boolean saveCar(CarEntity carEntity){
        try {
            String cacheKey = Constant.SELECT_USER_LIST;
            redisUtil.del(cacheKey);
            userServiceFeign.saveCar(carEntity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping("/updateCar")
    public Boolean updateCar(CarEntity carEntity){
        try {
            String cacheKey = Constant.SELECT_USER_LIST;
            redisUtil.del(cacheKey);
            userServiceFeign.updateCar(carEntity);
            return true;
        }catch (Exception e){



            return false;
        }
    }



}
