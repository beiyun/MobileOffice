package com.beiyun.workers.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


import com.beiyun.workers.entity.FieldItemInfo;
import com.beiyun.workers.interf.IFieldLayout;
import com.beiyun.workers.interf.OnDeleteClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 烟田变更
 * Created by zqht on 2016/6/29 13:40
 * Email:zmm534635184@sina.com
 */
public class FieldLayout extends LinearLayout implements IFieldLayout {

    private List<FieldForm> forms = new ArrayList<>();

    private List<FieldItemInfo> infos = new ArrayList<>();

    public FieldLayout(Context context) {
        this(context, null);
    }

    public FieldLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FieldLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        FieldForm gf = new FieldForm(getContext());
        gf.setLayoutParams(new LayoutParams(-1, -2));
        this.addView(gf);
        forms.add(gf);
        gf.setOnDeleteClickListener(new OnDeleteClickListener() {
            @Override
            public void OnDelete(View v) {
                reduce(v);
                FieldLayout.this.getRootView().requestFocus();
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
    }

    @Override
    public List<FieldItemInfo> getFieldInfo() {
        infos.clear();
        for (FieldForm form : forms) {
            FieldItemInfo info = new FieldItemInfo();
            info.tobaccoFieldNo = form.getTobaccoFieldNo();
            info.perFieldCount = form.getPerFieldCount();
            info.variety = form.getVariety();
            if (info.tobaccoFieldNo == null && info.perFieldCount == null
                    && info.variety == null) {
                continue;
            } else {
                infos.add(info);
            }
        }

        return infos;
    }

    @Override
    public void setFieldInfo(List<FieldItemInfo> infos) {
        this.removeAllViews();
        forms.clear();
        if (infos != null) {
            if (infos.isEmpty()) return;
            for (FieldItemInfo info : infos) {
                FieldForm f = new FieldForm(getContext());
                f.setLayoutParams(new LayoutParams(-1, -2));
                this.addView(f);
                this.getRootView().requestFocus();
                forms.add(f);
                f.setTobaccoFieldNo(info.tobaccoFieldNo);
                f.setPerFieldCount(info.perFieldCount);
                f.setVariety(info.variety);
            }
        }
    }

    public void setFieldInfo(String data) {
        this.removeAllViews();
        forms.clear();

        List<FieldItemInfo> infos = new ArrayList<>();
        String[] split = data.split(";");
        for (String string : split) {
            String[] item = string.split(",");
            if (item.length != 0) {
                FieldItemInfo entity = new FieldItemInfo(item[0], item[1], item[2]);
                infos.add(entity);
            }
        }
        if (infos.isEmpty()) return;
        for (FieldItemInfo info : infos) {
            FieldForm f = new FieldForm(getContext());
            f.setLayoutParams(new LayoutParams(-1, -2));
            this.addView(f);
            this.getRootView().requestFocus();
            forms.add(f);
            f.setTobaccoFieldNo(info.tobaccoFieldNo);
            f.setPerFieldCount(info.perFieldCount);
            f.setVariety(info.variety);
        }
    }



    @Override
    public String convertToString() {
        infos = getFieldInfo();
        String result = "";
        if (infos != null && infos.size() != 0) {
            for (FieldItemInfo info : infos
                    ) {
                result += info.tobaccoFieldNo + "," + info.perFieldCount + "," + info.variety + ";";
            }
        }
        return result;
    }
}
