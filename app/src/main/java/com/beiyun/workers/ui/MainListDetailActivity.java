package com.beiyun.workers.ui;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.beiyun.library.anot.Receiver;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.base.App;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Sizes;
import com.beiyun.library.util.Windows;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.News;
import com.beiyun.workers.web.SonicRuntimeImpl;
import com.beiyun.workers.web.SonicSessionClientImpl;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.PriorityThreadFactory;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Receiver
public class MainListDetailActivity extends BaseActivity {

    SimpleDraweeView imageView;
    private ActionBar actionBar;
    private SonicSession sonicSession;
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        setContentView(R.layout.activity_main_list_detail);
        userSlideEndAnimWithoutActionBar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        imageView = findViewById(R.id.main_list_detail_title_image);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Logs.e("------toolbarheight----" + Sizes.px2dp(Windows.getActionBarHeight()) + "------statusBarHeight-----" + Sizes.px2dp(Windows.getStatusBarHeight()));

            }
        });
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
        webView = (WebView) findViewById(R.id.main_list_detail_webView);
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

        progressBar = findViewById(R.id.main_list_detail_progress);

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


    @Subscribe
    public void receive(News entity) {
        Logs.e("receive  " + entity);
        actionBar.setTitle(entity.getPlate());
//        imageView.setImageURI(UriUtil.getUriForResourceId(entity.getTitleImage()));
        imageView.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getImg()));
        initWebView(AppUrl.get().BASE_URL+entity.getContent());
//        initWebView(entity.getContent());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
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
