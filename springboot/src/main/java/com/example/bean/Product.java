package com.example.bean;

import lombok.Data;

import java.io.Serializable;
@Data
public class Product implements Serializable {

    private Integer p_id;
    private String p_name;
    private Double p_price;
    private String p_date;

    public Product() {
    }

    public Product(Integer p_id, String p_name, Double p_price, String p_date) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_date = p_date;
    }
}
