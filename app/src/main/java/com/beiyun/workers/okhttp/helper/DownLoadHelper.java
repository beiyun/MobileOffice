package com.beiyun.workers.okhttp.helper;



import com.beiyun.workers.okhttp.callback.FileCallBack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by beiyun on 2016/8/8.
 *
 */
public class DownLoadHelper {

    Call call;

    public DownLoadHelper(String url, final FileCallBack fileCallBack) {

        if (url == null) {
            return;
        }

        if (fileCallBack == null) {
            return;
        }

        OkHttpClient okHttpClient =  new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        final Request r = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(r).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                DownLoadHelper.this.call = call;
                fileCallBack.onFailure(e);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                DownLoadHelper.this.call = call;
                fileCallBack.onResponse(fileCallBack.parseResponse(response));
            }
        });
    }


    public void downLoadCancel(){
        if(call != null){
            call.cancel();
        }

    }

}
