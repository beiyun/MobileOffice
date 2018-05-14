package com.beiyun.workers.entity;

/**
 * Created by beiyun on 2018/4/1.
 * Workers
 */
public class MessageEntity {
    private String title;
    private String date;
    private int imageRes;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
