package com.beiyun.workers.okhttp.helper;

/**
 * Created by beiyun on 2016/8/8.
 */
public class ResponseObject<T> {

    private int resultCode;//结果吗

    private String reason;//缘由

    private ResponseData<T> data;//data数据

    public ResponseData<T> getData() {
        return data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "data=" + data +
                ", resultCode='" + resultCode + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
