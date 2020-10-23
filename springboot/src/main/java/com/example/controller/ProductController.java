package com.example.controller;

import com.example.bean.Product;
import com.example.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 定义用户相关网址映射的Controller
 */
@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * @Cacheable 1、先查缓存，
     * 2、若没有缓存，就执行方法
     * 3、若有缓存。则返回，不执行方法
     * <p>
     * 所以@Cacheable 不能使用result
     */
    //查询单一商品
    @Cacheable(value = "product", unless = " #id == null ")
    @GetMapping("/product/{id}")
    public String byIdProduct(@PathVariable("id") Integer id, Model model) {
        Product byId = productService.getProductById(id);
        model.addAttribute("product", byId);
        return "product/product";
    }

    //查询所有商品返回列表页面
    @GetMapping("/products")
    public String getProducts(Model model,
                              @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "5", value = "pageSize") Integer pageSize) {

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
            List<Product> products = productService.getProducts();
            // 3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Product> pageInfo = new PageInfo<>(products, pageSize);
            //4.使用model/map/modelAndView等带回前端
            model.addAttribute("pageInfo", pageInfo);
            //放在请求域中
            model.addAttribute("products", products);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "table/product_list";
    }

    //来到商品添加页面
    @GetMapping(value = "/product/toIns")
    public String toAddProduct() {
        return "product/product_add";
    }

    /**
     * @CachePut 更新并刷新缓存
     * 1、先调用目标方法
     * 2、把结果缓存
     */
    //实现添加功能
    @PostMapping(value = "/product/ins")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    //来到修改页面,查出当前商品,页面回显
    @GetMapping("/product/toEdit/{id}")
    public String toEditProduct(@PathVariable("id") Integer id, Model model) {
        Product products = productService.getProductById(id);
        model.addAttribute("editProduct", products);
        //回到修改页面
        return "product/product_edit";
    }

    //实现修改功能
    @PutMapping("/product/edit")
    public String editProduct(Product product) {
        productService.editProduct(product);
        return "redirect:/products";
    }

    /**
     * 删除指定key的缓存
     * beforeInvocation=false  缓存的清除是否在方法之前执行
     * 默认是在方法之后执行
     */
    //实现删除功能
    @CacheEvict(value = "product", key = "#id", beforeInvocation = true)
    @DeleteMapping(value = "/product/del/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
