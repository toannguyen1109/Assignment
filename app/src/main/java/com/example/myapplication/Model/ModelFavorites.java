package com.example.myapplication.Model;

public class ModelFavorites {
    private int id;
    private String imgContent;

    public ModelFavorites(String imgContent) {
        this.imgContent = imgContent;
    }

    public int getId() {
        return id;
    }

    public String getImgContent() {
        return imgContent;
    }
}
