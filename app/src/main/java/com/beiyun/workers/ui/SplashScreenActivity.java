package com.beiyun.workers.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.RelativeLayout;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Sps;
import com.beiyun.library.util.Times;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.entity.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends BaseActivity {

    @BindView(R.id.rootView)
    RelativeLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        useExploderAnim();
        signIn();

    }

    private void signIn() {
        long saveTime = 365*24*60*60*1000;
        final User user = (User) Sps.get(User.class);
        Log.e("烟草职工移动办公 ==☆==", "signIn: splash Screen Activity user = "+user);
        Logs.e("signIn: splash Screen Activity user = "+user);
        if(user.getSignInTime()!= -1L && Times.currentTimeMillis() - user.getSignInTime()> saveTime){
            toastError("账户过期，请重新登陆");
            startActivity(LoginActivity.class);
            return;
        }

        if (user.getUserName() == null || user.getPassword() == null) {
            startActivity(LoginActivity.class);
        }else{
            startActivity(MainActivity.class);
        }

    }

    void startActivity(final Class<?> cls){
        rootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, cls));
                finish();
            }
        },1000);
    }
}