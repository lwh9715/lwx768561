package com.example.controller;


import com.example.bean.Picture;
import com.example.service.PictureService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PictureController {

    @Autowired
    PictureService pictureService;

    //根据id查找图片
    @GetMapping("/picture/{id}")
    public String getPictureById(@PathVariable("id") Integer id,Model model){
        Picture picture = pictureService.getPictureById(id);
        model.addAttribute("picture",picture);
        return "picture/picture";
    }
    //查找所有图片
    @GetMapping("/pictures")
    public String findPictures(Model model,
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
            List<Picture> pictures = pictureService.findPictures();
            // 3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Picture> pageInfo = new PageInfo<>(pictures, pageSize);
            //4.使用model/map/modelAndView等带回前端
            model.addAttribute("pageInfo", pageInfo);
            //放在请求域中
            model.addAttribute("pictures", pictures);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
            return "table/picture_list";
    }
    //来到添加图片页面
    @GetMapping("/picture/add")
    public String toAddPicture(){
        return "picture/picture_add";
    }
    //实现图片添加功能
    @PostMapping("/picture/add")
    public String addPicture(Picture picture){
        pictureService.addPicture(picture);
        return "redirect:/pictures";
    }
    //来到修改图片页面
    @GetMapping("/picture/edit/{id}")
    public String toEditPicture(@PathVariable("id") Integer id,Model model){
        Picture editPicture = pictureService.getPictureById(id);
        model.addAttribute("editPicture",editPicture);
        return "picture/picture_edit";
    }
    //实现修改图片功能
    @PutMapping("/picture/edit")
    public String editPicture(Picture picture){
        pictureService.editPicture(picture);
        return "redirect:/pictures";
    }
    //实现删除功能
    @DeleteMapping("/picture/del/{id}")
    public String deletePicture(@PathVariable("id") Integer id){
        pictureService.deletePicture(id);
        return "redirect:/pictures";
    }
}
