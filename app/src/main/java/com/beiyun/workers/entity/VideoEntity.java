package com.beiyun.workers.entity;

/**
 * Created by beiyun on 2018/4/11.
 * Workers
 */
public class VideoEntity {

    private String title;
    private String path;
    private long duration;
    private long size;
    private String thumbImage;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    @Override
    public String toString() {
        return "VideoEntity{" +
                "title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", duration=" + duration +
                ", thumbImage='" + thumbImage + '\'' +
                '}';
    }
}
