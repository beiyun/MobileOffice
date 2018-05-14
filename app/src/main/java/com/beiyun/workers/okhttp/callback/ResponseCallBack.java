package com.beiyun.workers.okhttp.callback;

import android.util.Log;

import com.beiyun.workers.okhttp.helper.ResponseObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beiyun on 2016/8/8.
 *查询回调
 */
public abstract class ResponseCallBack<T> extends StringCallBack {

    private static final String TAG = "ResponseCallBack";
    private Class<T> clazz;

    public ResponseCallBack(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onResponse(String response) throws IOException {
        Log.d(TAG, "onResponse: "+response);
        if (response == null) {
            onFailure(new IOException("返回JSON数据为空"));
            return;
        }

        Gson gson = new GsonBuilder().serializeNulls().create();
        ResponseObject object;
        try {
            object = gson.fromJson(response, new TypeToken<ResponseObject>(){}.getType());
        }catch (Exception e){
            onFailure(new IOException("返回JSON格式错误"));
            return;
        }

        if (object == null){
            onFailure(new IOException("返回JSON数据解析异常"));
            return;
        }

        //数据错误
        if(object.getResultCode() !=100){
            onFailure(new IOException(object.getReason()));
            return;
        }

        List slist = object.getData().getList();
        if (slist == null) {
            onFailure(new IOException("返回数据列表为空"));
            return;
        }

        if(slist.size() == 0){
            onFailure(new IOException("返回数据列表为空"));
            return;
        }

        List<T> list = new ArrayList<>();
        JsonArray array = new JsonParser().parse(slist.toString()).getAsJsonArray();
        for(final JsonElement elem : array){
            list.add(gson.fromJson(elem,clazz));
        }

        //解析成功
        if(list.size() != 0){
            onSuccess(list);
        }


    }

    protected abstract void onSuccess(List<T> data);

}
