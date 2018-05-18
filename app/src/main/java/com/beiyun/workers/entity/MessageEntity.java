package com.beiyun.workers.entity;

/**
 * Created by beiyun on 2018/4/1.
 * Workers
 */
public class MessageEntity {
    private String id;
    private String auditTime;
    private String img;
    private String content;
    private String title;
    private String date;
    private int imageRes;


    @Override
    public String toString() {
        return "MessageEntity{" +
                "id='" + id + '\'' +
                ", auditTime='" + auditTime + '\'' +
                ", img='" + img + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", imageRes=" + imageRes +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
