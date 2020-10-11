package com.example.service.impl;

import com.example.bean.Picture;
import com.example.mapper.PictureMapper;
import com.example.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureMapper pictureMapper;

    @Override
    public Picture getPictureById(Integer id) {
        return pictureMapper.getPictureById(id);
    }

    @Override
    public List<Picture> findPictures() {
        return pictureMapper.findPictures();
    }

    @Override
    public void addPicture(Picture picture) {
        pictureMapper.addPicture(picture);
    }

    @Override
    public void editPicture(Picture picture) {
        pictureMapper.editPicture(picture);
    }

    @Override
    public void deletePicture(Integer id) {
        pictureMapper.deletePicture(id);
    }
}
