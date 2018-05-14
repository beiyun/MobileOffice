package com.beiyun.workers.okhttp.request;

import android.util.Log;


import com.beiyun.workers.okhttp.builder.PostBuilder;
import com.beiyun.workers.okhttp.callback.CallBack;

import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by beiyun on 2016/8/4.
 *
 */
public class PostRequest extends OkHttpRequest {

    private static final String TAG = "PostRequest";

    private ArrayList<PostBuilder.FileInput> files;

    public PostRequest(ArrayList<PostBuilder.FileInput> files, HashMap<String, String> params, HashMap<String, String> headers, String tag, String url) {
        super(params, headers, tag, url);
        this.files = files;
    }

    @Override
    public RequestBody buildRequestBody() {
        Log.e(TAG, "buildRequestBody: "+files);

        if (files == null || files.isEmpty()) {
            Log.d(TAG, "buildRequestBody: "+files);
            FormBody.Builder builder = new FormBody.Builder();
            addParams(builder);
            return builder.build();
        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            addParams(builder);

            for (int i = 0; i < files.size(); i++) {
                PostBuilder.FileInput fileInput = files.get(i);
                RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileInput.filename)), fileInput.file);
                builder.addFormDataPart(fileInput.key, fileInput.filename, fileBody);
            }
            return builder.build();
        }
    }

    private String guessMimeType(String path) {

        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = null;
        try {
            contentTypeFor = fileNameMap.getContentTypeFor(URLEncoder.encode(path, "UTF-8"));
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }

        return contentTypeFor;
    }

    private void addParams(MultipartBody.Builder builder) {

        if (params != null && !params.isEmpty()) {

            for (String key : params.keySet()) {

                if(key != null && params.get(key) != null){
                    builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                            RequestBody.create(null, params.get(key)));
                }
            }
        }

    }

    private void addParams(FormBody.Builder builder) {
        if (params != null) {
            for (String key : params.keySet()) {
                if (params.get(key) != null)
                    builder.add(key, params.get(key));
            }
        }


    }

    @Override
    public Request buildRequest(RequestBody requestBody) {
        return builder.post(requestBody).build();
    }

    @Override
    public RequestBody buildProgressRequestBody(RequestBody requestBody, final CallBack callBack) {

        if (callBack == null) {
            return requestBody;
        }

        ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, new ProgressRequestBody.Listener() {
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength, boolean hasDone) {

                callBack.inProgress(bytesWritten * 1.0f / contentLength, contentLength, hasDone);

            }
        });

        return progressRequestBody;
    }
}
