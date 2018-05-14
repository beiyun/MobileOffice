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
import com.beiyun.workers.interf.ISubsidyForm;
import com.beiyun.workers.interf.OnDeleteClickListener;

/**
 * Created by zqht on 2016/6/29 13:48
 * Email:zmm534635184@sina.com
 */
public class SubsidyForm extends LinearLayout implements ISubsidyForm {

    private EditText project;
    private EditText standard;
    private EditText cash;
    private Button mDel;
    private OnDeleteClickListener mListener;

    public SubsidyForm(Context context) {
        this(context, null);
    }

    public SubsidyForm(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubsidyForm(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_subsidy_item, null, true);
        view.setLayoutParams(new LayoutParams(-1, -2));
        this.addView(view);

        mDel = (Button) view.findViewById(R.id.delete);
        project = (EditText) view.findViewById(R.id.project);
        standard = (EditText) view.findViewById(R.id.standard);
        cash = (EditText) view.findViewById(R.id.cash);
        mDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.OnDelete(SubsidyForm.this);
                }

            }
        });
    }

    @Override
    public String getProject() {
        return project.getText().toString().trim();
    }

    @Override
    public String getStandard() {
        return standard.getText().toString().trim();
    }

    @Override
    public String getCash() {
        return cash.getText().toString().trim();
    }
    @Override
    public void setCash(String fieldName) {
        cash.setText(fieldName);
    }

    @Override
    public void setProject(String fieldName) {
        project.setText(fieldName);
    }

    @Override
    public void setStandard(String fieldNumber) {
        standard.setText(fieldNumber);
    }

    @Override
    public void setOnDeleteClickListener(OnDeleteClickListener deleteClickListener) {
        mListener = deleteClickListener;
    }
}