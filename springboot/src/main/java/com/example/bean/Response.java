package com.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回服务器状态的bean类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {

    private Integer status;
    private String msg;
    private Object obj;

}
