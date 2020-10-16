package com.example.service.impl;

import com.example.bean.Order;
import com.example.mapper.OrderMapper;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    //根据id获取单条数据
    @Override
    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }
    //获取order所有数据
    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }
    //新增一条order数据
    @Override
    public void addOrder(Order order) {
        orderMapper.addOrder(order);
    }
    //删除一条order数据
    @Override
    public void deleteOrder(Integer id) {
        orderMapper.deleteOrder(id);
    }
    //更新一条order数据
    @Override
    public void editOrder(Order order) {
        orderMapper.editOrder(order);
    }
}
