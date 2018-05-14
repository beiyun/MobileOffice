package com.beiyun.workers.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.beiyun.workers.R;
import com.beiyun.workers.entity.Instructor;
import com.beiyun.workers.entity.PersonInfo;
import com.beiyun.workers.entity.SearchPersonEntity;
import com.beiyun.workers.entity.TGBasicInfoEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SearchPersonAdapter extends BaseQuickAdapter<TGBasicInfoEntity,BaseViewHolder> {

    public SearchPersonAdapter(@Nullable List<TGBasicInfoEntity> data) {
        super(R.layout.item_search_person,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TGBasicInfoEntity item) {
        if(item.getCategory() == 1){
            helper.setText(R.id.item_search_person_companyName,TextUtils.isEmpty(item.getUname())?"无":item.getUname())
                    .setText(R.id.item_search_person_personName,TextUtils.isEmpty(item.getNickname())?"无":item.getNickname())
                    .setText(R.id.item_search_person_call, TextUtils.isEmpty(item.getTel())?"无":item.getTel());
        }else{

            helper.setText(R.id.item_search_person_companyName,TextUtils.isEmpty(item.getVillage())?"无":item.getVillage())
                    .setText(R.id.item_search_person_personName,TextUtils.isEmpty(item.getName())?"无":item.getName())
                    .setText(R.id.item_search_person_call,TextUtils.isEmpty(item.getLinkTel())?"无":item.getLinkTel());
        }


    }
}
