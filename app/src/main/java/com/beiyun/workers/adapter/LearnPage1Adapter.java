package com.beiyun.workers.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;

import com.beiyun.library.util.Sizes;
import com.beiyun.workers.R;
import com.beiyun.workers.entity.LearnPage1Entity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by beiyun on 2018/4/9.
 * Workers
 */
public class LearnPage1Adapter extends BaseQuickAdapter<LearnPage1Entity,BaseViewHolder> {

    public LearnPage1Adapter(@Nullable List<LearnPage1Entity> data) {
        super(R.layout.item_learn_page1,data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final LearnPage1Entity item) {
        helper.setText(R.id.item_learn_page1_author,item.getAuthor())
                .setText(R.id.item_learn_page1_play_times,item.getPlayTimes()+"次")
                .setText(R.id.item_learn_page1_subTitle,item.getSubTitle())
                .setText(R.id.item_learn_page1_title,item.getTitle())
                .setText(R.id.item_learn_page1_video_length,item.getVideoLength());
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.item_learn_page1_image);
        simpleDraweeView.setImageURI(item.getThumbImageUrl());


//        simpleDraweeView.setImageURI(UriUtil.getUriForResourceId(item.getImageRes()));
        if(helper.getAdapterPosition() == 0){
            CardView cardView = helper.getView(R.id.item_learn_page1_cardView);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cardView.getLayoutParams();
            layoutParams.topMargin = Sizes.dp2px(10);
            layoutParams.bottomMargin = Sizes.dp2px(10);
            cardView.setLayoutParams(layoutParams);

        }


    }


}
