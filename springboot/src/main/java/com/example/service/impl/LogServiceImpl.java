package com.example.service.impl;

import com.example.bean.Log;
import com.example.mapper.LogMapper;
import com.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    //根据id获取单行信息
    @Override
    public Log getLogById(Integer id) {
        return logMapper.getLogById(id);
    }
    //获取所有log信息
    @Override
    public List<Log> getAllLogs() {
        return logMapper.getAllLogs();
    }
    //添加log信息
    @Override
    public void addLog(Log log) {
        logMapper.addLog(log);
    }
    //编辑log信息
    @Override
    public void editLog(Log log) {
        logMapper.editLog(log);
    }
    //删除一条log信息
    @Override
    public void delLog(Integer id) {
        logMapper.delLog(id);
    }
    //删除所有log信息
    @Override
    public void delAllLogs() {
        logMapper.delAllLogs();
    }
}
