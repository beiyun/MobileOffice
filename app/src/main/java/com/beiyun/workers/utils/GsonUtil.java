package com.beiyun.workers.utils;

import com.beiyun.workers.okhttp.callback.BaseInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class GsonUtil{

    public static Object parseJson(String json, TypeToken token) {
        Gson gson = new Gson();
        BaseInfo o = gson.fromJson(json, token.getType());
        return o.getData().getList();
    }

}
