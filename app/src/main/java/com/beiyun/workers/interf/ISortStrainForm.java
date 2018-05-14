package com.beiyun.workers.interf;

/**
 * Created by zqht on 2016/6/29 16:02
 * Email:zmm534635184@sina.com
 */
public interface ISortStrainForm {
    String getFieldName();

    String getFieldArea();

    String getStrainCount();

    void setFieldName(String fieldName);

    void setFieldArea(String feildArea);

    void setStrainCount(String strainCount);

    void setOnDeleteClickListener(OnDeleteClickListener deleteClickListener);
}
