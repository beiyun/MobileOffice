package com.beiyun.workers.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beiyun.library.anot.Receiver;
import com.beiyun.library.util.Apps;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Sizes;
import com.beiyun.library.util.Sps;
import com.beiyun.library.util.Times;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.User;
import com.beiyun.workers.okhttp.OkHttpUtils;
import com.beiyun.workers.okhttp.callback.BaseInfo;
import com.beiyun.workers.okhttp.callback.ResponseTCallBack;
import com.beiyun.workers.utils.MD5Util;
import com.beiyun.workers.utils.TestSimpleDataUtil;
import com.dd.processbutton.FlatButton;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A login screen that offers login via userName/password.
 */
@Receiver
public class LoginActivity extends BaseActivity {


    // UI references.
    private AutoCompleteTextView mUserNameView;
    private AutoCompleteTextView mPasswordView;

    private boolean isShowPassword = false;
    private FlatButton mSignInButton;
    private LinearLayout covertView;
    private int cx;
    private int cy;
    private float finalRadius;
    private LinearLayout contentView;
    private SwitchCompat switchCompat;
    private LinearLayout switchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        // Set up the login form.
        mUserNameView = findViewById(R.id.userName);
        covertView = findViewById(R.id.covert_layout);
        contentView = findViewById(R.id.content_layout);
        initPassWordView();
        initSwitchView();
        initSignBtn();


