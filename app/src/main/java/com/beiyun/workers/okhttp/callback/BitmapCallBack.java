package com.beiyun.workers.okhttp.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import okhttp3.Response;

/**
 * Created by beiyun on 2016/8/4.
 * 所有回调均属于UI线程可直接更新UI
 */
public abstract class BitmapCallBack extends CallBack<Bitmap> {
    @Override
    public Bitmap parseResponse(Response response) {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }
}
