package com.beiyun.workers.entity;

import com.beiyun.workers.adapter.WorkFragPage2Adapter;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by beiyun on 2018/4/6.
 * Workers
 */
public class WorkFrag2Entity extends AbstractExpandableItem<WorkFrag2ExpandReasonEntity> implements MultiItemEntity{

    private String name;
    private boolean complete;

    private String completeTime;

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int getItemType() {
        return WorkFragPage2Adapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
