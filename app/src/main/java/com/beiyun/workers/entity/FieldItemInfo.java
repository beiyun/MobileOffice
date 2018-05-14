package com.beiyun.workers.entity;

/**
 * 土地变更
 * Created by zqht on 2016/6/29 13:37
 * Email:zmm534635184@sina.com
 */
public class FieldItemInfo {
    /**
     * 盐田编号
     */
    public String tobaccoFieldNo;
    /**
     * 亩产数
     */
    public String perFieldCount;
    /**
     * 种植品种
     */
    public String variety;

    public FieldItemInfo(){}

    public FieldItemInfo(String tobaccoFieldNo, String perFieldCount, String variety) {
        this.perFieldCount = perFieldCount;
        this.tobaccoFieldNo = tobaccoFieldNo;
        this.variety = variety;
    }
}
