package com.example.service;

import com.example.bean.Log;

import java.util.List;

public interface LogService {

    //根据id获取单行信息
    public Log getLogById(Integer id);
    //获取所有log信息
    public List<Log> getAllLogs();
    //添加log信息
    public void addLog(Log log);
    //编辑log信息
    public void editLog(Log log);
    //删除一条log信息
    public void delLog(Integer id);
    //删除所有log信息
    void delAllLogs();
}
