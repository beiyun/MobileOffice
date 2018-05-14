package com.beiyun.workers.view;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.beiyun.workers.R;
import com.beiyun.workers.interf.IGrowTobaccoInfoForm;
import com.beiyun.workers.interf.OnDeleteClickListener;

/**
 * Created by mpb on 2016/6/24.
 */
public class GrowTobaccoInfoForm extends LinearLayout implements IGrowTobaccoInfoForm {

    private String id;
    private EditText mFieldName;
    private EditText mArea;
    private EditText mAreaCode;
    private EditText mPreceding;
    private Button mDel;
    private String landResource;
    private OnDeleteClickListener mListener;

    public GrowTobaccoInfoForm(Context context) {
        this(context,null);
    }

    public GrowTobaccoInfoForm(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GrowTobaccoInfoForm(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.grow_tobacco_info_form,null,true);
        view.setLayoutParams(new LayoutParams(-1,-2));
        this.addView(view);

        mDel = (Button) view.findViewById(R.id.delete);
        mFieldName = (EditText) view.findViewById(R.id.field_name);
        mPreceding = (EditText) view.findViewById(R.id.preceding);
        mArea = (EditText) view.findViewById(R.id.area);
        mAreaCode = (EditText) view.findViewById(R.id.areaCode);
        mDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.OnDelete(GrowTobaccoInfoForm.this);
                }

            }
        });
    }


    public String getLandResource() {
        return landResource;
    }

    public void setLandResource(String landResource) {
        this.landResource = landResource;
    }


    @Override
    public String getFieldName() {
        String s = mFieldName.getText().toString();
        return TextUtils.isEmpty(s)?null:s;
    }

    @Override
    public String getArea() {
        String s = mArea.getText().toString();
        return TextUtils.isEmpty(s)?null:s;
    }

    @Override
    public String getAreaCode() {
        String s = mAreaCode.getText().toString();
        return TextUtils.isEmpty(s)?null:s;
    }

    @Override
    public String getPreceding() {
        String s = mPreceding.getText().toString();
        return TextUtils.isEmpty(s)?null:s;
    }

    @Override
    public void setFieldName(String fieldName) {
        mFieldName.setText(fieldName);
    }

    @Override
    public void setArea(String area) {
        mArea.setText(area);
    }

    @Override
    public void setAreaCode(String areaCode) {
        mAreaCode.setText(areaCode);
    }

    @Override
    public void setPreceding(String preceding) {
        mPreceding.setText(preceding);
    }

    @Override
    public void setOnDeleteClickListener(OnDeleteClickListener deleteClickListener) {
        mListener = deleteClickListener;
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        mFieldName.setFocusable(false);
        mPreceding.setFocusable(focusable);
        mAreaCode.setFocusable(false);
        mArea.setFocusable(false);
    }


    public String getFieldId() {
        return id;
    }

    public void setFieldId(String id) {
        this.id = id;
    }
}
