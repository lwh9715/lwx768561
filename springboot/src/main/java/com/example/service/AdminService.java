package com.example.service;

import com.example.bean.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminService{

    //查询用户名和密码
    Admin login(@Param("username") String username, @Param("password") String password);
    Admin securityLogin(@Param("username")String username);


}
