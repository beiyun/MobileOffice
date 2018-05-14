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
import com.beiyun.workers.entity.ApplyAcceptEntity;
import com.beiyun.workers.entity.TGBasicInfoEntity;
import com.beiyun.workers.view.FormView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Administrator
 * 种植申请
 */
public class ApplyPlantInfoFragment extends BaseSearchFragment {


    @BindView(R.id.village)
    FormView village;
    @BindView(R.id.villageGroup)
    FormView villageGroup;
    @BindView(R.id.name)
    FormView name;
    @BindView(R.id.accountNumber)
    FormView accountNumber;
    @BindView(R.id.arableArea)
    FormView arableArea;
    @BindView(R.id.leaseArea)
    FormView leaseArea;
    @BindView(R.id.tianArea)
    FormView tianArea;
    @BindView(R.id.diArea)
    FormView diArea;
    @BindView(R.id.applyArea)
    FormView applyArea;
    @BindView(R.id.applyTian)
    FormView applyTian;
    @BindView(R.id.applyDi)
    FormView applyDi;
    @BindView(R.id.city)
    FormView city;
    @BindView(R.id.insureCost)
    FormView insureCost;
    @BindView(R.id.insureArea)
    FormView insureArea;
    @BindView(R.id.partyBSignature)
    SimpleDraweeView partyBSignature;
    @BindView(R.id.declareTime)
    FormView declareTime;
    @BindView(R.id.agreeArea)
    FormView agreeArea;
    @BindView(R.id.agreeTian)
    FormView agreeTian;
    @BindView(R.id.agreeDi)
    FormView agreeDi;
    @BindView(R.id.agreeVariety)
    FormView agreeVariety;
    @BindView(R.id.auditUnit)
    SimpleDraweeView auditUnit;
    @BindView(R.id.auditTime)
    FormView auditTime;
    @BindView(R.id.rejectedReason_a)
    FormView rejectedReasonA;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply_accept, null);
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
    public void onReceive(ApplyAcceptEntity entity){
         village.setEditText(entity.getVillage());
         villageGroup.setEditText(entity.getVillageGroup());
         name.setEditText(entity.getName());
         accountNumber.setEditText(entity.getAccountNumber());
         arableArea.setEditText(entity.getArableArea());
         leaseArea.setEditText(entity.getLeaseArea());
         tianArea.setEditText(entity.getTianArea());
         diArea.setEditText(entity.getDiArea());
         applyArea.setEditText(entity.getApplyArea());
         applyTian.setEditText(entity.getApplyTian());
         applyDi.setEditText(entity.getApplyDi());;
         city.setEditText(entity.getCity());;
         insureCost.setEditText(entity.getInsureCost());;
         insureArea.setEditText(entity.getInsureArea());;
         partyBSignature.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getPartyBSignature()));
         declareTime.setEditText(entity.getDeclareTime());;
         agreeArea.setEditText(entity.getAgreeArea());;
         agreeTian.setEditText(entity.getAgreeTian());;
         agreeDi.setEditText(entity.getAgreeDi());;
         agreeVariety.setEditText(entity.getAgreeVariety());;
         auditUnit.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getAuditUnit()));
         auditTime.setEditText(entity.getAuditTime());;
         rejectedReasonA.setEditText(entity.getRejectedReason());;

    }
}
