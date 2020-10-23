package com.security.mapper;

import com.security.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AdminMapper {
    /**
     * 从数据库中查询用户
     * @param username
     * @return
     */
    @Select("select * from admin where username = #{username}")
    Admin selectByUsername(@Param("username") String username);
}
