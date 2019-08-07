package com.example.myapplication.Model;

public class ModelLatest {

    private String imgIconEye;
    private String imgIconHeart;
    private String tvCountEye;
    private String tvCountHeart;

    public ModelLatest(String imgIconEye, String imgIconHeart, String tvCountEye, String tvCountHeart) {

        this.imgIconEye = imgIconEye;
        this.imgIconHeart = imgIconHeart;
        this.tvCountEye = tvCountEye;
        this.tvCountHeart = tvCountHeart;
    }

    public String getImgIconEye() {
        return imgIconEye;
    }

    public void setImgIconEye(String imgIconEye) {
        this.imgIconEye = imgIconEye;
    }

    public String getImgIconHeart() {
        return imgIconHeart;
    }

    public void setImgIconHeart(String imgIconHeart) {
        this.imgIconHeart = imgIconHeart;
    }

    public String getTvCountEye() {
        return tvCountEye;
    }

    public void setTvCountEye(String tvCountEye) {
        this.tvCountEye = tvCountEye;
    }

    public String getTvCountHeart() {
        return tvCountHeart;
    }

    public void setTvCountHeart(String tvCountHeart) {
        this.tvCountHeart = tvCountHeart;
    }
}