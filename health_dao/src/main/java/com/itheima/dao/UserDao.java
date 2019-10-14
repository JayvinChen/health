package com.itheima.dao;

import com.itheima.pojo.User;

/*
 * @author Jayvin
 * @date 2019/10/9 17:21
 */
public interface UserDao {
    User findPermissionByUsername(String username);

    User findMenuByUsername(String username);
}
