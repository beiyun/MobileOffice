package com.beiyun.workers.entity;

/**
 * Created by beiyun on 2018/3/31.
 * Workers
 */
public class MainFragListEntity {

    private String title;
    private String author;
    private int counter;
    private int titleImageRes;
    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleImageRes() {
        return titleImageRes;
    }

    public void setTitleImageRes(int titleImageRes) {

        this.titleImageRes = titleImageRes;
    }

    @Override
    public String toString() {
        return "MainFragListEntity{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", counter=" + counter +
                ", titleImageRes=" + titleImageRes +
                ", content='" + content + '\'' +
                '}';
    }
}
