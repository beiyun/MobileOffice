package com.beiyun.workers.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.beiyun.library.anot.Receiver;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.util.Apps;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.entity.MessageEntity;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

@Receiver
public class MessageDetailActivity extends BaseActivity {

    private ActionBar actionBar;
    private SimpleDraweeView titleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        userSlideEndAnimWithoutActionBar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        titleImage = (SimpleDraweeView) findViewById(R.id.message_list_detail_title_image);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Subscribe
    public void receive(MessageEntity entity){
        actionBar.setTitle(Apps.getAppName());
        titleImage.setImageURI(UriUtil.getUriForResourceId(entity.getImageRes()));
    }
}
