package com.example.service.impl;

import com.example.bean.Customer;
import com.example.mapper.CustomerMapper;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    //根据用户id查询用户信息
    @Override
    public Customer getCustomerById(Integer id) {
        return customerMapper.getCustomerById(id);
    }

    //实现查询所有用户信息
    @Override
    public List<Customer> getCustomerAll() {
        return customerMapper.getCustomerAll();
    }

    //实现增加一条用户信息
    @Override
    public void insertCustomer(Customer customer) {
        customerMapper.insertCustomer(customer);
    }

    //实现根据用户id删除一条用户信息
    @Override
    public void deleteCustomer(Integer id) {
        customerMapper.deleteCustomer(id);
    }

    //实现根据用户id修改用户信息
    @Override
    public void updateCustomer(Customer customer) {
        customerMapper.updateCustomer(customer);
    }

}
