package com.beiyun.workers.okhttp.helper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.beiyun.library.util.Logs;
import com.beiyun.workers.okhttp.OkHttpManager;
import com.beiyun.workers.okhttp.callback.CallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by beiyun on 2016/8/8.
 */
public class OkHttpHelper {

    private static final String TAG = "OkHttpHelper";
    public static final int FAILURE = 0x01;
    public static final int SUCCESS = 0X00;
    private CallBack mCallBack;

    private Handler handler = new Handler(Looper.getMainLooper(),new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == FAILURE && mCallBack != null) {
                mCallBack.onFailure((IOException) msg.obj);
            } else if (msg.what == SUCCESS && mCallBack != null) {
                try {
                    mCallBack.onResponse(msg.obj);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    });


    public OkHttpHelper(Request request, final CallBack callBack) {
        this.mCallBack = callBack;
        execute(request);
    }


    private void execute(Request request) {
        final OkHttpClient client = OkHttpManager.getInstance().getOkHttpClient();
        Log.d(TAG, "execute: ");
        if (request == null) {
            return;
        }
        if (client == null) {
            return;
        }

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                if (e.getMessage() == null) {
                    handler.obtainMessage(FAILURE, new IOException("服务器连接异常")).sendToTarget();
                    return;
                }
                handler.obtainMessage(FAILURE, e).sendToTarget();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logs.e("onResponse: ");
                if (mCallBack != null) {
                    Object o = mCallBack.parseResponse(response);
                    handler.obtainMessage(SUCCESS, o).sendToTarget();
                    call.cancel();
                }
            }
        });
    }
}
