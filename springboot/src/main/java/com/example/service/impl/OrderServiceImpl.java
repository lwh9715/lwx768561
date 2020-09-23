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
    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public void addOrder(Order order) {
        orderMapper.addOrder(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderMapper.deleteOrder(id);
    }

    @Override
    public void editOrder(Order order) {
        orderMapper.editOrder(order);
    }
}
