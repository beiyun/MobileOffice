package com.beiyun.workers.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beiyun.library.anot.Receiver;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Windows;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.LearnPage1Entity;
import com.beiyun.workers.entity.LearnPage2Entity;
import com.beiyun.workers.view.MyVideoPlayer;
import com.beiyun.workers.web.SonicRuntimeImpl;
import com.beiyun.workers.web.SonicSessionClientImpl;
import com.bumptech.glide.Glide;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    @BindView(R.id.video_activity_back)
    ImageView videoBack;
    private SonicSession sonicSession;
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        useExploderAnim();
        initVideoPlayer();


    }


    private void initWebView(String url) {


        // step 1: Initialize sonic engine if necessary, or maybe u can do this when application created
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(getApplication()), new SonicConfig.Builder().build());
        }

        SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
        sessionConfigBuilder.setSupportLocalServer(true);

        SonicSessionClientImpl sonicSessionClient = null;

        // step 2: Create SonicSession
        sonicSession = SonicEngine.getInstance().createSession(url, new SonicSessionConfig.Builder().build());
        if (null != sonicSession) {
            sonicSessionClient = new SonicSessionClientImpl();
            sonicSession.bindClient(sonicSessionClient);
        } else {
            // this only happen when a same sonic session is already running,
            // u can comment following codes to feedback as a default mode.
//            throw new UnknownError("create session fail!");
        }

        // step 3: BindWebView for sessionClient and bindClient for SonicSession
        // in the real world, the init flow may cost a long time as startup
        // runtime、init configs....
        webView = (WebView) findViewById(R.id.video_list_detail_webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (sonicSession != null) {
                    sonicSession.getSessionClient().pageFinish(url);
                }
            }




            @TargetApi(21)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return shouldInterceptRequest(view, request.getUrl().toString());
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (sonicSession != null) {
                    //step 6: Call sessionClient.requestResource when host allow the application
                    // to return the local data .
                    return (WebResourceResponse) sonicSession.getSessionClient().requestResource(url);
                }
                return null;
            }
        });

        progressBar = findViewById(R.id.video_list_detail_progress);

        updateProgress(1,90);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Logs.e("onProgressChanged >"+newProgress);
                if(newProgress == 100){
                    updateProgress(90,100);
                }

            }


        });


        WebSettings webSettings = webView.getSettings();

        // step 4: bind javascript
        // note:if api level lower than 17(android 4.2), addJavascriptInterface has security
        // issue, please use x5 or see https://developer.android.com/reference/android/webkit/
        // WebView.html#addJavascriptInterface(java.lang.Object, java.lang.String)
        webSettings.setJavaScriptEnabled(true);

        // init webview settings
        webSettings.setAllowContentAccess(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        // 设置显示缩放按钮
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(true);
//        webSettings.setTextZoom(150);



        // step 5: webview is ready now, just tell session client to bind
        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(webView);
            sonicSessionClient.clientReady();
        } else { // default mode
            webView.loadUrl(url);
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == 0){
                progressBar.setProgress((Integer) msg.obj);
            }else if(msg.what == 1){
                progressBar.setVisibility(View.GONE);
            }
            return true;
        }
    });

    ExecutorService service = Executors.newSingleThreadExecutor();

    private void updateProgress(final int startPosition, final int position) {


        service.execute(new Runnable() {
            @Override
            public void run() {

                for (int i = startPosition; i <= position; i++) {
                    try {
                        Thread.sleep(10);
                        handler.obtainMessage(0,i).sendToTarget();
                        if(i == 100){
                            handler.sendEmptyMessage(1);
                            service.shutdownNow();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }



    private void initVideoPlayer() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Windows.setStatusBarColor(android.R.color.black);
        }


    }


    @Subscribe
    public void dataForLearnFragPage1(LearnPage1Entity entity) {
        Logs.e("VideoActivity dataForLearnFragPage1:" + "" + entity);
        videoPlayer.setUp(AppUrl.get().BASE_IMAGE_URL +entity.getVideoUrl(),
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, entity.getTitle());
        Glide.with(this).load(AppUrl.get().BASE_IMAGE_URL + entity.getThumbImageUrl()).into(videoPlayer.thumbImageView);
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
        initWebView(AppUrl.get().BASE_URL+entity.getContent());
    }

    @Subscribe
    public void dataForLearnFragPage2(LearnPage2Entity entity) {
        Logs.e("VideoActivity dataForLearnFragPage2:" + "" + entity);
        videoPlayer.setUp(AppUrl.get().BASE_IMAGE_URL +entity.getVideoUrl(),
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, entity.getTitle());
        videoPlayer.titleTextView.setVisibility(View.GONE);
        videoPlayer.backButton.setImageResource(R.drawable.ic_chevron_left_white_36dp);
        Glide.with(this).load(AppUrl.get().BASE_IMAGE_URL + entity.getThumbImageUrl()).into(videoPlayer.thumbImageView);
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
        initWebView(AppUrl.get().BASE_URL+entity.getContent());
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

    @Override
    protected void onDestroy() {
        if (null != sonicSession) {
            sonicSession.destroy();
            sonicSession = null;
        }
        super.onDestroy();
    }
}
