package com.beiyun.workers.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.beiyun.workers.entity.SortStrainItemInfo;
import com.beiyun.workers.interf.ISortStrainLayout;
import com.beiyun.workers.interf.OnDeleteClickListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqht on 2016/6/29 16:18
 * Email:zmm534635184@sina.com
 */
public class SortStrainLayout extends LinearLayout implements ISortStrainLayout {
    private List<SortStrainForm> forms = new ArrayList<>();

    private List<SortStrainItemInfo> infos = new ArrayList<>();

    public SortStrainLayout(Context context) {
        this(context, null);
    }

    public SortStrainLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SortStrainLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        SortStrainForm gf = new SortStrainForm(getContext());
        gf.setLayoutParams(new LayoutParams(-1, -2));
        this.addView(gf);
        forms.add(gf);setCountListener(gf.mStrainCount);
        gf.setOnDeleteClickListener(new OnDeleteClickListener() {
            @Override
            public void OnDelete(View v) {
                reduce(v);
                SortStrainLayout.this.getRootView().requestFocus();
            }
        });
    }

    @Override
    public void add() {
        this.getRootView().requestFocus();
        init();
    }

    @Override
    public void reduce(View view) {
        this.removeView(view);
        forms.remove(view);
        setFieldChangeInfo(getFieldChangeInfo());
    }

    @Override
    public List<SortStrainItemInfo> getFieldChangeInfo() {
        infos.clear();
        for (SortStrainForm form : forms) {
            SortStrainItemInfo info = new SortStrainItemInfo();
            info.fieldName = form.getFieldName();
            info.fieldArea = form.getFieldArea();
            info.strainCount = form.getStrainCount();
            info.id = form.getfieldId();
            if (info.fieldArea == null && info.fieldName == null
                    && info.strainCount == null) {
                continue;
            } else {
                infos.add(info);
            }
        }

        return infos;
    }

    @Override
    public void setFieldChangeInfo(List<SortStrainItemInfo> info) {
        this.removeAllViews();
        forms.clear();
        if (info != null) {
            getChangeLand(info);
            if (info.isEmpty()) return;
            for (SortStrainItemInfo s : info) {
                SortStrainForm ssf = new SortStrainForm(getContext());
                ssf.setLayoutParams(new LayoutParams(-1, -2));
                this.addView(ssf);
                this.getRootView().requestFocus();
                forms.add(ssf);
                ssf.setFieldName(s.fieldName);
                ssf.setFieldArea(s.fieldArea);
                ssf.setStrainCount(s.strainCount);
                ssf.setfieldId(s.id);
                ssf.setOnDeleteClickListener(new OnDeleteClickListener() {
                    @Override
                    public void OnDelete(View v) {
                        reduce(v);
                    }
                });
                setCountListener(ssf.mStrainCount);
            }
        }
    }


    public void setFieldChangeInfo(String data) {
        this.removeAllViews();
        forms.clear();

        List<SortStrainItemInfo> info = new ArrayList<>();
        String[] split = data.split(";");
        for (String string : split) {
            String[] item = string.split(",");
            if (item.length != 0) {
                SortStrainItemInfo entity = new SortStrainItemInfo(item[0], item[1], item[2],item[3]);
                info.add(entity);
            }
        }

        getChangeLand(info);

        if (info.isEmpty()) return;
        for (SortStrainItemInfo s : info) {
            SortStrainForm ssf = new SortStrainForm(getContext());
            ssf.setLayoutParams(new LayoutParams(-1, -2));
            this.addView(ssf);
            this.getRootView().requestFocus();
            forms.add(ssf);
            ssf.setFieldName(s.fieldName);
            ssf.setFieldArea(s.fieldArea);
            ssf.setStrainCount(s.strainCount);
            ssf.setfieldId(s.id);
            setCountListener(ssf.mStrainCount);
            ssf.setOnDeleteClickListener(new OnDeleteClickListener() {
                @Override
                public void OnDelete(View v) {
                    reduce(v);
                }
            });
        }
    }


    public String convertToString() {
        infos = getFieldChangeInfo();
        String result = "";
        if (infos != null && infos.size() != 0) {
            for (SortStrainItemInfo info : infos) {
                result += info.fieldName + "," + info.fieldArea + "," + info.strainCount + "," + info.id +";";
            }
        }
        return result;
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        for (SortStrainForm form : forms) {
            form.setFocusable(focusable);
        }
    }

    private static final String TAG = "SortStrainLayout";
    private LandChangedListener mListener;

    public interface LandChangedListener{
        void onChanged(String count, String area);
    }

    public void setOnLandChangedListener(LandChangedListener listener){
        this.mListener = listener;
    }

    private void getChangeLand(List<SortStrainItemInfo> infos) {
        int count = 0;//株树
        double area = 0;//租赁土地面积


        for (SortStrainItemInfo info:infos) {
            Log.d(TAG, "getTypeArea: "+info);
           try {
               if(info.strainCount != null && !TextUtils.isEmpty(info.strainCount)){
                   count += Integer.parseInt(info.strainCount);
               }
               if(info.fieldArea != null && !TextUtils.isEmpty(info.fieldArea)){
                   area += Double.valueOf(info.fieldArea);
                   Log.e(TAG, "面积合计: "+area+"地块面积："+info.fieldArea);
               }
           }catch (Exception e){
               Log.d(TAG, "getChangeLand: "+e.getMessage());
           }
        }

        if(mListener != null){
            BigDecimal bd = new BigDecimal(area);
            area = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            mListener.onChanged(String.valueOf(count),String.valueOf(area));
        }
    }

    private OnCountChangedListener mCountChangedListener;

    public interface OnCountChangedListener{
        void onCountChanged(String totalCount);
    }

    public void setOnCountChangedListener(OnCountChangedListener listener){
        this.mCountChangedListener = listener;
    }

    private void setCountListener(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Log.d(TAG, "onTextChanged: ");
                int totalCount = 0;

                List<SortStrainItemInfo> infos = getFieldChangeInfo();
                if(infos!= null && !infos.isEmpty()){
                    for (SortStrainItemInfo info:infos) {

                        try {
                            totalCount += Integer.parseInt(info.strainCount);
                        }catch (Exception e){
                            Log.d(TAG, "onTextChanged: "+e.getMessage());
                        }
                    }
                }

                if(mCountChangedListener != null){
                    Log.d(TAG, "onTextChanged: "+totalCount);
                    mCountChangedListener.onCountChanged(String.valueOf(totalCount));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(TAG, "afterTextChanged: ");

            }
        });
    }



}
