package com.beiyun.workers.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beiyun.workers.R;


/**
 * Created by beiyun on 2017/4/19.
 */
public class CountEditText extends LinearLayout implements TextWatcher {

    private EditText mEditTextView;
    private TextView mCountTextView;
    private int mTextCount;

    public CountEditText(Context context) {
        this(context,null);
    }

    public CountEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CountEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_count_edit_text, null);
        view.setLayoutParams(new LinearLayoutCompat.LayoutParams(-1,-1));
        this.addView(view);

        mEditTextView = (EditText) view.findViewById(R.id.content);
        mCountTextView = (TextView) view.findViewById(R.id.count);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CountEditText,defStyleAttr,0);
        mTextCount = a.getInt(R.styleable.CountEditText_CountEditText_count, 100);
        int color = a.getColor(R.styleable.CountEditText_CountEditText_textColor, 0);
        float dimension = a.getDimensionPixelSize(R.styleable.CountEditText_CountEditText_textSize, 0);
        String hint = a.getString(R.styleable.CountEditText_CountEditText_hint);
        a.recycle();
        if(color != 0){
            mEditTextView.setTextColor(color);
        }
        if(dimension != 0){
            mEditTextView.setTextSize(dimension);
        }
        setHint(hint);
        mEditTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mTextCount)});
        mEditTextView.addTextChangedListener(this);
        mCountTextView.setText("0/"+mTextCount);


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        mCountTextView.setText(editable.length()+"/"+mTextCount);
    }

    public String getText(){
        return mEditTextView.getText().toString().trim();
    }

    public void setHint(String hint){
        if(hint == null) {
            return;
        }
        mEditTextView.setHint(hint);
    }

    public void setText(String text){
        if(text == null) {
            return;
        }
        mEditTextView.setText(text);
    }

    public void setEditable(boolean enable){

        if(enable){
            mEditTextView.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        }else{
            mEditTextView.setInputType(InputType.TYPE_NULL);
        }

        mEditTextView.setSingleLine(false);
        mEditTextView.setHorizontallyScrolling(false);
    }
}
