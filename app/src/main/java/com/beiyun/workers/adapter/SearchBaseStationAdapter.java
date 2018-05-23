package com.beiyun.workers.adapter;

import android.support.annotation.Nullable;

import com.beiyun.workers.R;
import com.beiyun.workers.entity.BaseStationEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SearchBaseStationAdapter extends BaseQuickAdapter<BaseStationEntity,BaseViewHolder> {

    public SearchBaseStationAdapter(@Nullable List<BaseStationEntity> data) {
        super(R.layout.item_search_plant,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseStationEntity item) {
        helper.setText(R.id.item_search_plant_name,item.getHeader())
                .setText(R.id.item_search_plant_call,item.getLinkTel());
    }
}
