package com.beiyun.workers.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.beiyun.workers.R;
import com.beiyun.workers.interf.ItemtgbasicInfoItem;
import com.beiyun.workers.interf.OnDeleteClickListener;
import com.beiyun.workers.utils.AppUtils;

/**
 * Created by 中旗 on 2016/11/15.
 */
public class TGBasicInfoItemLayou extends LinearLayout implements ItemtgbasicInfoItem {
    private static final String TAG = "TGBasicInfoItemLayou";

    public FormView mlongitude, mlatitude, mcount, mlandSources, mname, mtype;
    private String fieldId;
    private OnDeleteClickListener mListener;
    private Button mDel;

    public TGBasicInfoItemLayou(Context context) {
        this(context, null);
    }

    public TGBasicInfoItemLayou(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TGBasicInfoItemLayou(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_tgbasic_list_item_info, null, true);
        view.setLayoutParams(new LayoutParams(-1, -2));
        this.addView(view);

        mDel=(Button) view.findViewById(R.id.delete);
        mlongitude=(FormView) view.findViewById(R.id.longitude);
        mlatitude=(FormView) view.findViewById(R.id.latitude);
        mcount=(FormView) view.findViewById(R.id.count);
        mlandSources=(FormView) view.findViewById(R.id.landSources);
        mtype=(FormView) view.findViewById(R.id.type);
        mname=(FormView)view.findViewById(R.id.name);
        AppUtils.doubleDecimalListener(mlongitude,3,6);
        AppUtils.doubleDecimalListener(mlatitude,3,6);
        AppUtils.doubleDecimalListener(mcount,6,2);
        mDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.OnDelete(TGBasicInfoItemLayou.this);
                }

            }
        });
    }

    @Override
    public String getLongitude() {
        return mlongitude.getText();
    }

    @Override
    public String getLatitude() {
        return mlatitude.getText();
    }

    @Override
    public String getCount() {
        return mcount.getText();
    }

    @Override
    public String getLandSources() {
        return mlandSources.getText();
    }


    @Override
    public String getName() {
        return mname.getText();
    }

    @Override
    public String getType() {
        return mtype.getText();
    }

    @Override
    public void setLongitude(String longitude) {
        mlongitude.setEditText(longitude);
    }

    @Override
    public void setLatitude(String latitude) {
        mlatitude.setEditText(latitude);
    }

    @Override
    public void setCount(String count) {
        mcount.setEditText(count);
    }

    @Override
    public void setLandSources(String landSources) {
        Log.d(TAG, "setLandSources: "+landSources);
        try {

            if(!TextUtils.isEmpty(landSources)){
                mlandSources.setEditText(getResources().getStringArray(R.array.yes_no)[Integer.valueOf(landSources)-1]);
            }else{
                mlandSources.setEditText("");
            }

        }catch (Exception e){
            Log.d(TAG, "setLandSources: ");
        }
    }


    @Override
    public void setName(String name) {
        mname.setEditText(name);
    }

    @Override
    public void setType(String type) {
        Log.d(TAG, "setType: "+type);
        try {
            if(!TextUtils.isEmpty(type)){
                mtype.setEditText(getResources().getStringArray(R.array.tianyan_type)[Integer.valueOf(type)-1]);
            }else{
                mtype.setEditText("");
            }

        }catch (Exception e){
            Log.d(TAG, "setLandSources: ");
        }
    }



    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public void setOnDeleteListener(OnDeleteClickListener listener) {
        mListener=listener;
    }
}
