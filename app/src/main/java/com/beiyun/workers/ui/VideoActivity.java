package com.beiyun.workers.ui;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beiyun.library.anot.Receiver;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Windows;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.entity.LearnPage1Entity;
import com.beiyun.workers.entity.LearnPage2Entity;
import com.beiyun.workers.view.MyVideoPlayer;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

@Receiver
public class VideoActivity extends BaseActivity {

    @BindView(R.id.video_activity_player)
    MyVideoPlayer videoPlayer;
    @BindView(R.id.video_activity_title)
    TextView videoTitle;
    @BindView(R.id.video_activity_play_times)
    TextView videoPlayTimes;
    @BindView(R.id.video_activity_author)
    TextView videoAuthor;
    @BindView(R.id.video_activity_upload_date)
    TextView videoUploadDate;
    @BindView(R.id.video_activity_text)
    TextView videoText;
    @BindView(R.id.video_activity_back)
    ImageView videoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        useExploderAnim();
        initVideoPlayer();
//        Windows.setStatusBarColor(android.R.color.black);


    }

    private void initVideoPlayer() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Windows.setStatusBarColor(android.R.color.black);
        }


    }


    @Subscribe
    public void dataForLearnFragPage1(LearnPage1Entity entity) {
        Logs.e("VideoActivity dataForLearnFragPage1:" + "" + entity);
        videoPlayer.setUp(entity.getVideoUrl(),
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, entity.getTitle());
//        videoPlayer.thumbImageView.setImageResource(entity.getImageRes());
        Glide.with(this).load(entity.getThumbImageUrl()).into(videoPlayer.thumbImageView);
        videoPlayer.post(new Runnable() {
            @Override
            public void run() {
                videoPlayer.startVideo();
            }
    });
        videoPlayer.titleTextView.setMaxEms(10);
        videoPlayer.titleTextView.setEllipsize(TextUtils.TruncateAt.END);
        videoPlayer.backButton.setImageResource(R.drawable.ic_chevron_left_white_36dp);
        videoPlayer.titleTextView.setVisibility(View.GONE);
        videoAuthor.setText(entity.getAuthor());
        videoTitle.setText(entity.getTitle());
        videoPlayTimes.setText(entity.getPlayTimes() + "次");
        videoUploadDate.setText(entity.getUploadDate());
        videoText.setText(entity.getContent());
        videoPlayer.thumbImageView.setImageResource(entity.getImageRes());
    }

    @Subscribe
    public void dataForLearnFragPage2(LearnPage2Entity entity) {
        Logs.e("VideoActivity dataForLearnFragPage2:" + "" + entity);
        videoPlayer.setUp(entity.getVideoUrl(),
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, entity.getTitle());
        videoPlayer.titleTextView.setVisibility(View.GONE);
//        videoPlayer.backButton.setImageResource(R.drawable.ic_chevron_left_white_36dp);
        Glide.with(this).load(entity.getThumbImageUrl()).into(videoPlayer.thumbImageView);
        videoPlayer.thumbImageView.setImageResource(entity.getImageRes());
        videoPlayer.post(new Runnable() {
            @Override
            public void run() {
                videoPlayer.startVideo();
            }
        });
        videoPlayer.titleTextView.setMaxEms(10);
        videoPlayer.titleTextView.setEllipsize(TextUtils.TruncateAt.END);
        videoAuthor.setText(entity.getAuthor());
        videoTitle.setText(entity.getTitle());
        videoPlayTimes.setText(entity.getPlayTimes() + "次");
        videoUploadDate.setText(entity.getUploadDate());
        videoText.setText(entity.getContent());
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @OnClick(R.id.video_activity_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
