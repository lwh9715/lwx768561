package com.example.bean;

import lombok.Data;

@Data
public class Log {
    private Integer id;
    private String title;
    private String date;
    private String content;

    public Log() {}

    public Log(Integer id, String title, String date, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
    }
}
