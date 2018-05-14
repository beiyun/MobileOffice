package com.beiyun.workers.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.beiyun.workers.entity.GrowTobaccoInfo;
import com.beiyun.workers.interf.IGrowTobaccoInfoFormLayout;
import com.beiyun.workers.interf.OnDeleteClickListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 种烟信息
 * Created by mpb on 2016/6/24.
 */
public class GrowTobaccoInfoFormLayout extends LinearLayout implements IGrowTobaccoInfoFormLayout {

    private List<GrowTobaccoInfoForm> forms = new ArrayList<>();

    private List<GrowTobaccoInfo> infos = new ArrayList<>();

    public GrowTobaccoInfoFormLayout(Context context) {
        this(context, null);
    }

    public GrowTobaccoInfoFormLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GrowTobaccoInfoFormLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        GrowTobaccoInfoForm gf = new GrowTobaccoInfoForm(getContext());
        gf.setLayoutParams(new LayoutParams(-1, -2));
        this.addView(gf);
        forms.add(gf);
        gf.setOnDeleteClickListener(new OnDeleteClickListener() {
            @Override
            public void OnDelete(View v) {
                reduce(v);
                GrowTobaccoInfoFormLayout.this.getRootView().requestFocus();
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
        List<GrowTobaccoInfo> growTobaccoInfo = getGrowTobaccoInfo();
        Log.d(TAG, "reduce: "+growTobaccoInfo);
        setGrowTobaccoInfo(growTobaccoInfo);
    }

    @Override
    public List<GrowTobaccoInfo> getGrowTobaccoInfo() {
        infos.clear();
        for (GrowTobaccoInfoForm form : forms) {
            GrowTobaccoInfo info = new GrowTobaccoInfo();
            info.id = form.getFieldId();
            info.fieldName = form.getFieldName();
            info.area = form.getArea();
            info.areaCode = form.getAreaCode();
            info.landSources = form.getLandResource();
            info.preceding = form.getPreceding();
//            if (info.areaCode != null && info.fieldName != null) {
                infos.add(info);
//            }
        }
        Log.e(TAG, "getGrowTobaccoInfo: "+infos );
        return infos;
    }



    public void setGrowTobaccoInfo(String data) {
        this.removeAllViews();
        forms.clear();
        if(data == null || TextUtils.isEmpty(data))return;

        List<GrowTobaccoInfo> infos = new ArrayList<>();
        String[] split = data.split(";");
        for (String string : split) {
            String[] item = string.split(",");
            if (item.length != 0) {
                GrowTobaccoInfo entity = new GrowTobaccoInfo(item[0], item[1], item[2], item[3],item[4],item[5]);
                infos.add(entity);
            }
        }

        queryData = infos;
        getTypeArea(infos);//获取自有或租赁徒弟面积

        if (infos.isEmpty()) return;
        for (GrowTobaccoInfo info : infos) {
            final GrowTobaccoInfoForm gf = new GrowTobaccoInfoForm(getContext());
            gf.setLayoutParams(new LayoutParams(-1, -2));
            this.addView(gf);
            this.getRootView().requestFocus();
            forms.add(gf);
            gf.setFieldName(info.fieldName);
            gf.setLandResource(info.landSources);
            gf.setArea(info.area);
            gf.setFieldId(info.id);
            gf.setAreaCode(info.areaCode);
            gf.setPreceding(info.preceding);
            gf.setOnDeleteClickListener(new OnDeleteClickListener() {
                @Override
                public void OnDelete(View v) {
                    reduce(gf);
                }
            });
        }
    }


    private List<GrowTobaccoInfo> queryData;
    @Override
    public void setGrowTobaccoInfo(List<GrowTobaccoInfo> infos) {
        this.removeAllViews();
        forms.clear();
        Log.d(TAG, "setGrowTobaccoInfo: "+infos);
        if (infos != null) {
            queryData = infos;
            getTypeArea(infos);//获取自有或租赁徒弟面积
            if (infos.isEmpty()) return;
            for (GrowTobaccoInfo info : infos) {
                final GrowTobaccoInfoForm gf = new GrowTobaccoInfoForm(getContext());
                gf.setLayoutParams(new LayoutParams(-1, -2));
                this.addView(gf);
                this.getRootView().requestFocus();
                forms.add(gf);
                gf.setFieldId(info.id);
                gf.setFieldName(info.fieldName);
                gf.setArea(info.area);
                gf.setAreaCode(info.areaCode);
                gf.setLandResource(info.landSources);
                gf.setPreceding(info.preceding);
                gf.setOnDeleteClickListener(new OnDeleteClickListener() {
                    @Override
                    public void OnDelete(View v) {
                        reduce(gf);
                    }
                });
            }
        }
    }

    private AreaChangedListener mListener;

    public interface AreaChangedListener{
        void onChanged(String ownArea, String zuLinArea);
    }

    public void setOnAreaChangedListener(AreaChangedListener listener){
        this.mListener = listener;
    }

    private void getTypeArea(List<GrowTobaccoInfo> infos) {
        double ownArea = 0;//自由土地面积
        double zuLinArea = 0;//租赁土地面积

        for (GrowTobaccoInfo info:infos) {
            Log.d(TAG, "getTypeArea: "+info);
            if("1".equals(info.landSources)){
                ownArea += Double.valueOf(info.area);
            }else if("2".equals(info.landSources)){
                zuLinArea += Double.valueOf(info.area);
            }
        }



        if(mListener != null){
            BigDecimal bd = new BigDecimal(ownArea);
            ownArea = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            BigDecimal bd1 = new BigDecimal(zuLinArea);
            zuLinArea = bd1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            mListener.onChanged(String.valueOf(ownArea),String.valueOf(zuLinArea));
        }
    }



    private static final String TAG = "convertToString";

    @Override
    public String convertToString() {
        infos = getGrowTobaccoInfo();
        String result = "";
        if (infos != null && infos.size() != 0) {
            for (GrowTobaccoInfo info : infos
                    ) {
                result += info.id+","+info.fieldName + "," + info.area + "," + info.areaCode + "," + info.preceding +","+info.landSources+ ";";
            }
        }

        Log.d(TAG, "convertToString: "+result);
        return result;
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        for (GrowTobaccoInfoForm form : forms) {
            form.setFocusable(focusable);
        }
    }
}
