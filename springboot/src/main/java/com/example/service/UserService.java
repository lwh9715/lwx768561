package com.example.service;

import com.example.bean.User;


import java.util.List;

public interface UserService {

    /**
     * 查询所有用户
     *
     * @return
     */
    User getUserById(Integer id);

    List<User> getUserAll();

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

}
