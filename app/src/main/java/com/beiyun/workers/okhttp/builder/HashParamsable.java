package com.beiyun.workers.okhttp.builder;

import java.util.HashMap;

/**
 * Created by beiyun on 2016/8/5.
 *
 */
public interface HashParamsable {

    OkHttpRequestBuilder params(HashMap<String, String> params);
    OkHttpRequestBuilder addParams(String key, String val);
}
