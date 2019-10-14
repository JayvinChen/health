package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.exception.MyException;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/*
 * @author Jayvin
 * @date 2019/10/7 9:13
 */
@RequestMapping("/validateCode")
@RestController
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    /** 
     * @Description: 预约所需的验证码 
     * @Param: [telephone] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/send4Order")
    public Result send4Order(String telephone){
        Jedis jedis = jedisPool.getResource();
        String key = "Order_" + RedisMessageConstant.SENDTYPE_ORDER + "_" + telephone;

        // 判断是否发过验证码，Redis有值则发过
        if (null != jedis.get(key)) {
            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        }

        // 生成验证码
        String appointmentCode = ValidateCodeUtils.generateValidateCode(6).toString();
        try {
            // 发送验证码
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,appointmentCode);
            //存入Redis,并设置5分钟有效时间，过时则自动删除
            jedis.setex(key,5*60,appointmentCode);

            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
    }
    
    /** 
     * @Description: 登录所需的验证码
     * @Param: [telephone] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/send4Login")
    public Result send4Login(String telephone){
        Jedis jedis = jedisPool.getResource();
        String key = "Login_" + RedisMessageConstant.SENDTYPE_LOGIN + telephone;
        if (null != jedis.get(key)) {
            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        }

        String loginCode = ValidateCodeUtils.generateValidateCode(4).toString();
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone, loginCode);
            jedis.setex(key,2*60,loginCode);
            return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
    }
}









