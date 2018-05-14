package com.beiyun.workers.interf;

import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;

/**
 * Created by mpb on 2016/6/23.
 */
public interface IFromView {

    /**
     * set a error info for this FromView
     *
     * @param error
     */
    void setError(String error);


    void setEditText(String text);

    String getText();

    /***
     * check the content of FromView is null or otherwise
     *
     * @return
     */
    boolean isContentNull();

    void setHint(String hint);

    void setTitleText(String titleText);

    void requestFocusNc();

    void setRightText(String rightText);

    void setRightTextColor(int rightTextColor);

    void setOnlyTextColor(int onlyTextColor);

    void setText(String text);

    void setMaxLength(int maxLength);

    void setFocusable(boolean focusable);

    void setAdapter(ArrayAdapter adapter);

    void setOnItemClickedListener(OnItemClickedListener listenter);

    void setOnClickListener(OnClickListener listener);

    void addTextChangeListener(TextChangeListener listener);

    void setOnFocusChangeListener(OnFocusChangeListener listener);

    interface OnItemClickedListener {
        void getPos(int pos);
    }

    interface OnClickListener {
        void Onclick(View v);
    }

    interface TextChangeListener {
        void beforeTextChanged(CharSequence charSequence, int start, int before, int count);

        void onTextChanged(CharSequence charSequence, int start, int count, int after);

        void afterTextChanged(Editable editable);
    }

    interface OnFocusChangeListener {
        void onFocusChange(View v, boolean hasFocus);
    }

    void setStringArray(String[] stringArray);

    void setEditable(boolean isEditable);
    void setSelection(int position);
    void setCode(String code);
    String getCode();

    void setGravity(int gravity);
}
