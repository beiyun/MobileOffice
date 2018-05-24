package com.beiyun.workers.ui;

import android.os.Bundle;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.beiyun.library.anot.Receiver;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.util.Logs;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.User;
import com.beiyun.workers.okhttp.OkHttpUtils;
import com.beiyun.workers.okhttp.callback.StringCallBack;
import com.beiyun.workers.utils.MD5Util;
import com.dd.processbutton.iml.SubmitProcessButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
@Receiver
public class UpdatePasswordStep2Activity extends BaseActivity {

    @BindView(R.id.update_password_step2_newPassword)
    MaterialEditText updatePasswordStep2NewPassword;
    @BindView(R.id.update_password_step2_newPassword_again)
    MaterialEditText updatePasswordStep2NewPasswordAgain;
    @BindView(R.id.update_password_step2_submit)
    SubmitProcessButton updatePasswordStep2Submit;
    private String mUserNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password_step2);
        ButterKnife.bind(this);
        userSlideEndAnimWithoutActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("设置新密码");
    }

    @OnClick(R.id.update_password_step2_submit)
    public void onViewClicked() {
        String pa1 = updatePasswordStep2NewPassword.getText().toString();
        String pa2 = updatePasswordStep2NewPasswordAgain.getText().toString();

        if(TextUtils.isEmpty(pa1)){
            updatePasswordStep2NewPassword.setError("请输入新密码");
            return;
        }

        if(TextUtils.isEmpty(pa2)){
            updatePasswordStep2NewPasswordAgain.setError("请输入新密码");
            return;
        }

        if(!pa1.equals(pa2)){
            updatePasswordStep2NewPasswordAgain.setError("两次密码输入不一致");
            return;
        }

        final MaterialDialog dialog = showProgressDialog();

        HashMap<String,String> params = new HashMap<>();
        params.put("bo.password", MD5Util.getMD5(pa1));
        params.put("bo.userNumber",mUserNumber);
        OkHttpUtils.getQuery(AppUrl.get().UPDATE_PASSWORD_STEP_2, params, new StringCallBack() {
            @Override
            public void onFailure(IOException e) {
                dialog.dismiss();
                Logs.e(e.getMessage());
                toastError("请求异常");
            }

            @Override
            public void onResponse(String response) throws IOException {
                Logs.e(response);
                dialog.dismiss();
                try {
                    JSONObject object = JSONObject.parseObject(response);
                    int resultCode = object.getIntValue("resultCode");
                    String reason = object.getString("reason");
                    if(resultCode == 100){
                        toastSuccess(reason);
                    }else{
                        toastError(reason);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    @Subscribe
    public void receive(String userNumber){
        mUserNumber = userNumber;
    }
}
