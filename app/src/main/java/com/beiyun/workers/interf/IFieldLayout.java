package com.beiyun.workers.interf;

import android.view.View;

import com.beiyun.workers.entity.FieldItemInfo;

import java.util.List;

/**
 * Created by zqht on 2016/6/29 14:09
 * Email:zmm534635184@sina.com
 */
public interface IFieldLayout {
    void add();

    void reduce(View view);

    List<FieldItemInfo> getFieldInfo();

    void setFieldInfo(List<FieldItemInfo> info);
    String convertToString();
}
