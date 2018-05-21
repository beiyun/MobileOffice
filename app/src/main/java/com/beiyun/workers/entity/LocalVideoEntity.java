package com.beiyun.workers.entity;

import java.io.File;

public class LocalVideoEntity {

//    bo.title 标题  bo.introduction 简介  bo.content 内容  bo.editId登录人的ID
//    bo.courseModel 课程模块  1 育苗 2 理墒 3 植保 4 大田 5 优化 6 烘烤 7 收购 8 其他

    private String title;

    private String introduction;

    private String content;

    private String editId;

    private String courseModel;

    private File videoFile;

    private String fileName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEditId() {
        return editId;
    }

    public void setEditId(String editId) {
        this.editId = editId;
    }

    public String getCourseModel() {
        return courseModel;
    }

    public void setCourseModel(String courseModel) {
        this.courseModel = courseModel;
    }

    public File getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(File videoFile) {
        this.videoFile = videoFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
