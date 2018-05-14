package com.beiyun.workers.okhttp;


import com.beiyun.workers.okhttp.builder.GetBuilder;
import com.beiyun.workers.okhttp.builder.PostBuilder;
import com.beiyun.workers.okhttp.callback.CallBack;
import com.beiyun.workers.okhttp.callback.FileCallBack;
import com.beiyun.workers.okhttp.helper.DownLoadHelper;
import com.beiyun.workers.okhttp.helper.OkHttpHelper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by beiyun on 2016/8/4.
 *
 *
 */
public class OkHttpManager {

    private static final String TAG = "OkHttpManager";

    private static OkHttpManager mInstance;

    private OkHttpClient mOkHttpClient;

    public OkHttpManager(OkHttpClient okHttpClient){

        if(mOkHttpClient == null){

            mOkHttpClient = new OkHttpClient();

        }else{

            mOkHttpClient = okHttpClient;

        }

    }

    public static OkHttpManager getInstance(){

        return initClient(null);
    }

    public static OkHttpManager initClient(OkHttpClient okHttpClient){

        if(mInstance == null){

            synchronized (OkHttpManager.class){

                if(mInstance == null){

                    mInstance = new OkHttpManager(okHttpClient);
                }
            }
        }

        return mInstance;
    }

    public static OkHttpClient defaultClient(){

        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .build();

    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get(){
        return new GetBuilder();
    }

    public static PostBuilder post(){
        return new PostBuilder();
    }

    public OkHttpHelper execute(Request request, final CallBack callBack){
        return new OkHttpHelper(request,callBack);
    }

    public static DownLoadHelper downLoad(String url, FileCallBack fileCallBack){
        return new DownLoadHelper(url,fileCallBack);
    }



}
