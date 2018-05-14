package com.beiyun.workers.okhttp.callback;

import com.beiyun.library.util.Logs;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by beiyun on 2016/8/8.
 *查询回调
 */
public abstract class ResponseTCallBack<T> extends StringCallBack {
    private static final String TAG = "ResponseTCallBack";

    @Override
    public void onResponse(String response) throws IOException {
        Logs.e(response);
        Logs.e("onResponse: "+response);
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type beanType = parameterizedType.getActualTypeArguments()[0];
            try {
                onSuccess((T)new Gson().fromJson(response,beanType));
            }catch (Exception e){
                Logs.e("json 解析异常: "+e.getMessage());
            }
            }
        }

    protected abstract void onSuccess(T data);

}
