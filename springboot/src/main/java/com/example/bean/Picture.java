package com.example.bean;

public class Picture {

    private Integer id;
    private String picture_name;
    private String picture_images;

    public Picture() {
    }

    public Picture(Integer id, String picture_name, String picture_images) {
        this.id = id;
        this.picture_name = picture_name;
        this.picture_images = picture_images;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture_name() {
        return picture_name;
    }

    public void setPicture_name(String picture_name) {
        this.picture_name = picture_name;
    }

    public String getPicture_images() {
        return picture_images;
    }

    public void setPicture_images(String picture_images) {
        this.picture_images = picture_images;
    }

}
