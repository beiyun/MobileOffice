package com.beiyun.workers.interf;

import android.view.View;


import com.beiyun.workers.entity.TGBasicInfoItemEntity;

import java.util.List;

/**
 * Created by 中旗 on 2016/11/15.
 */
public interface ItemtgbasicInfoLayou {

    void add();

    void del(View view);

    List<TGBasicInfoItemEntity> getInfo();

    void setInfo(List<TGBasicInfoItemEntity> info);

    String convertToString();

}
