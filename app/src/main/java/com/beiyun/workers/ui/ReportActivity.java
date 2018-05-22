package com.beiyun.workers.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.okhttp.callback.RequestCallBack;
import com.beiyun.workers.okhttp.helper.ResultData;
import com.beiyun.workers.utils.AppRequests;

import java.io.IOException;

public class ReportActivity extends BaseActivity {

    private EditText backContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        backContent = findViewById(R.id.backContent);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("意见反馈");
        }
    }

    public void submit(View view) {

        String content = backContent.getText().toString();
        if(TextUtils.isEmpty(content)){
            toastError("未添加任何反馈内容");
            return;
        }

        showProgressDialog();

        AppRequests.report(content, new RequestCallBack() {
            @Override
            public void success(ResultData data) {
                dismissProgressDialog();
                if(data.getResultCode() == 100){
                    toastSuccess("提交成功");
                }else{
                    toastError(data.getReason());
                }
            }

            @Override
            public void onFailure(IOException e) {
                super.onFailure(e);
                dismissProgressDialog();
            }
        });


    }
}
