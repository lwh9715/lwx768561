package com.example.service;

import com.example.bean.Customer;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface CustomerService {


    //根据用户id查询用户信息
    Customer getCustomerById(Integer id);

    //查询所有用户信息
    List<Customer> getCustomerAll();

    //增加一条用户信息
    void insertCustomer(Customer customer);

    //根据用户id删除一条用户信息
    void deleteCustomer(Integer id);

    //根据用户id修改用户信息
    void updateCustomer(Customer customer);

}
