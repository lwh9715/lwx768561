package com.example.bean;

import lombok.Data;

@Data
public class Order {
    private Integer o_id;
    private String o_name;
    private String o_date;
    private String o_images;

    public Order() {
    }
    public Order(Integer o_id, String o_name, String o_date, String o_images) {
        this.o_id = o_id;
        this.o_name = o_name;
        this.o_date = o_date;
        this.o_images = o_images;
    }
}
