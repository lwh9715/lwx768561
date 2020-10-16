package com.example.service.impl;

import com.example.bean.Admin;
import com.example.mapper.AdminMapper;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    //实现查询用户名和密码
    @Override
    public Admin login(String username, String password) {
        return adminMapper.login(username, password);
    }
}
