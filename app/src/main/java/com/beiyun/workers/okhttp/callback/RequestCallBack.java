package com.beiyun.workers.okhttp.callback;

import android.util.Log;

import com.beiyun.library.util.Logs;
import com.beiyun.workers.okhttp.helper.ResultData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

/**
 * Created by zqht on 2016/8/30 14:44
 * Email:zmm534635184@sina.com
 * 上传回调
 */
public abstract class RequestCallBack extends StringCallBack {
    private static final String TAG = "RequestCallBack";
    @Override
    public void onResponse(String response) throws IOException {
        ResultData object;
        Log.e(TAG, "onResponse: "+response);
        try {
            object = new Gson().fromJson(response, new TypeToken<ResultData>() {
            }.getType());
            success(object);
        } catch (Exception e) {
            Logs.e("json 解析异常："+e.getMessage());
            onFailure(new IOException(e.getMessage()));
        }
    }

    @Override
    public void onFailure(IOException e) {

    }

    public abstract void success(ResultData data);
}
