package com.beiyun.workers.entity;

/**
 * Created by zqht on 2016/6/29 16:00
 * Email:zmm534635184@sina.com
 */
public class SortStrainItemInfo {

    public String id;
    /**
     * 地块名称
     */
    public String fieldName;
    /**
     * 地块面积
     */
    public String fieldArea;
    /**
     * 株数
     */
    public String strainCount;

    public SortStrainItemInfo() {
    }

    public SortStrainItemInfo(String fieldName, String fieldArea, String strainCount,String id) {
        this.id = id;
        this.fieldArea = fieldArea;
        this.fieldName = fieldName;
        this.strainCount = strainCount;
    }
}
