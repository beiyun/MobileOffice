package com.beiyun.workers.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.beiyun.workers.R;
import com.beiyun.workers.interf.ISortStrainForm;
import com.beiyun.workers.interf.OnDeleteClickListener;
import com.beiyun.workers.utils.AppUtils;

/**
 * 清塘点株
 * Created by zqht on 2016/6/29 15:57
 * Email:zmm534635184@sina.com
 */
public class SortStrainForm extends LinearLayout implements ISortStrainForm {
    private String fieldId;
    private EditText mFieldName;
    private EditText mFieldArea;
    public EditText mStrainCount;
    private Button mDel;
    private OnDeleteClickListener mListener;

    public SortStrainForm(Context context) {
        this(context, null);
    }

    public SortStrainForm(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SortStrainForm(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_sort_strain_item, null, true);
        view.setLayoutParams(new LayoutParams(-1, -2));
        this.addView(view);

        mDel = (Button) view.findViewById(R.id.delete);
        mFieldName = (EditText) view.findViewById(R.id.fieldName);
        mFieldArea = (EditText) view.findViewById(R.id.fieldArea);
        mStrainCount = (EditText) view.findViewById(R.id.strainCount);
        AppUtils.decimalListener(mFieldArea,0,0,0);
        mDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.OnDelete(SortStrainForm.this);
                }

            }
        });
    }

    public String getfieldId() {
        return fieldId;
    }

    public void setfieldId(String id) {
        this.fieldId = id;
    }

    @Override
    public String getFieldName() {
        return mFieldName.getText().toString().trim();
    }

    @Override
    public String getFieldArea() {
        return mFieldArea.getText().toString().trim();
    }

    @Override
    public String getStrainCount() {
        return mStrainCount.getText().toString().trim();
    }

    @Override
    public void setFieldName(String fieldName) {
        mFieldName.setText(fieldName);
    }

    @Override
    public void setFieldArea(String fieldArea) {
        mFieldArea.setText(fieldArea);
    }

    @Override
    public void setStrainCount(String strainCount) {
        mStrainCount.setText(strainCount);
    }

    @Override
    public void setOnDeleteClickListener(OnDeleteClickListener deleteClickListener) {
        mListener = deleteClickListener;
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        mFieldName.setFocusable(false);
        mFieldArea.setFocusable(focusable);
        mStrainCount.setFocusable(focusable);
    }


}
