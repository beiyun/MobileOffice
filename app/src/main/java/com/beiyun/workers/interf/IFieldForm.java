package com.beiyun.workers.interf;

/**
 * Created by zqht on 2016/6/29 14:07
 * Email:zmm534635184@sina.com
 */
public interface IFieldForm {
    String getTobaccoFieldNo();

    String getPerFieldCount();

    String getVariety();

    void setVariety(String variety);

    void setPerFieldCount(String perFieldCount);

    void setTobaccoFieldNo(String tobaccoFieldNo);

    void setOnDeleteClickListener(OnDeleteClickListener deleteClickListener);
}
