package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.service.MemberService;
import com.itheima.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/*
 * @author Jayvin
 * @date 2019/10/8 9:16
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Reference
    private MemberService memberService;
    @Autowired
    private JedisPool jedisPool;

    /** 
     * @Description: 手机验证码登录，并跟踪用户行为
     * @Param: [map, response] 
     * @return: com.itheima.entity.Result 
     */ 
    @PostMapping("/check")
    public Result login(@RequestBody Map<String, String> map, HttpServletResponse response){
        String telephone = map.get("telephone");
        Jedis jedis = jedisPool.getResource();
        String key = "Login_" + RedisMessageConstant.SENDTYPE_LOGIN + "_" + telephone;
        String codeInRedis = jedis.get(key);
        if (StringUtils.isEmpty(codeInRedis)) {
            // 验证码为空或者已过时
            return new Result(false,MessageConstant.TELEPHONE_VALIDATECODE_NOTNULL);
        }
        if (!codeInRedis.equals(map.get("validateCode"))) {
            //验证码错误
            return new Result(false,MessageConstant.VALIDATECODE_ERROR);
        }

        // 清除验证码，防止复用
        jedis.del(key);
        // 登录
        memberService.login(telephone);
        //跟踪用户行为
        Cookie cookie = CookieUtil.creatCookie("member_telephone", telephone, 30 * 24 * 60 * 60, "/");
        response.addCookie(cookie);

        return new Result(true,MessageConstant.LOGIN_SUCCESS);
    }
}
