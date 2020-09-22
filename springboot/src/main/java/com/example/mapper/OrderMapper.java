package com.example.mapper;

import com.example.bean.Order;

import java.util.List;

public interface OrderMapper {

    //根据id获取单条数据
    String getOrderById(Integer id);
    //获取order所有数据
    List<Order> getAllOrders();
    //新增一条order数据
    String addOrder(Order order);
    //删除一条order数据
    String deleteOrder(Integer id);
    //更新一条order数据
    String editOrder(Order order);
}
