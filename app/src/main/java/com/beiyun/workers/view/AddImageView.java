package com.beiyun.workers.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.beiyun.workers.R;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.BaseStationEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beiyun on 2016/9/13.x
 */
public class AddImageView extends LinearLayout {
    private static final String TAG = "AddImageView";

    private LinearLayout mImageGroup;

    public AddImageView(Context context) {
        this(context,null);
    }

    public AddImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(final Context context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert mInflater != null;
        View view = mInflater.inflate(R.layout.view_add_image_view, null);
        this.addView(view);
        mImageGroup = (LinearLayout) view.findViewById(R.id.view_add_image_group);

    }

    /**
     * 展示图片
     * @param images 得到图片的路径集合
     */
    public void setImages(ArrayList<BaseStationEntity.StationImages> images){

        if(images == null || images.isEmpty()) {
            return;
        }
        mImageGroup.removeAllViews();

        for (BaseStationEntity.StationImages a:images) {
            final SimpleDraweeView c = new SimpleDraweeView(getContext());
            c.setMinimumHeight(com.beiyun.library.util.Sizes.dp2px(100));
            c.setMinimumWidth(com.beiyun.library.util.Sizes.dp2px(120));
            c.setScaleType(ImageView.ScaleType.CENTER_CROP);
            c.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL +a.image));
            mImageGroup.addView(c);

        }
    }
}
