package com.beiyun.workers.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import com.beiyun.library.util.Logs;

import com.beiyun.workers.R;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.MainFragListEntity;
import com.beiyun.workers.entity.News;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by beiyun on 2018/3/31.
 * Workers
 */
public class MainFragmentAdapter extends BaseQuickAdapter<News, BaseViewHolder> {


    public MainFragmentAdapter(@Nullable List<News> data) {
        super(R.layout.item_main_adapter,data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final News item) {

        SimpleDraweeView imageView = helper.getView(R.id.item_main_image);
        Logs.e("MainFragmentAdapter titleImage = "+item.getTitleImage());
        imageView.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL+item.getImg()));
//        imageView.setImageURI(UriUtil.getUriForResourceId(item.getTitleImage()));
        helper.setText(R.id.item_main_title,item.getTitle());
        helper.setText(R.id.item_main_author,item.getPlate());
        helper.setText(R.id.item_main_counter,item.getReadCount()+"热度");
        View view = helper.getView(R.id.item_main_fire);
        if(item.getReadCount() > 1000){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.INVISIBLE);
        }

    }


}
