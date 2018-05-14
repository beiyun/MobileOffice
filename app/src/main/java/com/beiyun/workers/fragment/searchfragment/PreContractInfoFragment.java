package com.beiyun.workers.fragment.searchfragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.beiyun.library.anot.Subscribe;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseSearchFragment;
import com.beiyun.workers.base.BaseWorkPageFragment;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.CheckQualificationBean;
import com.beiyun.workers.entity.PreContractBean;
import com.beiyun.workers.view.FormView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PreContractInfoFragment extends BaseSearchFragment {


    @BindView(R.id.city)
    FormView city;
    @BindView(R.id.company)
    FormView company;
    @BindView(R.id.tobaccoPlot)
    FormView tobaccoPlot;
    @BindView(R.id.name)
    FormView name;
    @BindView(R.id.identity)
    FormView identity;
    @BindView(R.id.familyAddress)
    FormView familyAddress;
    @BindView(R.id.householdNo)
    FormView householdNo;
    @BindView(R.id.tel)
    FormView tel;
    @BindView(R.id.plantArea)
    FormView plantArea;
    @BindView(R.id.strainNum)
    FormView strainNum;
    @BindView(R.id.plantVariety)
    FormView plantVariety;
    @BindView(R.id.purchaseAmount)
    FormView purchaseAmount;
    @BindView(R.id.mandatoryAmount)
    FormView mandatoryAmount;
    @BindView(R.id.exportAmount)
    FormView exportAmount;
    @BindView(R.id.jiaSign)
    SimpleDraweeView jiaSign;
    @BindView(R.id.jiaTime)
    FormView jiaTime;
    @BindView(R.id.yiSign)
    SimpleDraweeView yiSign;
    @BindView(R.id.yiTime)
    FormView yiTime;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pre_contract_info, null);
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
    public void onReceive(PreContractBean entity){
         city.setEditText(entity.getCity());
         company.setEditText(entity.getCompany());
         tobaccoPlot.setEditText(entity.getTobaccoPlot());
         name.setEditText(entity.getName());
         identity.setEditText(entity.getIdentity());
         familyAddress.setEditText(entity.getFamilyAddress());
         householdNo.setEditText(entity.getHouseholdNo());
         tel.setEditText(entity.getTel());
         plantArea.setEditText(entity.getPlantArea());
         strainNum.setEditText(entity.getStrainNum());
         plantVariety.setEditText(entity.getPlantVariety());
         purchaseAmount.setEditText(entity.getPurchaseAmount());
         mandatoryAmount.setEditText(entity.getMandatoryAmount());
         exportAmount.setEditText(entity.getExportAmount());
         jiaSign.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getJiaSign()));
         jiaTime.setEditText(entity.getJiaTime());
         yiSign.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getYiSign()));
         yiTime.setEditText(entity.getYiTime());
    }
}
