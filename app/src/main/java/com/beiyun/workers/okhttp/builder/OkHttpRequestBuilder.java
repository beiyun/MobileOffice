package com.beiyun.workers.okhttp.builder;


import com.beiyun.workers.okhttp.request.OkHttpRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by beiyun on 2016/8/4.
 *
 */
public abstract class OkHttpRequestBuilder<T extends OkHttpRequestBuilder>  {

    protected HashMap<String,String> params;
    protected HashMap<String,String> headers;
    protected String url;
    protected String tag;

    public abstract OkHttpRequest build();

    public T addHeader(String key,String value){
        if (headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(key,value);
        return (T) this;
    }

    public T headers(HashMap<String,String> headers){
        this.headers= headers;
        return (T) this;
    }

    public T url(String url){
        this.url = url;
        return (T) this;
    }

    public T tag(String tag){
        this.tag = tag;
        return (T) this;
    }

}
