package com.beiyun.workers.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by beiyun on 2016/8/4.
 *
 */
public abstract class StringCallBack extends CallBack<String> {

    @Override
    public String parseResponse(Response response) throws IOException {
        return response.body().string();
    }
}
