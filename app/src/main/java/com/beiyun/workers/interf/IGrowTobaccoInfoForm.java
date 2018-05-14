package com.beiyun.workers.interf;

/**
 * Created by mpb on 2016/6/24.
 */
public interface IGrowTobaccoInfoForm {

    String getFieldName();

    String getArea();

    String getAreaCode();

    String getPreceding();

    void setFieldName(String fieldName);

    void setArea(String area);

    void setAreaCode(String areaCode);

    void setPreceding(String preceding);

    void setOnDeleteClickListener(OnDeleteClickListener deleteClickListener);


}
