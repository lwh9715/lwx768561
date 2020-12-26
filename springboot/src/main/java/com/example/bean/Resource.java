package com.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/*
* 资源表resource对应的类
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource implements Serializable {

    private Long id;
    private String url;
    private String roles;

    public String[] getRolesArray(){
        String[] authorities = roles.split(",");
        return authorities;
    }
}
