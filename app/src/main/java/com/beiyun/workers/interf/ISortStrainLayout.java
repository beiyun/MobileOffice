package com.beiyun.workers.interf;

import android.view.View;

import com.beiyun.workers.entity.SortStrainItemInfo;

import java.util.List;

/**
 * Created by zqht on 2016/6/29 16:05
 * Email:zmm534635184@sina.com
 */
public interface ISortStrainLayout {
    void add();

    void reduce(View view);

    List<SortStrainItemInfo> getFieldChangeInfo();

    void setFieldChangeInfo(List<SortStrainItemInfo> info);
}
