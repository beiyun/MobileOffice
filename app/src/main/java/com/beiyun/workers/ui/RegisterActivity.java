package com.beiyun.workers.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.Address;
import com.beiyun.workers.entity.Instructor;
import com.beiyun.workers.okhttp.OkHttpUtils;
import com.beiyun.workers.okhttp.callback.RequestCallBack;
import com.beiyun.workers.okhttp.helper.ResultData;
import com.beiyun.workers.utils.AddressSelector;
import com.beiyun.workers.utils.MD5Util;
import com.dd.processbutton.iml.SubmitProcessButton;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.sql.Struct;
import java.util.Arrays;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.nickname)
    MaterialEditText nickname;
    @BindView(R.id.province)
    MaterialSpinner province;
    @BindView(R.id.city)
    MaterialSpinner city;
    @BindView(R.id.county)
    MaterialSpinner county;
    @BindView(R.id.uid)
    MaterialSpinner uid;
    @BindView(R.id.departmentName)
    MaterialSpinner departmentName;
    @BindView(R.id.vid)
    TextView vid;
    @BindView(R.id.typeName)
    MaterialSpinner typeName;
    @BindView(R.id.tel)
    MaterialEditText tel;
    @BindView(R.id.password)
    MaterialEditText password;
    @BindView(R.id.passwordAgain)
    MaterialEditText passwordAgain;
    @BindView(R.id.sign_in_button)
    SubmitProcessButton signInButton;
    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;
    @BindView(R.id.login_form)
    ScrollView loginForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("注册");
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        AddressSelector.attachSpinners(province, city, county, uid);
        String[] instructorTypes = {"省领导","省职工","市领导","市职工","县领导","县职工","烟站站长","烟站职工"};
        String[] departments = {"企管科长","企管科员","办公室主任","办公室科员","生产科长","生产科员"};
        typeName.setItems(instructorTypes);
        departmentName.setItems(departments);
    }



    public void register(View view) {
        if(hasNull()){
         return;
        }

        HashMap<String, Address> addressMap = AddressSelector.getAddressMap();
        Instructor instructor = new Instructor();
        instructor.setProvince(addressMap.get("key0")==null? "" : addressMap.get("key0").getCode());
        instructor.setCity(addressMap.get("key1") == null? "" : addressMap.get("key1").getCode());
        instructor.setCounty(addressMap.get("key2") == null? "" : addressMap.get("key2").getCode());
        instructor.setUid(addressMap.get("key3") == null? "" : addressMap.get("key3").getCode());
        instructor.setTypeName(getSpinnerValue(typeName));
        instructor.setDepartmentName(getSpinnerValue(departmentName));
        instructor.setNickname(getTextValue(nickname));
        instructor.setTel(getTextValue(tel));
        instructor.setVid(vidCodes);
        instructor.setPassword(MD5Util.getMD5(getTextValue(password)));

        HashMap<String, String> params = OkHttpUtils.generate(instructor);
        showProgressDialog();
        OkHttpUtils.postUpload(AppUrl.get().REGISTER, params, new RequestCallBack() {
            @Override
            public void success(ResultData data) {
                dismissProgressDialog();
                if(data.getResultCode() == 100){
                    toastSuccess("注册成功");
                    com.beiyun.library.util.Events.post(data.getReason());
                    ActivityCompat.finishAfterTransition(RegisterActivity.this);
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


    private String getTextValue(MaterialEditText editText){
        return editText.getText().toString();
    }

    private String getSpinnerValue(MaterialSpinner spinner){
        return spinner.getText().toString();
    }

    private boolean hasNull() {
        if(isTextNull(nickname)){
            toastError("姓名不能为空");
            return true;
        }else if(isTextNull(tel)){
            toastError("手机号不能为空");
            return true;
        }else if(TextUtils.isEmpty(vid.getText())){
            toastError("所辖村委会不能为空");
            return true;
        }else if(isTextNull(password)){
            toastError("密码不能为空");
            return true;
        }else if(isTextNull(passwordAgain)){
            toastError("密码不能为空");
            return true;
        }else if(isSpinnerNull(province)){
            toastError("省不能为空");
            return true;
        }else if(isSpinnerNull(city)){
            toastError("市不能为空");
            return true;
        }else if(isSpinnerNull(county)){
            toastError("县不能为空");
            return true;
        }else if(isSpinnerNull(uid)){
            toastError("烟站不能为空");
            return true;
        }else if(isSpinnerNull(departmentName)){
            toastError("科室不能为空");
            return true;
        }else if(isSpinnerNull(typeName)){
            toastError("职工类别不能为空");
            return true;
        }else if(!getTextValue(passwordAgain).equals(getTextValue(password))){
            toastError("两次密码输入不一致");
            return true;
        }


        return false;
    }

    private boolean isTextNull(EditText editText){
        return TextUtils.isEmpty(editText.getText());
    }

    private boolean isSpinnerNull(MaterialSpinner spinner){
        return TextUtils.isEmpty(spinner.getText());
    }


    private String vidCodes;

    private String vidTexts;

    @OnClick(R.id.vid)
    public void onViewClicked() {

        if(AddressSelector.getVillages() == null){
            toastError("请补全地址信息");
            return;
        }

        String[] items = new String[AddressSelector.getVillages().size()];

        for (int i = 0; i < AddressSelector.getVillages().size(); i++) {
            items[i] = AddressSelector.getVillages().get(i).getName();
        }



        new MaterialDialog.Builder(this)
                .title("选择所辖村组")
                .items(items)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        /**
                         * If you use alwaysCallMultiChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected check box to actually be selected
                         * (or the newly unselected check box to be unchecked).
                         * See the limited multi choice dialog example in the sample project for details.
                         **/
                        return true;
                    }
                })
                .positiveText("确定")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        Integer[] indices = dialog.getSelectedIndices();
                        if(indices == null ||indices.length ==0){
                            return;
                        }

                        StringBuilder vidTextBuilder = new StringBuilder();
                        StringBuilder codesBuilder = new StringBuilder();

                        for (Integer indice : indices) {
                            vidTextBuilder.append(AddressSelector.getVillages().get(indice).getName()).append(",");
                            codesBuilder.append(AddressSelector.getVillages().get(indice).getCode()).append(",");
                        }

                        vidTexts = vidTextBuilder.toString().substring(0,vidTextBuilder.toString().length()-1);
                        vidCodes = codesBuilder.toString().substring(0,codesBuilder.toString().length()-1);

                        vid.setText(vidTexts);
                    }
                })
                .show();



    }
}
