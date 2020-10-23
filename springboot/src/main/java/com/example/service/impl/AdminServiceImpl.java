package com.example.service.impl;

import com.example.bean.Admin;
import com.example.mapper.AdminMapper;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsService的实现类，用于在程序中引入一个自定义的AuthenticationProvider，实现数据库访问模式的验证
 */
@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {

    @Autowired
    AdminMapper adminMapper;

    //实现查询用户名和密码
    @Override
    public Admin login(String username, String password) {
        return adminMapper.login(username, password);
    }

    @Override
    public Admin securityLogin(String username) {
        return adminMapper.securityLogin(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminMapper.securityLogin(username);

        if (admin == null) {
            throw new UsernameNotFoundException("查无此人！！");
        }
        return admin;
    }
}
