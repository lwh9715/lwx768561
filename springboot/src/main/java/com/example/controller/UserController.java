package com.example.controller;


import com.example.bean.Product;
import com.example.bean.User;
import com.example.service.UserService;
import com.example.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserServiceImpl userServiceImpl;


    /**
     * @Cacheable 1、先查缓存，
     * 2、若没有缓存，就执行方法
     * 3、若有缓存。则返回，不执行方法
     * <p>
     * 所以@Cacheable 不能使用result
     */
    //查询单一商品
    @Cacheable(value = "user", unless = " #id == null ")
    @GetMapping("/customer/{id}")
    public String getUser(@PathVariable("id") Integer id, Model model) {
        User userById = userService.getUserById(id);
        model.addAttribute("list", userById);
        return "customer/customer";
    }

    //查询所有员工返回列表页面
    @GetMapping(value = "/customers")
    public String findAll(Model model,
                          @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                          @RequestParam(defaultValue = "8", value = "pageSize") Integer pageSize) {

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
            List<User> userList = userService.getUserAll();
            // 3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<User> pageInfo = new PageInfo<>(userList, pageSize);
            //4.使用model/map/modelAndView等带回前端
            model.addAttribute("pageInfo", pageInfo);
            //放在请求域中
            model.addAttribute("lists", userList);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "table/customer_list";
    }

    //来到添加页面
    @GetMapping("/ins")
    public String toInsertUser() {
        //来到添加页面
        return "customer/add";
    }


    /**
     * @CachePut 更新并刷新缓存
     * 1、先调用目标方法
     * 2、把结果缓存
     */
    //添加
    @PostMapping("/ins")
    public String insertUser(User user) {
        userService.insertUser(user);
        return "redirect:/customers";
    }

    //来到修改页面,查出当前用户,页面回显
    @GetMapping("/update/{id}")
    public String toUpdateUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("update", user);
        //回到修改页面
        return "customer/upload";
    }

    //修改
    @PutMapping("/update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/customers";
    }
    //删除

    /**
     * 删除指定key 的缓存
     * beforeInvocation=false  缓存的清除是否在方法之前执行
     * 默认是在方法之后执行
     */
    @CacheEvict(value = "#user", key = "#id", beforeInvocation = true)
    @DeleteMapping("/del/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/customers";
    }
}
