package com.beiyun.workers.adapter;

import android.support.annotation.Nullable;

import com.beiyun.workers.R;
import com.beiyun.workers.entity.SearchPublicEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SearchPublicAdapter extends BaseQuickAdapter<SearchPublicEntity,BaseViewHolder> {

    public SearchPublicAdapter(@Nullable List<SearchPublicEntity> data) {
        super(R.layout.item_search_public,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchPublicEntity item) {
        helper.setText(R.id.item_search_public_companyName,item.getPublicCompanyName())
                .setText(R.id.item_search_public_publicTitle,item.getPublicTitle())
                .setText(R.id.item_search_public_publicTime,item.getPublicTime());


    }
}
