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
import com.beiyun.workers.entity.CheckQualificationBean;
import com.beiyun.workers.entity.TGLetterOfCommitmentBean;
import com.beiyun.workers.view.FormView;
import com.beiyun.workers.view.GrowTobaccoInfoFormLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PlantQualificationInfoFragment extends BaseSearchFragment {


    @BindView(R.id.towns)
    FormView towns;
    @BindView(R.id.village)
    FormView village;
    @BindView(R.id.villageGroup)
    FormView villageGroup;
    @BindView(R.id.name)
    FormView name;
    @BindView(R.id.aid)
    FormView aid;
    @BindView(R.id.applyArea)
    FormView applyArea;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.growInfoForm)
    GrowTobaccoInfoFormLayout growInfoForm;
    @BindView(R.id.grow_info_layout)
    LinearLayout growInfoLayout;
    @BindView(R.id.arableArea)
    FormView arableArea;
    @BindView(R.id.leaseArea)
    FormView leaseArea;
    @BindView(R.id.preceding_qualification)
    FormView precedingQualification;
    @BindView(R.id.previousArea)
    FormView previousArea;
    @BindView(R.id.combineArea)
    FormView combineArea;
    @BindView(R.id.labour)
    FormView labour;
    @BindView(R.id.executionRate)
    FormView executionRate;
    @BindView(R.id.level)
    FormView level;
    @BindView(R.id.barnNumber)
    FormView barnNumber;
    @BindView(R.id.denseBarn)
    FormView denseBarn;
    @BindView(R.id.capacity)
    FormView capacity;
    @BindView(R.id.projectSituation)
    FormView projectSituation;
    @BindView(R.id.certificate)
    FormView certificate;
    @BindView(R.id.evaluationDegree)
    FormView evaluationDegree;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_qualification, null);
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
    public void onReceive(CheckQualificationBean entity){
         towns.setEditText(entity.getTowns());
         village.setEditText(entity.getVillage());
         villageGroup.setEditText(entity.getVillageGroup());
         name.setEditText(entity.getName());
         aid.setEditText(entity.getAid());
         applyArea.setEditText(entity.getApplyArea());
         growInfoForm.setGrowTobaccoInfo(entity.getPlatTobaccoInfos());
         arableArea.setEditText(entity.getArableArea());
         leaseArea.setEditText(entity.getLeaseArea());
         precedingQualification.setEditText(entity.getPreceding());
         previousArea.setEditText(entity.getPreviousArea());
         combineArea.setEditText(entity.getCombineArea());
         labour.setEditText(entity.getLabour());
         executionRate.setEditText(entity.getExecutionRate());
         level.setEditText(entity.getLevel());
         barnNumber.setEditText(entity.getBarnNumber());
         denseBarn.setEditText(entity.getDenseBarn());
         capacity.setEditText(entity.getCapacity());
         projectSituation.setEditText(entity.getProjectSituation());
         certificate.setEditText(entity.getCertificate());
         evaluationDegree.setEditText(entity.getEvaluationDegree());

    }
}
