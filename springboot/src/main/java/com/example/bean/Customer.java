package com.example.bean;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class Customer implements Serializable {

    private Integer id;
    @NotEmpty(message = "密码不能为空")
    private String username;
    private String password;

    public Customer() {
    }

    public Customer(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
