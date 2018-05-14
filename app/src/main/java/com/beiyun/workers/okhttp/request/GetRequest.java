package com.beiyun.workers.okhttp.request;

import java.util.HashMap;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by beiyun on 2016/8/4.
 *
 */
public class GetRequest extends OkHttpRequest {

    public GetRequest(HashMap<String,String> params,HashMap<String,String> headers,String tag,String url) {
        super(params,headers,tag,url);
    }

    @Override
    public RequestBody buildRequestBody() {
        return null;
    }

    @Override
    public Request buildRequest(RequestBody requestBody) {
        return builder.get().build();
    }

}
