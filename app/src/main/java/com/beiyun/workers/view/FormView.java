package com.beiyun.workers.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beiyun.library.util.Logs;
import com.beiyun.workers.R;
import com.beiyun.workers.interf.IFromView;
import com.beiyun.workers.utils.RegexUtil;

/**
 * Created by nc on 2016/6/23.x
 */
public class FormView extends FrameLayout implements IFromView, CalendarDialog.OnDateSelectedListener, CalendarDialog.OnTimeSelectedListener {

    private static final String TAG = "FormView";
    public static final int DATA_PICKER = 7;
    private AutoCompleteTextView mEditTextView;
    private TextView mTextView;
    private TextView mRightTextView;
    private TextView mOnlyTextView;
    private boolean dropDownAble = true;//可否下拉
    private boolean dialogAble = true;//可否弹出对话框

    private int mGravity;
    private String mEditText;
    private String mTitleText;
    private String mText;
    private String mHint;
    private String[] mStringArray;
    private int mInputType;
    private String mRightText;
    private int mRightTextColor;
    private int mOnlyTextColor;
    private int mTitleTextColor;
    private int[] inputTypes = {InputType.TYPE_CLASS_TEXT, InputType.TYPE_CLASS_NUMBER,
            InputType.TYPE_CLASS_PHONE, InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD,
            DATA_PICKER, InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS};
    private boolean isCardNo;
    private boolean isIDNo;
    private static int DEFAULT_MAX_TEXT = 100;
    private int mMaxLength = DEFAULT_MAX_TEXT;
    private boolean isFocusable;
    private CalendarDialog mCalendarDialog;
    private boolean isCalendarShow = true;
    private String mDataCode;
    private boolean mNullAble;
    private String mError;

    public FormView(Context context) {
        this(context, null);
    }

