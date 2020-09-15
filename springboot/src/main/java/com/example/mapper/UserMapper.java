package com.example.mapper;


import com.example.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

//指定这是一个操作数据库的mapper
//mapper或者mapperScan将接口扫描装配到容器中
@Repository
public interface UserMapper {
    //根据用户id查询用户信息
    public User getUserById(Integer id);

    //查询所有用户信息
    public List<User> getUserAll();

    //增加一条用户信息
    public void insertUser(User user);

    //根据用户id删除一条用户信息
    public void deleteUser(Integer id);

    //根据用户id修改用户信息
    public void updateUser(User user);


}
