package com.example.controller;

import com.example.bean.Order;
import com.example.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 定义用户相关网址映射的Controller
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order/{id}")
    public String getOrderById(@PathVariable("id") Integer id,Model model){
        Order order = orderService.getOrderById(id);
        model.addAttribute("order",order);
        return "order/order";
    }

    //查询所有订单返回列表页面
    @GetMapping("/orders")
    public String getAllOrders(Model model,
                               @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                               @RequestParam(defaultValue = "5", value = "pageSize") Integer pageSize){
        //为了程序的严谨性,判断非空：
        if (pageNum == null) {
            pageNum = 1;   //设置默认当前页
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;    //设置默认每页显示的数据数
        }
        //1.引入分页插件,pageNum是第几页,pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟,后面的其他查询不会被分页,除非再次调用PageHelper.startPage
        try {
            List<Order> orders = orderService.getAllOrders();
            // 3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Order> pageInfo = new PageInfo<>(orders, pageSize);
            //4.使用model/map/modelAndView等带回前端
            model.addAttribute("pageInfo", pageInfo);
            //放在请求域中
            model.addAttribute("orders", orders);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "table/order_list";
    }
    //来到商品添加页面
    @GetMapping("/order/toAdd")
    public String toAddOrder(){
        return "order/order_add";
    }
    //实现添加功能
    @PostMapping("/order/add")
    public String addOrder(Order order){
        orderService.addOrder(order);
        return "redirect:/orders";
    }

    //实现删除功能
    @DeleteMapping("/order/del/{id}")
    public String deleteOrder(@PathVariable("id") Integer id){
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }

    //来到商品修改页面，查出当前商品,页面回显
    @GetMapping("/order/toEdit/{id}")
    public String toEditOrder(@PathVariable("id") Integer id,Model model){
        Order order = orderService.getOrderById(id);
        model.addAttribute("orderEdit",order);
        //回到修改页面
        return "order/order_edit";
    }

    //实现修改功能
    @PutMapping("/order/edits")
    public String editOrders(Order order){
        orderService.editOrder(order);
        return "redirect:/orders";
    }
}
