package com.example.service.impl;

import com.example.bean.Picture;
import com.example.mapper.PictureMapper;
import com.example.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureMapper pictureMapper;

    //根据id查找图片
    @Override
    public Picture getPictureById(Integer id) {
        return pictureMapper.getPictureById(id);
    }
    //查找所有图片
    @Override
    public List<Picture> findPictures() {
        return pictureMapper.findPictures();
    }
    //添加图片
    @Override
    public void addPicture(Picture picture) {
        pictureMapper.addPicture(picture);
    }
    //更新图片
    @Override
    public void editPicture(Picture picture) {
        pictureMapper.editPicture(picture);
    }
    //删除图片
    @Override
    public void deletePicture(Integer id) {
        pictureMapper.deletePicture(id);
    }
}
