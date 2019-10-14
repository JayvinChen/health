package com.itheima.service;

import com.itheima.pojo.User;

/*
 * @author Jayvin
 * @date 2019/10/9 16:54
 */
public interface UserService {
    User findPermissionByUsername(String username);

    User findMenuByUsername(String username);
}
