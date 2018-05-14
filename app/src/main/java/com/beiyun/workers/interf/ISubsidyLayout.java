package com.beiyun.workers.interf;

import android.view.View;

import com.beiyun.workers.entity.SubsidyItemInfo;

import java.util.List;

/**
 * Created by zqht on 2016/6/29 14:09
 * Email:zmm534635184@sina.com
 */
public interface ISubsidyLayout {
    void add();

    void reduce(View view);

    List<SubsidyItemInfo> getSubsidyInfo();

    void setSubsidyInfo(List<SubsidyItemInfo> info);
    String convertToString();
}
