package com.beiyun.workers.entity;

/**
 * 扶持补贴
 * Created by zqht on 2016/6/29 13:37
 * Email:zmm534635184@sina.com
 */
public class SubsidyItemInfo {
    /**
     * 扶持项目
     */
    public String project;
    /**
     * 补贴标准
     */
    public String standard;
    /**
     * 兑现方式
     */
    public String cash;

    public SubsidyItemInfo(String project, String standard, String cash) {
        this.cash = cash;
        this.project = project;
        this.standard = standard;
    }

    public SubsidyItemInfo() {
    }
}
