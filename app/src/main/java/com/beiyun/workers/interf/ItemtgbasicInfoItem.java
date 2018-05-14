package com.beiyun.workers.interf;

/**
 * Created by 中旗 on 2016/11/15.
 */
public interface ItemtgbasicInfoItem {

    String getLongitude();//经度

    String getLatitude();//纬度

    String getCount();//亩数

    String getLandSources();//租赁(是否)

    String getName();//地块名称

    String getType();//田烟类型

    void setLongitude(String longitude);

    void setLatitude(String latitude);

    void setCount(String count);

    void setLandSources(String landSources);

    void setName(String name);

    void setType(String type);

    void setOnDeleteListener(OnDeleteClickListener listener);

}
