package com.beiyun.workers.entity;

import com.beiyun.workers.adapter.WorkFragPage2Adapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by beiyun on 2018/4/6.
 * Workers
 */
public class WorkFrag2ExpandReasonEntity implements MultiItemEntity {

    private String noneCompleteReason;

    public WorkFrag2ExpandReasonEntity(String noneCompleteReason) {
        this.noneCompleteReason = noneCompleteReason;
    }

    public String getNoneCompleteReason() {
        return noneCompleteReason;
    }

    @Override
    public int getItemType() {
        return WorkFragPage2Adapter.TYPE_LEVEL_1;
    }
}
