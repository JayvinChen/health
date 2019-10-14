package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Order;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.util.Map;

/*
 * @author Jayvin
 * @date 2019/10/7 10:31
 */
@RequestMapping("/order")
@RestController
public class OrderController {
    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;

    /** 
     * @Description: 提交预约
     * @Param: [map] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/submit")
    public Result submit(@RequestBody Map<String,String> map) {
        String telephone = map.get("telephone");
        Jedis jedis = jedisPool.getResource();
        String key = "Order_" + RedisMessageConstant.SENDTYPE_ORDER + "_" + telephone;
        String validateCodeInRedis = jedis.get(key);
        // 先验证是否有不符合情况，保证后面代码的有效性
        if (StringUtils.isEmpty(validateCodeInRedis)) {
            // 代表验证码过时或验证码为空
            return new Result(false,"请获取验证码");
        }
        if (!validateCodeInRedis.equals(map.get("validateCode"))) {
            // 验证码输入错误 441701199502140044
            return new Result(false,MessageConstant.VALIDATECODE_ERROR);
        }
        // 清除Redis的验证码，防止复用
        jedis.del("validateCode");

        Order order = orderService.submitOrder(map);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order);
    }

    /**
     * @Description: 预约成功，数据回显
     * @Param: [id]
     * @return: com.itheima.entity.Result
     */
    @PostMapping("/findById")
    public Result findOrderById(Integer id){
        Map<String,Object> orderInfo = orderService.findOrderById(id);
        return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,orderInfo);
    }
}











