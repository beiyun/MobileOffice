package com.beiyun.workers.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.beiyun.workers.entity.SubsidyItemInfo;
import com.beiyun.workers.interf.ISubsidyLayout;
import com.beiyun.workers.interf.OnDeleteClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 烟田变更
 * Created by zqht on 2016/6/29 13:40
 * Email:zmm534635184@sina.com
 */
public class SubsidyLayout extends LinearLayout implements ISubsidyLayout {

    private List<SubsidyForm> forms = new ArrayList<>();

    private List<SubsidyItemInfo> infos = new ArrayList<>();

    public SubsidyLayout(Context context) {
        this(context, null);
    }

    public SubsidyLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SubsidyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        SubsidyForm gf = new SubsidyForm(getContext());
        gf.setLayoutParams(new LayoutParams(-1, -2));
        this.addView(gf);
        forms.add(gf);
        gf.setOnDeleteClickListener(new OnDeleteClickListener() {
            @Override
            public void OnDelete(View v) {
                reduce(v);
                SubsidyLayout.this.getRootView().requestFocus();
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
    public List<SubsidyItemInfo> getSubsidyInfo() {
        infos.clear();
        for (SubsidyForm form : forms) {
            SubsidyItemInfo info = new SubsidyItemInfo();
            info.project = form.getProject();
            info.standard = form.getStandard();
            info.cash = form.getCash();
            if (info.project == null && info.standard == null
                    && info.cash == null) {
                continue;
            } else {
                infos.add(info);
            }
        }

        return infos;
    }

    @Override
    public void setSubsidyInfo(List<SubsidyItemInfo> infos) {
        this.removeAllViews();
        forms.clear();
        if (infos != null) {
            if (infos.isEmpty()) return;
            for (SubsidyItemInfo info : infos) {
                SubsidyForm f = new SubsidyForm(getContext());
                f.setLayoutParams(new LayoutParams(-1, -2));
                this.addView(f);
                this.getRootView().requestFocus();
                forms.add(f);
                f.setProject(info.project);
                f.setStandard(info.standard);
                f.setCash(info.cash);
            }
        }
    }

    public void setSubsidyInfo(String data) {
        this.removeAllViews();
        forms.clear();

        List<SubsidyItemInfo> infos = new ArrayList<>();
        String[] split = data.split(";");
        for (String string : split) {
            String[] item = string.split(",");
            if (item.length != 0) {
                SubsidyItemInfo entity = new SubsidyItemInfo(item[0], item[1], item[2]);
                infos.add(entity);
            }
        }

        if (infos.isEmpty()) return;
        for (SubsidyItemInfo info : infos) {
            SubsidyForm f = new SubsidyForm(getContext());
            f.setLayoutParams(new LayoutParams(-1, -2));
            this.addView(f);
            this.getRootView().requestFocus();
            forms.add(f);
            f.setProject(info.project);
            f.setStandard(info.standard);
            f.setCash(info.cash);
        }
    }


    @Override
    public String convertToString() {
        infos = getSubsidyInfo();
        String result = "";
        if (infos != null && infos.size() != 0) {
            for (SubsidyItemInfo info : infos
                    ) {
                result += info.project + "," + info.standard + "," + info.cash + ";";
            }
        }
        return result;
    }
}
