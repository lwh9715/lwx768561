package com.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private Integer p_id;
    private String p_name;
    private Double p_price;
    private String p_date;

}
