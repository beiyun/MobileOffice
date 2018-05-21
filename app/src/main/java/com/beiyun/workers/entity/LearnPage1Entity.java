package com.beiyun.workers.entity;

/**
 * Created by beiyun on 2018/4/9.
 * Workers
 */
public class LearnPage1Entity {


    private int imageRes;

    //标题
    private String title;
    //副标题
    private String subTitle;
    //播放次数
    private int playTimes;
    //作者
    private String author;
    //视频时长
    private String videoLength;
    //上传日期
    private String uploadDate;
    //内容
    private String content;
    //视频路径
    private String videoUrl;
    //视频缩略图
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

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(String videoLength) {
        this.videoLength = videoLength;
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
        return "LearnPage1Entity{" +
                "imageRes=" + imageRes +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", playTimes=" + playTimes +
                ", author='" + author + '\'' +
                ", videoLength='" + videoLength + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
