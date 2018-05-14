package com.beiyun.workers.entity;

/**
 * Created by beiyun on 2018/4/9.
 * Workers
 */
public class LearnPage2Entity {

    private int imageRes;
    private String title;
    private String subTitle;
    private int playTimes;
    private String author;
    private String uploadDate;
    private String videoLength;
    private String content;
    private String videoUrl;
    private String thumbImageUrl;

    public String getThumbImageUrl() {
        return thumbImageUrl;
    }

    public void setThumbImageUrl(String thumbImageUrl) {
        this.thumbImageUrl = thumbImageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(String videoLength) {
        this.videoLength = videoLength;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(int playTimes) {
        this.playTimes = playTimes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "LearnPage2Entity{" +
                "imageRes=" + imageRes +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", playTimes=" + playTimes +
                ", author='" + author + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", videoLength='" + videoLength + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
