package com.example.service;

import com.example.bean.Picture;

import java.util.List;

public interface PictureService {
    //根据id查找图片
    public Picture getPictureById(Integer id);
    //查找所有图片
    public List<Picture> findPictures();
    //添加图片
    public void addPicture(Picture picture);
    //更新图片
    public void editPicture(Picture picture);
    //删除图片
    public void deletePicture(Integer id);
}
