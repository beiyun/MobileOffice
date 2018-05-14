package com.beiyun.workers.okhttp.request;

import android.util.Log;


import com.beiyun.workers.okhttp.OkHttpManager;
import com.beiyun.workers.okhttp.callback.CallBack;

import java.util.HashMap;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by beiyun on 2016/8/4.
 *
 */
public abstract class OkHttpRequest {

    protected HashMap<String,String> params;
    protected HashMap<String,String> headers;
    protected String tag;
    protected String url;

    protected okhttp3.Request.Builder builder = new okhttp3.Request.Builder();

    private static final String TAG = "OkHttpRequest";

    public OkHttpRequest(HashMap<String,String> params, HashMap<String,String> headers, String tag, String url) {
        this.params = params;
        this.headers = headers;
        this.tag = tag;
        this.url = url;
        Log.d(TAG, "OkHttpRequest: url ="+url);

        initBuilder();
    }

    private void initBuilder() {
        builder.url(url).tag(tag);
        appendHeaders();
    }

    private void appendHeaders() {

        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) {
            return;
        }

        for (String key : headers.keySet()){
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());

    }

    public abstract RequestBody buildRequestBody();

    public abstract Request buildRequest(RequestBody requestBody);

    public RequestBody buildProgressRequestBody(RequestBody requestBody,CallBack callBack){
        return requestBody;
    }

    public void execute(CallBack callBack){

        Request request = generateRequest(callBack);
        OkHttpManager.getInstance().execute(request,callBack);
    }

    private Request generateRequest(CallBack callBack) {
        RequestBody requestBody = buildRequestBody();
        RequestBody progressRequestBody = buildProgressRequestBody(requestBody,callBack);

        Log.d(TAG, "generateRequest: ");
        return buildRequest(progressRequestBody);
    }

}
