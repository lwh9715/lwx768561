package com.example.service;

import com.example.bean.Order;

import java.util.List;

public interface OrderService {

    //根据id获取单条数据
    Order getOrderById(Integer id);
    //获取order所有数据
    List<Order> getAllOrders();
    //新增一条order数据
    void addOrder(Order order);
    //删除一条order数据
    void deleteOrder(Integer id);
    //更新一条order数据
    void editOrder(Order order);

}
