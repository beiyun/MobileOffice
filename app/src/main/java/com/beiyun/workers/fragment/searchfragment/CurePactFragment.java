package com.beiyun.workers.fragment.searchfragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.beiyun.library.anot.Subscribe;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseSearchFragment;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.CuredPactEntity;
import com.beiyun.workers.entity.NoticeEntity;
import com.beiyun.workers.view.FormView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurePactFragment extends BaseSearchFragment {


    @BindView(R.id.years)
    FormView years;
    @BindView(R.id.partyA)
    FormView partyA;
    @BindView(R.id.partyB)
    FormView partyB;
    @BindView(R.id.shoufeibiaozhun1)
    RadioButton shoufeibiaozhun1;
    @BindView(R.id.shoufeibiaozhun2)
    RadioButton shoufeibiaozhun2;
    @BindView(R.id.shoufeibiaozhun)
    RadioGroup shoufeibiaozhun;
    @BindView(R.id.signTime)
    FormView signTime;
    Unbinder unbinder;
    @BindView(R.id.signatureA)
    SimpleDraweeView signatureA;
    @BindView(R.id.signatureB)
    SimpleDraweeView signatureB;

    public CurePactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cure_pact, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        com.beiyun.library.util.Events.register(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        com.beiyun.library.util.Events.unregister(this);
    }


    @Subscribe
    public void onReceive(CuredPactEntity entity){
         years.setEditText(entity.getYears());
         partyA.setEditText(entity.getPartyA());
         partyB.setEditText(entity.getPartyB());
        if("1".equals(entity.getFees())){
            shoufeibiaozhun1.setChecked(true);
        }else if("2".equals(entity.getFees())){
            shoufeibiaozhun2.setChecked(true);
        }
         signTime.setEditText(entity.getSignTime());
         signatureA.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getSignatureA()));
         signatureB.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getSignatureB()));

    }
}
