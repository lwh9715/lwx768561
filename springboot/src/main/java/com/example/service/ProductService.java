package com.example.service;

import com.example.bean.Product;

import java.util.List;

public interface ProductService {

    //根据id获取单条数据
    Product getProductById(Integer id);

    //获取去所有数据
    List<Product> getProducts();

    //新增数据
    void addProduct(Product product);

    //修改数据
    void editProduct(Product product);

    //删除数据
    void deleteProduct(Integer id);
}
