package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author Jayvin
 * @date 2019/10/9 19:20
 */
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HEALTH_MANAGER')")
public class UserController {
    @Reference
    private UserService userService;

    /** 
     * @Description: 查询用户名与菜单
     * @Param: [] 
     * @return: com.itheima.entity.Result 
     */ 
    @GetMapping("/getUsernameAndMenus")
    public Result getUsernameAndMenus(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // 也可通过以下方法获得用户名
        // org.springframework.security.core.userdetails.User user1 = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // String username1 = user1.getUsername();

        User user = userService.findMenuByUsername(username);
        return new Result(true,MessageConstant.GET_USERNAME_SUCCESS,user);
    }

}
