package com.beiyun.workers.adapter;

import android.support.annotation.Nullable;

import com.beiyun.workers.R;
import com.beiyun.workers.entity.MessageEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by beiyun on 2018/4/1.
 * Workers
 */
public class MessageFragAdapter extends BaseQuickAdapter<MessageEntity,BaseViewHolder> {
    public MessageFragAdapter(@Nullable List<MessageEntity> data) {
        super(R.layout.item_message_adapter,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageEntity item) {
        helper.setText(R.id.item_message_title,item.getTitle());
        helper.setText(R.id.item_message_date,item.getDate());
        SimpleDraweeView image = helper.getView(R.id.item_message_image);
        image.setImageURI(UriUtil.getUriForResourceId(item.getImageRes()));
    }
}
