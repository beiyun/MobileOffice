package com.beiyun.workers.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.beiyun.workers.R;
import com.beiyun.workers.interf.IFieldForm;
import com.beiyun.workers.interf.OnDeleteClickListener;

/**
 * Created by zqht on 2016/6/29 13:48
 * Email:zmm534635184@sina.com
 */
public class FieldForm extends LinearLayout implements IFieldForm {

    private EditText tobaccoFieldNo;
    private EditText perFieldCount;
    private EditText variety;
    private Button mDel;
    private OnDeleteClickListener mListener;

    public FieldForm(Context context) {
        this(context, null);
    }

    public FieldForm(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FieldForm(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_field_item, null, true);
        view.setLayoutParams(new LayoutParams(-1, -2));
        this.addView(view);

        mDel = (Button) view.findViewById(R.id.delete);
        tobaccoFieldNo = (EditText) view.findViewById(R.id.tobaccoFieldNo);
        perFieldCount = (EditText) view.findViewById(R.id.perFieldCount);
        variety = (EditText) view.findViewById(R.id.variety);
        mDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.OnDelete(FieldForm.this);
                }

            }
        });
    }

    @Override
    public String getTobaccoFieldNo() {
        return tobaccoFieldNo.getText().toString().trim();
    }

    @Override
    public String getPerFieldCount() {
        return perFieldCount.getText().toString().trim();
    }

    @Override
    public String getVariety() {
        return variety.getText().toString().trim();
    }
    @Override
    public void setVariety(String fieldName) {
        variety.setText(fieldName);
    }

    @Override
    public void setPerFieldCount(String fieldName) {
        perFieldCount.setText(fieldName);
    }

    @Override
    public void setTobaccoFieldNo(String fieldNumber) {
        tobaccoFieldNo.setText(fieldNumber);
    }

    @Override
    public void setOnDeleteClickListener(OnDeleteClickListener deleteClickListener) {
        mListener = deleteClickListener;
    }
}