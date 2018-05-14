package com.beiyun.workers.okhttp;



import com.beiyun.library.util.Logs;
import com.beiyun.workers.okhttp.callback.BitmapCallBack;
import com.beiyun.workers.okhttp.callback.CallBack;
import com.beiyun.workers.okhttp.callback.FileCallBack;
import com.beiyun.workers.okhttp.helper.DownLoadHelper;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by beiyun on 2016/8/8.
 */
public class OkHttpUtils {


    public static void getQuery(String url, HashMap<String, String> params, CallBack callBack) {
        getUpload(url, params, callBack);
    }

    public static void getUpload(String url, HashMap<String, String> params, CallBack callBack) {
        executeGet(url, params, callBack);
    }

    public static void postQuery(String url, HashMap<String, String> params, CallBack callBack) {
        postUpload(url, params, callBack);
    }

    public static void postUpload(String url, HashMap<String, String> params, CallBack callBack) {
        executePost(url, params, callBack);
    }

    public static void bitmap(String url, BitmapCallBack callBack) {
        executeGet(url, null, callBack);
    }

    public static DownLoadHelper downLoad(String url, FileCallBack fileCallBack) {
        return OkHttpManager.downLoad(url, fileCallBack);
    }

    private static void executeGet(String url, HashMap<String, String> params, CallBack callBack) {
        OkHttpManager.get().url(url).params(params).build().execute(callBack);
    }

    private static void executePost(String url, HashMap<String, String> params, CallBack callBack) {
        OkHttpManager.post().url(url).params(params).build().execute(callBack);
    }

    public static HashMap<String, String> generate(Object obj) {
        HashMap<String, String> map = new HashMap<>();
        if (obj != null) {
            Field[] fields = obj.getClass().getDeclaredFields();
            if (fields.length != 0) {
                for (Field field : fields) {
                    try {
                        field.setAccessible(true);
                        String name = field.getName();
                        String value = (String) field.get(obj);
                        map.put("bo." + name, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        Logs.e("OkHttpUtils generate map = "+map.toString());
        return map;
    }


    public static HashMap<String, String> generateQV(Object obj) {
        HashMap<String, String> map = new HashMap<>();
        if (obj != null) {
            Field[] fields = obj.getClass().getDeclaredFields();
            if (fields.length != 0) {
                for (Field field : fields) {
                    try {
                        String name = field.getName();
                        String value = (String) field.get(obj);
                        map.put("qv." + name, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return map;
    }
}