        mUserNameView.setText(TestSimpleDataUtil.getUserNumber());
    }



    private void initSignBtn() {
        mSignInButton = findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();

            }
        });
    }

    private void initSwitchView() {
        switchCompat = findViewById(R.id.signSwitch);
        switchLayout = findViewById(R.id.switchLayout);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    switchCompat.setText(R.string.publicSign);
                }else {
                    switchCompat.setText(R.string.privateSign);
                }

                changeBackgroundState(isChecked);
            }
        });


        initXY();
    }

    private void initXY() {

        switchCompat.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Logs.e("getViewTreeObserver getRight = "+ switchCompat.getRight());
                Logs.e("getViewTreeObserver getLeft = "+ switchCompat.getLeft());
                Logs.e("getViewTreeObserver getBottom = "+ switchCompat.getBottom());
                Logs.e("getViewTreeObserver getTop = "+ switchCompat.getTop());
                cx = (switchCompat.getRight() + switchCompat.getLeft())/2;
                cy = (switchLayout.getBottom() + switchLayout.getTop())/2;
                int dx = Math.max(cx,covertView.getWidth()- cx);
                int dy = Math.max(cy,covertView.getHeight()- cy);
                finalRadius = (float) Math.hypot(dx,dy);
                switchCompat.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void changeBackgroundState(final boolean isChecked) {
        initXY();
        switchCompat.post(new Runnable() {
            @Override
            public void run() {
                // 专网
                if(!isChecked){
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        covertView.setBackgroundResource(R.color.colorPrimary);
                        Animator animator = ViewAnimationUtils.createCircularReveal(covertView, cx, cy, Sizes.dp2px(40), finalRadius);
                        animator.setDuration(500).setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.start();
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                contentView.setBackgroundResource(R.color.colorPrimary);
                            }
                        });

                    }else{
                        contentView.setBackgroundResource(R.color.colorPrimary);
                    }

                }else{//公网
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        covertView.setBackgroundResource(R.color.blue_normal);
                        Animator animator = ViewAnimationUtils.createCircularReveal(covertView, cx, cy, Sizes.dp2px(40), finalRadius);
                        animator.setDuration(500).setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.start();
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                contentView.setBackgroundResource(R.color.blue_normal);
                            }
                        });
                    }else{
                        contentView.setBackgroundResource(R.color.blue_normal);
                    }

                }

            }
        });


    }

    @SuppressLint("ClickableViewAccessibility")
    private void initPassWordView() {
        mPasswordView = findViewById(R.id.password);

        mPasswordView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Drawable drawableLeft = getMyDrawable(R.drawable.ic_lock_white_24dp);
                Drawable drawableRightVisiable = getMyDrawable(R.drawable.ic_visibility_white_24dp);
                Drawable drawableRightUnVisiable = getMyDrawable(R.drawable.ic_visibility_off_white_24dp);
                boolean isVisiablePassword = isVisiblePasswordInputType(mPasswordView.getInputType());
                if(hasFocus){
                    if(isVisiablePassword){
                        mPasswordView.setCompoundDrawables(drawableLeft,null,drawableRightVisiable,null);
                    }else{
                        mPasswordView.setCompoundDrawables(drawableLeft,null,drawableRightUnVisiable,null);
                    }
                }else{
                    mPasswordView.setCompoundDrawables(drawableLeft,null,null,null);
                }

            }
        });

        mPasswordView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Drawable drawable = mPasswordView.getCompoundDrawables()[2];
                if(drawable == null) {
                    return false;
                }
                if(event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                if(event.getX() > mPasswordView.getWidth() - mPasswordView.getPaddingRight() - drawable.getIntrinsicWidth()){

                    Drawable drawableLeft = getMyDrawable(R.drawable.ic_lock_white_24dp);
                    if(!isShowPassword){

                        Drawable drawableRightVisiable = getMyDrawable(R.drawable.ic_visibility_white_24dp);
                        mPasswordView.setCompoundDrawables(drawableLeft,null, drawableRightVisiable,null);
                        mPasswordView.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        mPasswordView.requestFocus();
                        mPasswordView.setSelection(mPasswordView.getText().length());
                        isShowPassword = true;
                    }else{
                        Drawable drawableRightVisiableOff = getMyDrawable(R.drawable.ic_visibility_off_white_24dp);
                        mPasswordView.setCompoundDrawables(drawableLeft,null, drawableRightVisiableOff,null);
                        mPasswordView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        mPasswordView.requestFocus();
                        mPasswordView.setSelection(mPasswordView.getText().length());
                        isShowPassword = false;
                    }

                }


                return false;
            }
        });
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_ACTION_DONE) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

    }



    private Drawable getMyDrawable(@DrawableRes int res) {
        Drawable drawable = Apps.getResources().getDrawable(res);
        if (drawable != null) {
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        }

        return drawable;
    }

    private boolean isVisiblePasswordInputType(int inputType) {
        final int variation =
                inputType & (EditorInfo.TYPE_MASK_CLASS | EditorInfo.TYPE_MASK_VARIATION);
        return variation
                == EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
    }

    public void goToMainActivity(){

        startActivity(new Intent(LoginActivity.this,MainActivity.class),mSignInButton);
        mSignInButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                Apps.finish(LoginActivity.this);
            }
        },2000);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid userName, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mUserNameView.setError(null);
        mPasswordView.setError(null);


        // Store values at the time of the login attempt.
        String userName = mUserNameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 检验密码正确性
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("请输入密码");
            focusView = mPasswordView;
            cancel = true;
        }

        // 判断用户名是否正确.
        if (TextUtils.isEmpty(userName)) {
            mUserNameView.setError("请输入用户名");
            focusView = mUserNameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //login
//            mUserNameView.setEnabled(false);
//            mPasswordView.setEnabled(false);
//            mSignInButton.setClickable(false);

            signIn();
//            signInLocal();
        }
    }

    private void signInLocal() {
        goToMainActivity();
    }

    private void signIn() {
        final String userName = mUserNameView.getText().toString();
        final String password = MD5Util.getMD5(mPasswordView.getText().toString());

        HashMap<String,String> params = new HashMap<>();
        params.put("bo.userName",userName);
        params.put("bo.password",password);
        showProgressDialog();
        OkHttpUtils.postUpload(AppUrl.get().LOGIN_SIGN_IN, params, new ResponseTCallBack<BaseInfo<ArrayList<User>>>() {
            @Override
            public void onFailure(IOException e) {
                dismissProgressDialog();
                toastError("数据请求异常");
                Logs.e(e.getMessage());

            }

            @Override
            protected void onSuccess(BaseInfo<ArrayList<User>> data) {
                dismissProgressDialog();
                if(data.getResultCode() != 100){
                    toastError(data.getReason());
                    return;
                }
                ArrayList<User> users = data.getData().getList();
                if(users != null && users.size() != 0){
                    User userResponse = users.get(0);
                    userResponse.setUserName(userName);
                    userResponse.setPassword(password);
                    userResponse.setSignInTime(Times.currentTimeMillis());
                    Sps.save(userResponse);
                    goToMainActivity();
                }
            }
        });



    }


    @TargetApi(Build.VERSION_CODES.M)
    public void signUp(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class),view);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void forgetPassword(View view) {
        startActivity(new Intent(LoginActivity.this,UpdatePasswordStep1Activity.class),view);

    }
}

