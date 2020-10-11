package com.example.controller;

import com.example.bean.Log;
import com.example.bean.Product;
import com.example.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LogController {

    @Autowired
    LogService logService;


    //根据id获取单行信息
    @GetMapping("/log/{id}")
    public String getLogById(@PathVariable("id") Integer id, Model model){
        Log log = logService.getLogById(id);
        model.addAttribute("log",log);
        return "log/log";
    }
    //获取所有log信息
    @GetMapping("/logs")
    public String getAllLogs(Model model,
                             @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "5", value = "pageSize") Integer pageSize){
        //为了程序的严谨性,判断非空：
        if (pageNum == null) {
            pageNum = 1;   //设置默认当前页
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;    //设置默认每页显示的数据数
        }
        //1.引入分页插件,pageNum是第几页,pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟,后面的其他查询不会被分页,除非再次调用PageHelper.startPage
        try {
        List<Log> logs = logService.getAllLogs();
        // 3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
        PageInfo<Log> pageInfo = new PageInfo<>(logs, pageSize);
        //4.使用model/map/modelAndView等带回前端
        model.addAttribute("pageInfo", pageInfo);
        //放在请求域中
        model.addAttribute("logs",logs);
        } finally {
        PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        return "table/logs_list";
    }
    //来到添加log界面
    @GetMapping("/log/add")
    public String toAddLog(){
        return "log/log_add";
    }
    //添加log信息
    @PostMapping("/log/add")
    public String addLog(Log log){
        logService.addLog(log);
        return "redirect:/logs";
    }

    //来到编辑log界面,查出当前商品,页面回显
    @GetMapping("/log/edit/{id}")
    public String toEditLog(@PathVariable("id") Integer  id,Model model){
        Log editLog = logService.getLogById(id);
        model.addAttribute("editLog",editLog);
        //回到修改页面
        return "log/log_edit";
    }

    //实现编辑log信息
    @PutMapping("/log/edit")
    public String editLog(Log log){
        logService.editLog(log);
        return "redirect:/logs";
    }
    //删除一条log信息
    @DeleteMapping("/log/del/{id}")
    public String delLog(@PathVariable("id") Integer id){
        logService.delLog(id);
        return "redirect:/logs";
    }

    //删除所有log信息
    @DeleteMapping("/log/dels")
    public String delAllLogs(){
        logService.delAllLogs();
        return "redirect:/logs";
    }
}
