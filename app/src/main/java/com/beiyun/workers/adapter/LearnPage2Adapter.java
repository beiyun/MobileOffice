package com.beiyun.workers.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;

import com.beiyun.library.util.Sizes;
import com.beiyun.workers.R;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.LearnPage2Entity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by beiyun on 2018/4/9.
 * Workers
 */
public class LearnPage2Adapter extends BaseQuickAdapter<LearnPage2Entity,BaseViewHolder> {

    public LearnPage2Adapter(@Nullable List<LearnPage2Entity> data) {
        super(R.layout.item_learn_page2,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LearnPage2Entity item) {
        helper.setText(R.id.item_learn_page2_play_times,item.getPlayTimes()+"次")
                .setText(R.id.item_learn_page2_title,item.getTitle())
                .setText(R.id.item_learn_page2_upload_date,item.getUploadDate())
                .setText(R.id.item_learn_page2_video_length,item.getVideoLength());
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.item_learn_page2_image);
        simpleDraweeView.setImageURI(AppUrl.get().BASE_IMAGE_URL + item.getThumbImageUrl());
//        simpleDraweeView.setImageURI(UriUtil.getUriForResourceId(item.getImageRes()));
        if(helper.getAdapterPosition() == 0 || helper.getAdapterPosition() ==1){
            CardView cardView = helper.getView(R.id.item_learn_page2_cardView);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cardView.getLayoutParams();
            layoutParams.topMargin = Sizes.dp2px(10);
            layoutParams.bottomMargin = Sizes.dp2px(4);
            layoutParams.leftMargin = Sizes.dp2px(4);
            layoutParams.rightMargin = Sizes.dp2px(4);
            cardView.setLayoutParams(layoutParams);

        }
    }
}
