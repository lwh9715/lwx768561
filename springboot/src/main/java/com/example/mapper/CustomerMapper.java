package com.example.mapper;


import com.example.bean.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

//指定这是一个操作数据库的mapper
//mapper或者mapperScan将接口扫描装配到容器中
@Repository
public interface CustomerMapper {
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
