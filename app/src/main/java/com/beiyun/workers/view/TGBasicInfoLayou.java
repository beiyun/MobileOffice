package com.beiyun.workers.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.beiyun.workers.R;
import com.beiyun.workers.entity.TGBasicInfoItemEntity;
import com.beiyun.workers.interf.ItemtgbasicInfoLayou;
import com.beiyun.workers.interf.OnDeleteClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 中旗 on 2016/11/15.
 */
public class TGBasicInfoLayou extends FrameLayout implements ItemtgbasicInfoLayou {

    private List<TGBasicInfoItemLayou> forms = new ArrayList<>();
    private List<TGBasicInfoItemEntity> infos = new ArrayList<>();
    private LinearLayout mContentView;
    private Button add;

    public TGBasicInfoLayou(Context context) {
        super(context);
        init(context);
    }

    public TGBasicInfoLayou(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TGBasicInfoLayou(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_tgbasic_info_list, null);
        this.addView(view);
        mContentView = (LinearLayout) view.findViewById(R.id.content);
        add = (Button) view.findViewById(R.id.add);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        forms = new ArrayList<>();
        TGBasicInfoItemLayou gf = new TGBasicInfoItemLayou(getContext());
        gf.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        mContentView.addView(gf);
        forms.add(gf);
        gf.setOnDeleteListener(new OnDeleteClickListener() {
            @Override
            public void OnDelete(View v) {
                del(v);
                TGBasicInfoLayou.this.getRootView().requestFocus();
            }
        });
    }

    @Override
    public void add() {
        this.getRootView().requestFocus();
        TGBasicInfoItemLayou gf = new TGBasicInfoItemLayou(getContext());
        gf.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        mContentView.addView(gf);
        forms.add(gf);
        gf.setOnDeleteListener(new OnDeleteClickListener() {
            @Override
            public void OnDelete(View v) {
                del(v);
                TGBasicInfoLayou.this.getRootView().requestFocus();
            }
        });
    }

    @Override
    public void del(View view) {
        mContentView.removeView(view);
        forms.remove(view);
    }

    @Override
    public List<TGBasicInfoItemEntity> getInfo() {
        infos.clear();
        for (TGBasicInfoItemLayou form : forms) {
            TGBasicInfoItemEntity info = new TGBasicInfoItemEntity();
            info.longitude = form.getLongitude();//经度
            info.latitude = form.getLatitude();//纬度
            info.count = form.getCount();//亩数
            info.landSources=form.getLandSources();//土地来源
            info.type = form.getType();//烟田类型
            info.name = form.getName();//地块名称
            info.id = form.getFieldId();
            if (info.longitude == null && info.latitude == null
                    && info.count == null && info.landSources==null
                    && info.name == null && info.type == null) {
                continue;
            } else {
                infos.add(info);
            }
        }
        return infos;
    }

    @Override
    public void setInfo(List<TGBasicInfoItemEntity> info) {
        mContentView.removeAllViews();
        forms.clear();
        if (info != null) {
            if (info.isEmpty()) return;
            for (TGBasicInfoItemEntity i : info) {
                TGBasicInfoItemLayou gf = new TGBasicInfoItemLayou(getContext());
                gf.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                mContentView.addView(gf);
                this.getRootView().requestFocus();
                forms.add(gf);
                gf.setLongitude(i.longitude);
                gf.setLatitude(i.latitude);
                gf.setCount(i.count);
                gf.setLandSources(i.landSources);
                gf.setType(i.type);
                gf.setName(i.name);
                gf.setFieldId(i.id);
                gf.setOnDeleteListener(new OnDeleteClickListener() {
                    @Override
                    public void OnDelete(View v) {
                        del(v);
                        TGBasicInfoLayou.this.getRootView().requestFocus();
                    }
                });

            }
        }
    }


    public void setInfo(String data) {

        if(data == null) return;

        mContentView.removeAllViews();
        forms.clear();
        List<TGBasicInfoItemEntity> info = new ArrayList<>();
        String[] split = data.split(";");
        for (String string : split) {
            String[] item = string.split(",");
            if(item.length != 0){
                TGBasicInfoItemEntity entity = new TGBasicInfoItemEntity(item[0], item[1], item[2], item[3], item[4],item[5],item[6]);
                info.add(entity);
            }
        }

        if (info.isEmpty()) return;

        for(TGBasicInfoItemEntity i :info) {
            TGBasicInfoItemLayou gf = new TGBasicInfoItemLayou(getContext());
            gf.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            mContentView.addView(gf);
            this.getRootView().requestFocus();
            forms.add(gf);
            gf.setLongitude(i.longitude);
            gf.setLatitude(i.latitude);
            gf.setCount(i.count);
            gf.setLandSources(i.landSources);
            gf.setType(i.type);
            gf.setName(i.name);
            gf.setFieldId(i.id);
            gf.setOnDeleteListener(new OnDeleteClickListener() {
                @Override
                public void OnDelete(View v) {
                    del(v);
                    TGBasicInfoLayou.this.getRootView().requestFocus();
                }
            });
        }
}


    @Override
    public String convertToString() {
        infos=getInfo();
        String result="";
        if(infos!=null&&infos.size()!=0){
            for(TGBasicInfoItemEntity info:infos){
                result +=info.longitude+","+info.latitude+"," +info.count+","+info.landSources+","+info.type+","+info.name+","+info.id+";";
            }
        }
        return result;
    }
}
