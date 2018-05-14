package com.beiyun.workers.utils;


import android.os.Build;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.beiyun.library.util.Logs;

import com.beiyun.workers.R;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.Address;
import com.beiyun.workers.okhttp.OkHttpUtils;
import com.beiyun.workers.okhttp.callback.BaseInfo;
import com.beiyun.workers.okhttp.callback.ResponseTCallBack;
import com.beiyun.workers.view.SpinnerLayout;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.angmarch.views.NiceSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public class AddressSelector {

    private MaterialSpinner[] spinners;
    private ViewGroup viewGroup;
    private SpinnerLayout spinnerLayout;
    private static HashMap<String,Address> addressMap = new HashMap<>();
    private static AddressSelector selector;

    private AddressSelector(ViewGroup viewGroup,MaterialSpinner[] spinners) {
        this.spinners = spinners;
        this.viewGroup = viewGroup;

    }

    private AddressSelector(SpinnerLayout spinnerLayout) {
        this.spinnerLayout = spinnerLayout;
        loadAddress(1,"");
    }


    private void loadAddress(final int type,String parentCode) {
        HashMap<String,String> params = new HashMap<>();
        params.put("bo.para",parentCode);
        params.put("bo.types",String.valueOf(type));
        OkHttpUtils.postQuery(AppUrl.get().ADDRESS, params, new ResponseTCallBack<BaseInfo<ArrayList<Address>>>() {
            @Override
            public void onFailure(IOException e) {
                Logs.e("SearchActivity loadAddress failed >"+e.getMessage());

            }

            @Override
            protected void onSuccess(BaseInfo<ArrayList<Address>> data) {
                Logs.e("SearchActivity loadAddress success >"+data);
                if(data.getResultCode() == 100){
                    List<Address> addresses = data.getData().getList();
                    if(addresses == null || addresses.size() == 0){
                        return;
                    }
                    setAddressData(addresses,type);
                }

            }
        });
    }


    public static void attachSpinners(ViewGroup viewGroup,MaterialSpinner... spinner){
        if(selector == null){
            synchronized (AddressSelector.class){
                if(selector == null){
                    selector = new AddressSelector(viewGroup,spinner);
                }
            }
        }

        clearAddressMap();
        selector.loadAddress(1,"");
    }

    public static void attachSpinners(SpinnerLayout spinnerLayout){
        new AddressSelector(spinnerLayout);
    }



    private void setAddressData(final List<Address> addresses, final int type) {
        List<String> names = new ArrayList<>();

        for (int i = 0; i < addresses.size(); i++) {
            Address address = addresses.get(i);
            names.add(address.getName());
        }
        spinners[type-1].setItems(names);
//        final MaterialSpinner spinner = spinnerLayout.addItem(type, "测试", names);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(viewGroup);
        }
        spinners[type-1].setVisibility(View.VISIBLE);
        spinners[type-1].setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Address address = addresses.get(position);
                Logs.e("AddressSelector item click"+ address);
                addressMap.put("key"+(type-1),address);
                clearCaches(type);

                if(type == 7){
                    return;
                }
                loadAddress(type+1,address.getCode());
            }
        });

    }


    private void clearCaches(int type) {
        if(type > 6){
            return;
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(viewGroup);
        }
        for (int i = type; i < spinners.length; i++) {
            //todo maybe has some idea to clear the bottom spinners

            spinners[i].setVisibility(View.GONE);

            Address remove = addressMap.remove("key" + i);
            Logs.e("------------------remove address >>"+remove);

        }

        Logs.e("------------AddressSelector clearCache addressMap = "+addressMap);
    }

    public static void clearAddressMap(){
        addressMap.clear();
        if(selector != null){
            selector.clearCaches(0);
        }


    }


    public static HashMap<String, Address> getAddressMap() {
        return addressMap;
    }
}

