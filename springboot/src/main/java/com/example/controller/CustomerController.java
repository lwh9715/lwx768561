package com.example.controller;


import com.example.bean.Customer;
import com.example.service.CustomerService;
import com.example.service.impl.CustomerServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

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
    public String getCustomer(@PathVariable("id") Integer id, Model model) {
        Customer customerById = customerService.getCustomerById(id);
        model.addAttribute("list", customerById);
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
            List<Customer> customerAll = customerService.getCustomerAll();
            // 3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Customer> pageInfo = new PageInfo<>(customerAll, pageSize);
            //4.使用model/map/modelAndView等带回前端
            model.addAttribute("pageInfo", pageInfo);
            //放在请求域中
            model.addAttribute("lists", customerAll);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "table/customer_list";
    }

    //来到添加页面
    @GetMapping("/customer/toIns")
    public String toInsertCustomer() {
        //来到添加页面
        return "customer/customer_add";
    }


    /**
     * @CachePut 更新并刷新缓存
     * 1、先调用目标方法
     * 2、把结果缓存
     *@Valid 表单校验效果，在bean实体类中添加相关的注解
     **/
     //添加
    @PostMapping("/customer/ins")
    public String insertUser(@Valid Customer customer) {
        customerService.insertCustomer(customer);
        return "redirect:/customers";
    }

    //来到修改页面,查出当前用户,页面回显
    @GetMapping("/customer/toUpdate/{id}")
    public String toUpdateCustomer(@PathVariable("id") Integer id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("update", customer);
        //回到修改页面
        return "customer/customer_edit";
    }

    //修改
    @PutMapping("/customer/update")
    public String updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }
    //删除

    /**
     * 删除指定key 的缓存
     * beforeInvocation=false  缓存的清除是否在方法之前执行
     * 默认是在方法之后执行
     */
    @CacheEvict(value = "#user", key = "#id", beforeInvocation = true)
    @DeleteMapping("customer/del/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
