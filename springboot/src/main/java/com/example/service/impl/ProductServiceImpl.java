package com.example.service.impl;

import com.example.bean.Product;
import com.example.mapper.ProductMapper;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    //根据id获取单条数据
    @Override  //该标签会检查检查重写方法的正确性
    public Product getProductById(Integer id) {
        return productMapper.getProductById(id);
    }
    //获取去所有数据
    @Override
    public List<Product> getProducts() {
        return productMapper.getProducts();
    }
    //新增数据
    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }
    //修改数据
    @Override
    public void editProduct(Product product) {
        productMapper.editProduct(product);
    }
    //删除数据
    @Override
    public void deleteProduct(Integer id) {
        productMapper.deleteProduct(id);
    }
}
