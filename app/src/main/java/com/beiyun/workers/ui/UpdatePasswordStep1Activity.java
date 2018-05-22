package com.beiyun.workers.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.beiyun.library.util.Logs;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.UpdatePasswordEntity;
import com.beiyun.workers.okhttp.OkHttpUtils;
import com.beiyun.workers.okhttp.callback.StringCallBack;
import com.beiyun.workers.utils.TestSimpleDataUtil;
import com.dd.processbutton.FlatButton;
import com.dd.processbutton.iml.SubmitProcessButton;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePasswordStep1Activity extends BaseActivity {

    @BindView(R.id.update_password_next_step)
    FlatButton updatePasswordNextStep;
    @BindView(R.id.update_password_step1_tel)
    MaterialEditText updatePasswordStep1Tel;
    @BindView(R.id.update_password_step1_code)
    MaterialEditText updatePasswordStep1Code;
    @BindView(R.id.update_password_step1_getCode)
    SubmitProcessButton updatePasswordStep1GetCode;
    @BindView(R.id.update_password_step1_userNumber)
    MaterialEditText updatePasswordStep1UserNumber;
    private UpdatePasswordEntity passwordEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password_step1);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("验证手机号");
        updatePasswordStep1UserNumber.setText(TestSimpleDataUtil.getUserNumber());
    }


    public void nextStep(View view) {
        String userNumber = updatePasswordStep1UserNumber.getText().toString();
        if(TextUtils.isEmpty(userNumber)){
            updatePasswordStep1UserNumber.setError("请输入账户名");
            return;
        }
        if (TextUtils.isEmpty(updatePasswordStep1Tel.getText()) || TextUtils.isEmpty(updatePasswordStep1Code.getText())
                || passwordEntity == null || passwordEntity.getVerifyCode() != Integer.valueOf(updatePasswordStep1Code.getText().toString())) {
            updatePasswordStep1Code.setError("验证码不正确");
            return;
        }

        com.beiyun.library.util.Events.post(userNumber);
        startActivity(new Intent(this, UpdatePasswordStep2Activity.class), updatePasswordNextStep);
    }


    @OnClick(R.id.update_password_step1_getCode)
    public void onViewClicked() {
        String userNumber = updatePasswordStep1UserNumber.getText().toString();
        String tel = updatePasswordStep1Tel.getText().toString();
        if(TextUtils.isEmpty(userNumber)){
            updatePasswordStep1UserNumber.setError("请输入账户名");
        }

        if (TextUtils.isEmpty(tel)) {
            updatePasswordStep1Tel.setError("请输入手机号");
            return;
        }

        final MaterialDialog dialog = showProgressDialog();
        HashMap<String, String> params = new HashMap<>();
        params.put("bo.tel",tel);
        params.put("bo.userNumber",userNumber);
        OkHttpUtils.getQuery(AppUrl.get().UPDATE_PASSWORD_STEP_1, params, new StringCallBack() {
            @Override
            public void onFailure(IOException e) {
                Logs.e(e.getMessage());
                dialog.dismiss();
            }

            @Override
            public void onResponse(String response) throws IOException {
                Logs.e(response);
                try {
                    passwordEntity = new Gson().fromJson(response, UpdatePasswordEntity.class);
                    if(passwordEntity.getResultCode() == 100){
                        updatePasswordStep1Code.setText(String.valueOf(passwordEntity.getVerifyCode()));
                        toastSuccess("验证码获取成功");
                    }else{
                        toastError(passwordEntity.getReason());
                    }

                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
