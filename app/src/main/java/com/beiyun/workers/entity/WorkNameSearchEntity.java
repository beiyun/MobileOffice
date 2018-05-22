package com.beiyun.workers.entity;

/**
 * 工作任务名称查询类
 */
public class WorkNameSearchEntity {

    private String id;
    private String title;
    private String performRole;

    @Override
    public String toString() {
        return "WorkNameSearchEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", performRole='" + performRole + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformRole() {
        return performRole;
    }

    public void setPerformRole(String performRole) {
        this.performRole = performRole;
    }
}
