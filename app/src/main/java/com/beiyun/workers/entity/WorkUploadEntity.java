package com.beiyun.workers.entity;

public class WorkUploadEntity {

    private String title; // 任务标题   50个字符

    private String degree; // 紧急程度   1正常  2紧急  3非常紧急

    private String endTime; // 截止日期

    private String performRole; // 执行角色  82办公室主任  3辅导员  83办公室科员  2职工  1站长  81企管科长  84生产科长  85生产科员

    private String demand; // 任务要求  100个字符



    @Override
    public String toString() {
        return "WorkUploadEntity{" +
                "title='" + title + '\'' +
                ", degree='" + degree + '\'' +
                ", endTime='" + endTime + '\'' +
                ", performRole='" + performRole + '\'' +
                ", demand='" + demand + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public String getPerformRole() {
        return performRole;
    }

    public void setPerformRole(String performRole) {
        this.performRole = performRole;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }


}
