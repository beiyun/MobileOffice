package com.beiyun.workers.okhttp.helper;

import java.util.List;

/**
 * Created by beiyun on 2016/8/9.
 *
 */
public class ResponseData<T> {

    private String total;//条数

    private List<T> list;//list

    public List<T> getList() {
        return list;
    }

    public String getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "slist=" + list +
                ", total='" + total + '\'' +
                '}';
    }
}
