package com.example.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private Integer o_id;
    private String o_name;
    private String o_date;
    private String o_images;

}
