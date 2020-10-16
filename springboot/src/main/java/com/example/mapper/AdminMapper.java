package com.example.mapper;

import com.example.bean.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    //查询用户名和密码
    Admin login(@Param("username")String username, @Param("password") String password);

}
