package com.beiyun.workers.entity;

/**
 * Created by mpb on 2016/6/24.
 */
public class GrowTobaccoInfo {

//    烟田名称、面积、片区编号、前茬
    //fieldName   ，area,areaCode,preceding

    public String id;

    /**烟田名称*/
    public String fieldName;

    /**面积*/
    public String area;

    /**片区编号*/
    public String areaCode;
    /**前茬*/
    public String preceding;

    public String landSources;

    public GrowTobaccoInfo() {
    }

    public GrowTobaccoInfo(String id,String fieldName,  String area,String areaCode, String preceding,String landSources) {
        this.id = id;
        this.fieldName = fieldName;
        this.areaCode = areaCode;
        this.preceding = preceding;
        this.area = area;
        this.landSources = landSources;
    }

    @Override
    public String toString() {
        return "GrowTobaccoInfo{" +
                "area='" + area + '\'' +
                ", id='" + id + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", preceding='" + preceding + '\'' +
                ", landSources='" + landSources + '\'' +
                '}';
    }
}
