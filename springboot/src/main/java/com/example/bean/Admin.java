package com.example.bean;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor//注解在类上；为类提供一个无参的构造方法
@AllArgsConstructor//注解在类上；为类提供一个全参的构造方法
public class Admin {

    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
