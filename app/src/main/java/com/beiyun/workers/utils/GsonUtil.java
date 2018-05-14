package com.beiyun.workers.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class GsonUtil<T> {

    public static <T> T parseJson(String json,TypeToken<T> token){
        return new Gson().fromJson(json,token.getType());
    }
}
