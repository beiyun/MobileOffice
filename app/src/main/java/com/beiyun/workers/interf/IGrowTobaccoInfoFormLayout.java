package com.beiyun.workers.interf;

import android.view.View;


import com.beiyun.workers.entity.GrowTobaccoInfo;

import java.util.List;

/**
 * Created by mpb on 2016/6/24.
 */
public interface IGrowTobaccoInfoFormLayout {

    void add();

    void reduce(View view);

    List<GrowTobaccoInfo> getGrowTobaccoInfo();

    void setGrowTobaccoInfo(List<GrowTobaccoInfo> info);
    String convertToString();
}
