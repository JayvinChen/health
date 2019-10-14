package com.itheima.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.UserDao;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

/*
 * @author Jayvin
 * @date 2019/10/9 16:53
 */
@Service(interfaceClass = UserService.class)
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /** 
     * @Description: 根据用户名查询权限 
     * @Param: [username] 
     * @return: com.itheima.pojo.User 
     */ 
    @Override
    public User findPermissionByUsername(String username) {
        return userDao.findPermissionByUsername(username);
    }

    /** 
     * @Description: 根据用户名查询菜单
     * @Param: [username] 
     * @return: com.itheima.pojo.User 
     */ 
    @Override
    public User findMenuByUsername(String username) {
        return userDao.findMenuByUsername(username);
    }
}
