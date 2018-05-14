package com.beiyun.workers.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by beiyun on 2016/8/4.
 *所有回调均属于UI线程可直接更新UI
 */
public abstract class CallBack<T>{

    public abstract T parseResponse(Response response) throws IOException;

    public abstract void onFailure(IOException e);

    public abstract void onResponse(T response) throws IOException;

    public void inProgress(float progress, long total,boolean hasDone){}


}
