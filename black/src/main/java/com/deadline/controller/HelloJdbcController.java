package com.deadline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HelloJdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    int num = 0;

    @GetMapping("/findAll")
    public Map<String, Object> map() {
        System.out.println(++num);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from users");
        return maps.get(0);
    }
}
