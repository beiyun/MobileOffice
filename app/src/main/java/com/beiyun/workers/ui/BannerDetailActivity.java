package com.beiyun.workers.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.beiyun.library.util.Logs;
import com.beiyun.library.anot.Receiver;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.constant.AppUrl;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
@Receiver
public class BannerDetailActivity extends BaseActivity {

    @BindView(R.id.bannerDetailImage)
    SimpleDraweeView bannerDetailImage;
    private static final String TAG = "BannerDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_detail);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("广告详情");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Subscribe
    public void receive(String url){
        Uri parse = Uri.parse(AppUrl.get().BASE_IMAGE_URL + url);
        Logs.e("BannerDetailActivity receive url = "+ parse.toString());
        Log.e(TAG, "receive: "+"BannerDetailActivity receive url = "+ parse.toString());
        bannerDetailImage.setImageURI(parse);
    }

}


