package com.beiyun.workers.fragment.searchfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.beiyun.library.anot.Subscribe;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseSearchFragment;
import com.beiyun.workers.base.BaseWorkPageFragment;
import com.beiyun.workers.entity.FormalContractBean;
import com.beiyun.workers.entity.TGBasicInfoEntity;
import com.beiyun.workers.view.FieldLayout;
import com.beiyun.workers.view.FormView;
import com.beiyun.workers.view.SubsidyLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PlantContractInfoFragment extends BaseSearchFragment {


    @BindView(R.id.partAName)
    FormView partAName;
    @BindView(R.id.partBName)
    FormView partBName;
    @BindView(R.id.householdNo)
    FormView householdNo;
    @BindView(R.id.linkTel)
    FormView linkTel;
    @BindView(R.id.identity)
    FormView identity;
    @BindView(R.id.familyAddress)
    FormView familyAddress;
    @BindView(R.id.bank)
    FormView bank;
    @BindView(R.id.bankAccount)
    FormView bankAccount;
    @BindView(R.id.tobaccoPlot)
    FormView tobaccoPlot;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.fieldInfo)
    FieldLayout fieldInfo;
    @BindView(R.id.platArea)
    FormView platArea;
    @BindView(R.id.strainNum)
    FormView strainNum;
    @BindView(R.id.mandatoryAmount)
    FormView mandatoryAmount;
    @BindView(R.id.exportAmount)
    FormView exportAmount;
    @BindView(R.id.totalAmount)
    FormView totalAmount;
    @BindView(R.id.adjust1Area)
    FormView adjust1Area;
    @BindView(R.id.adjust1Strain)
    FormView adjust1Strain;
    @BindView(R.id.adjust1Mandatory)
    FormView adjust1Mandatory;
    @BindView(R.id.adjust1Export)
    FormView adjust1Export;
    @BindView(R.id.totalAmount1)
    FormView totalAmount1;
    @BindView(R.id.adjust2Area)
    FormView adjust2Area;
    @BindView(R.id.adjust2Strain)
    FormView adjust2Strain;
    @BindView(R.id.adjust2Mandatory)
    FormView adjust2Mandatory;
    @BindView(R.id.adjust2Export)
    FormView adjust2Export;
    @BindView(R.id.totalAmount2)
    FormView totalAmount2;
    @BindView(R.id.purchasePlace)
    FormView purchasePlace;
    @BindView(R.id.purchasePeriod)
    FormView purchasePeriod;
    @BindView(R.id.addSubsidy)
    Button addSubsidy;
    @BindView(R.id.subsidyInfo)
    SubsidyLayout subsidyInfo;
    @BindView(R.id.term1)
    FormView term1;
    @BindView(R.id.term2)
    FormView term2;
    @BindView(R.id.term3)
    FormView term3;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_contract_info, null);
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
    public void onReceive(FormalContractBean entity){

         partAName.setEditText(entity.getPartAName());
         partBName.setEditText(entity.getPartBName());
         householdNo.setEditText(entity.getHouseholdNo());
         linkTel.setEditText(entity.getLinkTel());
         identity.setEditText(entity.getIdentity());
         familyAddress.setEditText(entity.getFamilyAddress());
         bank.setEditText(entity.getBank());
         bankAccount.setEditText(entity.getBankAccount());
         tobaccoPlot.setEditText(entity.getTobaccoPlot());
         fieldInfo.setFieldInfo(entity.getFieldInfo());
         platArea.setEditText(entity.getPlatArea());
         strainNum.setEditText(entity.getStrainNum());
         mandatoryAmount.setEditText(entity.getMandatoryAmount());
         exportAmount.setEditText(entity.getExportAmount());
         adjust1Area.setEditText(entity.getAdjust1Area());
         adjust1Strain.setEditText(entity.getAdjust1Strain());
         adjust1Mandatory.setEditText(entity.getAdjust1Mandatory());
         adjust1Export.setEditText(entity.getAdjust1Export());
         adjust2Area.setEditText(entity.getAdjust2Area());
         adjust2Strain.setEditText(entity.getAdjust2Strain());
         adjust2Mandatory.setEditText(entity.getAdjust2Mandatory());
         adjust2Export.setEditText(entity.getAdjust2Export());
         purchasePlace.setEditText(entity.getPurchasePlace());
         purchasePeriod.setEditText(entity.getPurchasePeriod());
         subsidyInfo.setSubsidyInfo(entity.getSubsidyInfo());
        totalAmount.setText(String.valueOf(Double.valueOf(entity.getMandatoryAmount()) + Double.valueOf(entity.getExportAmount())));
        totalAmount1.setText(String.valueOf(Double.valueOf(entity.getAdjust1Mandatory()) + Double.valueOf(entity.getAdjust1Export())));
        totalAmount2.setText(String.valueOf(Double.valueOf(entity.getAdjust2Mandatory()) + Double.valueOf(entity.getAdjust2Export())));
        term1.setText(entity.getTerm1());
        term2.setText(entity.getTerm2());
        term3.setText(entity.getTerm3());
    }

}
