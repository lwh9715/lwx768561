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

    @Override
    public Log getLogById(Integer id) {
        return logMapper.getLogById(id);
    }

    @Override
    public List<Log> getAllLogs() {
        return logMapper.getAllLogs();
    }

    @Override
    public void addLog(Log log) {
        logMapper.addLog(log);
    }

    @Override
    public void editLog(Log log) {
        logMapper.editLog(log);
    }

    @Override
    public void delLog(Integer id) {
        logMapper.delLog(id);
    }

    @Override
    public void delAllLogs() {
        logMapper.delAllLogs();
    }
}