    public FormView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.form_form_view, null, true);
        this.addView(view);
        mEditTextView = (AutoCompleteTextView) view.findViewById(R.id.edit);
        mTextView = (TextView) view.findViewById(R.id.text);
        mRightTextView = (TextView) view.findViewById(R.id.textRight);
        mOnlyTextView = (TextView) view.findViewById(R.id.only_text);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FormView, defStyleAttr, 0);
        mEditText = a.getString(R.styleable.FormView_editText);
        mHint = a.getString(R.styleable.FormView_hint);
        mGravity = a.getInt(R.styleable.FormView_gravity, 4);
        mTitleText = a.getString(R.styleable.FormView_titleText);
        mRightText = a.getString(R.styleable.FormView_rightText);
        mInputType = a.getInt(R.styleable.FormView_inputType, 0);
        mText = a.getString(R.styleable.FormView_only_text);
        mRightTextColor = a.getColor(R.styleable.FormView_rightTextColor, getResources().getColor(R.color.text_black));
        mOnlyTextColor = a.getColor(R.styleable.FormView_onlyTextColor, getResources().getColor(R.color.text_black));
        mTitleTextColor = a.getColor(R.styleable.FormView_titleTextColor,getResources().getColor(R.color.text_black));
        mMaxLength = a.getInt(R.styleable.FormView_maxLength, DEFAULT_MAX_TEXT);
        isFocusable = a.getBoolean(R.styleable.FormView_focusable, true);
        mNullAble = a.getBoolean(R.styleable.FormView_nullAble,true);
        int resourceId = a.getResourceId(R.styleable.FormView_dropDownStringArray, 0);
        if (resourceId != 0) {
            try {
                mStringArray = context.getResources().getStringArray(resourceId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        int editBackgroundResourceId = a.getResourceId(R.styleable.FormView_editBackground,0);
        if(editBackgroundResourceId != 0){
            setEditBackground(editBackgroundResourceId);
        }
        a.recycle();
        initView();
    }

    private void setEditBackground(int editBackgroundResourceId) {
        mEditTextView.setBackgroundResource(editBackgroundResourceId);
    }

    private void initView() {
        setRightText(mRightText);
        setRightTextColor(mRightTextColor);
        setOnlyTextColor(mOnlyTextColor);
        setTitleTextColor(mTitleTextColor);
        setEditText(mEditText);
        setHint(mHint);
        setTitleText(mTitleText);
        setText(mText);
        setMaxLength(mMaxLength);
        setFocusable(isFocusable);
        setStringArray(mStringArray);
        setGravity(mGravity);
        switchInput();
    }

    private void setTitleTextColor(int mTitleTextColor) {
        mTextView.setTextColor(mTitleTextColor);
    }

    //设置取值内容是否可以为空
    private void setNullAble(boolean mNullAble) {
        this.mNullAble = mNullAble;
    }

    public void switchInput() {
        switch (mInputType) {
            case 1://text
                mEditTextView.setInputType(inputTypes[0]);
                break;
            case 2://number
                mEditTextView.setInputType(inputTypes[1]);
                break;
            case 3://phone
                mEditTextView.setInputType(inputTypes[2]);
                mEditTextView.setMaxEms(11);
                break;
            case 4://ID_number
                mEditTextView.setKeyListener(DigitsKeyListener.getInstance(getResources().getString(R.string.identity_digit)));
                isIDNo = true;
                break;
            case 5://Card_No
                mEditTextView.setInputType(inputTypes[1]);
                isCardNo = true;
                break;
            case 6://password
                mEditTextView.setInputType(inputTypes[3]);
                mEditTextView.setKeyListener(DigitsKeyListener.getInstance(getResources().getString(R.string.psd_digit)));
                break;
            case 7://datePicker
                showDatePikerDialog();
                break;
            case 8:
                mEditTextView.setInputType(inputTypes[5]);
                break;
            case 9:
                showTimePickerDialog();
                break;
            default:
                mEditTextView.setInputType(inputTypes[0]);
                break;
        }
    }

    private void showTimePickerDialog() {
        mEditTextView.setFocusable(false);
        mEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!dialogAble) {
                    return;
                }
                if (mCalendarDialog != null && mCalendarDialog.isShowing()) {
                    mCalendarDialog.dismiss();
                }
                mCalendarDialog = new CalendarDialog(getContext(),CalendarDialog.TIME_PICKER);
                mCalendarDialog.setOnTimeSelectedListener(FormView.this);
                if (isCalendarShow) {
                    mCalendarDialog.show();
                }
            }
        });
    }

    private void showDatePikerDialog() {
        mEditTextView.setFocusable(false);
        mEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!dialogAble) {
                    return;
                }
                if (mCalendarDialog != null && mCalendarDialog.isShowing()) {
                    mCalendarDialog.dismiss();
                }
                mCalendarDialog = new CalendarDialog(getContext(),CalendarDialog.DATE_PICKER);
                mCalendarDialog.setOnDateSelectedListener(FormView.this);
                if (isCalendarShow) {
                    mCalendarDialog.show();
                }
            }
        });
    }

    @Override
    public void setError(String error) {
        this.mError = error;
        Toast.makeText(FormView.this.getContext(), error, Toast.LENGTH_SHORT).show();
        requestFocusNc();
    }

    @Override
    public void setEditText(String text) {
        if (!TextUtils.isEmpty(text)) {
            if (mStringArray != null && mStringArray.length != 0) {
                try {
                    int position = Integer.valueOf(text)-1;
                    mEditTextView.setText(mStringArray[position]);
                }catch (Exception e){
                    mEditTextView.setText(text);
                }

            } else {
                mEditTextView.setText(text);
            }
        } else {
            mEditTextView.setText(text);
        }
    }

    @Override
    public String getText() {
        mError = null;
        String result = mEditTextView.getText().toString().trim();

        if (!TextUtils.isEmpty(result)) {
            if (isIDNo && !RegexUtil.verifyIdentity(result)) {
                setError("身份证格式不正确");
                return "";
            } else if (isCardNo && !RegexUtil.verifyBankAccount(result)) {
                setError("银行卡号格式不正确");
                return "";
            }

            //if mStringArray is not null ,return the code for upload
            if (mStringArray != null && mStringArray.length != 0) {

                for (int i = 0; i < mStringArray.length; i++) {
                    if(mStringArray[i].equals(result)){
                        result = String.valueOf(i+1);
                    }
                }
            }

        }else {
            if(!mNullAble){
                setError(mTextView.getText().toString()+"不可为空");
            }
            return "";
        }

        return result;
    }

    public String getEditText(){
        return mEditTextView.getText().toString().trim();
    }

    public String getStringArrayText(){
        String result = mEditTextView.getText().toString().trim();
        if(mStringArray != null && mStringArray.length != 0){
            for (int i = 0; i < mStringArray.length; i++) {
                if(mStringArray[i].equals(result)){
                    return String.valueOf(i+1);
                }
            }
        }
        return result;
    }

    public void setStringArrayText(String text){
        if(text == null ||TextUtils.isEmpty(text)) {
            return;
        }
        if(mStringArray != null && mStringArray.length != 0){
            try {
                mEditTextView.setText(mStringArray[Integer.valueOf(text)-1]);
            }catch (Exception e){
                mEditTextView.setText(text);
            }
        }
    }

    public boolean hasError() {
        return mError != null;
    }

    @Override
    public boolean isContentNull() {
        String edit = mEditTextView.getText().toString();
        Log.e(TAG, "isContentNull: "+mTextView.getText().toString()+"是否为空=="+TextUtils.isEmpty(edit) );
        return TextUtils.isEmpty(edit);
    }

    public boolean isNull(){
        String edit = mEditTextView.getText().toString();
        if(TextUtils.isEmpty(edit)&&!mNullAble) {
            setError(mTextView.getText().toString() + "不可为空");
        }
        return TextUtils.isEmpty(edit);
    }

    @Override
    public void setHint(String hint) {
        if (hint != null) {
            mEditTextView.setHint(hint);
        }
    }

    @Override
    public void setTitleText(String titleText) {
        if (titleText != null) {
            mTextView.setText(titleText);
        } else {
            mTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void requestFocusNc() {
        mEditTextView.requestFocus();
    }

    @Override
    public void setRightText(String rightText) {
        if (rightText != null) {
            mRightTextView.setText(rightText);
        } else {
            mRightTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setRightTextColor(int rightTextColor) {
        mRightTextView.setTextColor(rightTextColor);
    }

    @Override
    public void setOnlyTextColor(int onlyTextColor) {
        mOnlyTextView.setTextColor(onlyTextColor);
    }

    @Override
    public void setText(String text) {
        if (text != null) {
            mEditTextView.setVisibility(View.GONE);
            mOnlyTextView.setText(text);
            mOnlyTextView.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.GONE);
            mRightTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setMaxLength(final int maxLength) {
        if (maxLength != 0) {
            mMaxLength = maxLength;
            mEditTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mMaxLength)});
        }
    }

    @Override
    public void setFocusable(boolean focusable) {
        mEditTextView.setFocusable(focusable);
    }

    @Override
    public void setAdapter(ArrayAdapter adapter) {
        mEditTextView.setAdapter(adapter);
        mEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!dropDownAble) {
                    return;
                }
                AutoCompleteTextView v = (AutoCompleteTextView) view;
                v.showDropDown();
            }
        });
    }

    @Override
    public void setOnItemClickedListener(final OnItemClickedListener listener) {
        mEditTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listener.getPos(i);
            }
        });
    }

    @Override
    public void setOnClickListener(final IFromView.OnClickListener listener) {
        mEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.Onclick(view);
            }
        });
    }

    @Override
    public void addTextChangeListener(final TextChangeListener listener) {
        mEditTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listener.beforeTextChanged(charSequence, i, i1, i2);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listener.onTextChanged(charSequence, i, i1, i2);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Logs.e("code----" + mDataCode);
                listener.afterTextChanged(editable);
            }
        });
    }

    @Override
    public void setOnFocusChangeListener(final IFromView.OnFocusChangeListener listener) {
        mEditTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                listener.onFocusChange(view, b);
            }
        });
    }

    @Override
    public void setStringArray(String[] stringArray) {
        if (stringArray == null) {
            return;
        }
        mEditTextView.setFocusable(false);
        mEditTextView.setThreshold(DEFAULT_MAX_TEXT);
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), R.layout.item_base_popup, stringArray);
        setAdapter(adapter);
    }

    @Override
    public void setEditable(boolean isEditable) {

        dropDownAble = isEditable;
        dialogAble = isEditable;

        if (isEditable) {
            switchInput();
            isCalendarShow = true;
        } else {
            setFocusable(false);
            mEditTextView.setInputType(InputType.TYPE_NULL);
            isCalendarShow = false;
        }
    }

    @Override
    public void setSelection(int position) {
        mEditTextView.setSelection(position);
    }

    private OnSetCodeListener mOnSetCodeListener;

    public interface OnSetCodeListener{
        void onSetCode(String code);
    }

    public void setOnSetCodeListener(OnSetCodeListener listener){
        this.mOnSetCodeListener = listener;
    }

    @Override
    public void setCode(String mDataCode) {
        this.mDataCode = mDataCode;
        if(mOnSetCodeListener != null){
            mOnSetCodeListener.onSetCode(mDataCode);
        }
    }

    @Override
    public String getCode() {
        return mDataCode;
    }

    @Override
    public void setGravity(int gravity) {

        if (mEditTextView == null) {
            return;
        }

        switch (gravity) {
            case 1:
                mEditTextView.setGravity(Gravity.CENTER);
                break;
            case 2:
                mEditTextView.setGravity(Gravity.BOTTOM);
                break;
            case 3:
                mEditTextView.setGravity(Gravity.TOP);
                break;
            case 4:
                mEditTextView.setGravity(Gravity.START);
                break;
            case 5:
                mEditTextView.setGravity(Gravity.END);
                break;
                default:
        }
    }



    @Override
    public void onDateSelected(CalendarDialog.Date date) {
        mEditTextView.setText(date.getYear() + "年" + date.getMonth()+ "月" + date.getDay() + "日");
        if (mCalendarDialog != null) {
            mCalendarDialog.dismiss();
            mCalendarDialog = null;
        }
    }

    @Override
    public void onTimeSelected(CalendarDialog.Time time) {
        mEditTextView.setText(time.getHour()+"时"+time.getMinute()+"分");
        if (mCalendarDialog != null) {
            mCalendarDialog.dismiss();
            mCalendarDialog = null;
        }
    }
}
