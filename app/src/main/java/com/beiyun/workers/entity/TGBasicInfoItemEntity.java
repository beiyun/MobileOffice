package com.beiyun.workers.entity;

/**
 * Created by 中旗 on 2016/11/15.
 */
public class TGBasicInfoItemEntity  {

    public String id;

    public String longitude;//经度

    public String latitude;//纬度

    public String count;//亩数

    public String landSources;//土地来源

    public String type;//田烟类型

    public String name;//地块名称

    public TGBasicInfoItemEntity(){}

    public TGBasicInfoItemEntity(String longitude, String latitude, String count, String type, String name,String id) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.count = count;
        this.type = type;
        this.name = name;
        this.id = id;
    }

    public TGBasicInfoItemEntity(String longitude, String latitude, String count, String landSources, String type, String name,String id) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.count = count;
        this.landSources = landSources;
        this.type = type;
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "TGBasicInfoItemEntity{" +
                "count='" + count + '\'' +
                ", id='" + id + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", landSources='" + landSources + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


}
