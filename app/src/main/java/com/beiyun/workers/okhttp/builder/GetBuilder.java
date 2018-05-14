package com.beiyun.workers.okhttp.builder;

import android.net.Uri;
import android.util.Log;


import com.beiyun.workers.okhttp.request.GetRequest;
import com.beiyun.workers.okhttp.request.OkHttpRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;


/**
 * Created by beiyun on 2016/8/4.
 *
 */
public class GetBuilder extends OkHttpRequestBuilder<GetBuilder> implements HashParamsable {

    private static final String TAG = "GetBuilder";

    @Override
    public OkHttpRequest build() {
        if (params != null) {
            String appendedUrl = appendParams(url,params);
            Log.d(TAG, "build: url = "+url);
            Log.d(TAG, "build: appendedUrl = "+appendedUrl);
            Log.d(TAG, "build: params = "+params.get("type")+"----"+params.get("code"));
            return new GetRequest(params,headers,tag,appendedUrl);
        }

        return new GetRequest(params,headers,tag,url);
    }

    @Override
    public GetBuilder addParams(String key,String value){
        if (params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key,value);
        return this;
    }

    @Override
    public GetBuilder params(HashMap<String,String> params){
        this.params = params;
        return this;
    }

    protected String appendParams(String url, HashMap<String, String> params) {

        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }
}
