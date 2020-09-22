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

    @Override
    public String getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public String addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public String deleteOrder(Integer id) {
        return orderMapper.deleteOrder(id);
    }

    @Override
    public String editOrder(Order order) {
        return orderMapper.editOrder(order);
    }
}
