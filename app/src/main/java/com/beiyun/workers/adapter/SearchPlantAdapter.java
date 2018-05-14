package com.beiyun.workers.adapter;

import android.support.annotation.Nullable;

import com.beiyun.workers.R;
import com.beiyun.workers.entity.SearchPlantEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SearchPlantAdapter extends BaseQuickAdapter<SearchPlantEntity,BaseViewHolder> {

    public SearchPlantAdapter(@Nullable List<SearchPlantEntity> data) {
        super(R.layout.item_search_plant,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchPlantEntity item) {
        helper.setText(R.id.item_search_plant_name,item.getPlantName())
                .setText(R.id.item_search_plant_call,item.getPlantCall());

    }
}
