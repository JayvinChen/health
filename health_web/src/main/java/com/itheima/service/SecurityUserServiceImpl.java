package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Jayvin
 * @date 2019/10/9 16:13
 */
@Service("securityUserServiceImpl")
public class SecurityUserServiceImpl implements UserDetailsService {
    @Reference
    private UserService userService;

    /** 
     * @Description: 根据用户名授权 
     * @Param: [username] 
     * @return: org.springframework.security.core.userdetails.UserDetails 
     */ 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户是否存在
        User user = userService.findPermissionByUsername(username);
        if (null == user) {
            return null;
        }
        // 授权
        List<GrantedAuthority> authorityList = new ArrayList();
        List<Role> userRoles = user.getRoles();
        if (null != userRoles && userRoles.size() != 0) {
            GrantedAuthority authority = null;
            for (Role userRole : userRoles) {
                authority = new SimpleGrantedAuthority(userRole.getKeyword());
                authorityList.add(authority);

                if (null != userRole.getPermissions()) {
                    for (Permission permission : userRole.getPermissions()) {
                        authority = new SimpleGrantedAuthority(permission.getKeyword());
                        authorityList.add(authority);
                    }
                }
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorityList);
    }
}
