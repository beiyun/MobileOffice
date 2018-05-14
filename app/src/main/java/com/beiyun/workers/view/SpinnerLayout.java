package com.beiyun.workers.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.beiyun.library.util.Sizes;

import com.beiyun.workers.entity.Address;
import com.beiyun.workers.utils.AddressSelector;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.HashMap;
import java.util.List;

public class SpinnerLayout extends LinearLayout {


    public SpinnerLayout(Context context) {
        super(context);
        init(context);
    }


    public SpinnerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SpinnerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.setOrientation(LinearLayout.VERTICAL);
    }


    public void start(){
        AddressSelector.attachSpinners(this);
    }


    public MaterialSpinner addItem(int flag,String hint,List<String> items){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(this);
        }
        MaterialSpinner spinner = new MaterialSpinner(getContext());
        LayoutParams params = new LayoutParams(-1, -2);
        params.topMargin = Sizes.dp2px(10);
        spinner.setLayoutParams(params);
        spinner.setHint(hint);
        spinner.setId(flag);
        spinner.setItems(items);
        this.addView(spinner);
        return spinner;
    }


    public void removeItem(int flag){
        if(flag > 6){
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(this);
        }

        int childCount = getChildCount();

        try {
            for (int i = flag; i < childCount; i++) {
                this.removeView(getChildAt(i));
                getAddressMap().remove("key"+i);
                com.beiyun.library.util.Logs.e("-----------flag = "+flag+"-------remove"+getChildCount());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public HashMap<String,Address> getAddressMap(){
        return AddressSelector.getAddressMap();
    }

    public void clearAddressMap(){
        AddressSelector.clearAddressMap();
    }




}
