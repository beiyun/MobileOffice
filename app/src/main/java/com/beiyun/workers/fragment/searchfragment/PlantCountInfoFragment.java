package com.beiyun.workers.fragment.searchfragment;

import android.net.Uri;
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
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.FormalContractBean;
import com.beiyun.workers.entity.SortAndCountEntity;
import com.beiyun.workers.view.FormView;
import com.beiyun.workers.view.SortStrainLayout;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PlantCountInfoFragment extends BaseSearchFragment {


    @BindView(R.id.year)
    FormView year;
    @BindView(R.id.towns)
    FormView towns;
    @BindView(R.id.village)
    FormView village;
    @BindView(R.id.villageGroup)
    FormView villageGroup;
    @BindView(R.id.name)
    FormView name;
    @BindView(R.id.variety)
    FormView variety;
    @BindView(R.id.sigArea)
    FormView sigArea;
    @BindView(R.id.sigStrains)
    FormView sigStrains;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.verification)
    SortStrainLayout verification;
    @BindView(R.id.totalNum)
    FormView totalNum;
    @BindView(R.id.totalArea)
    FormView totalArea;
    @BindView(R.id.mandatoryAmount)
    FormView mandatoryAmount;
    @BindView(R.id.exportAmount)
    FormView exportAmount;
    @BindView(R.id.pointTime)
    FormView pointTime;
    @BindView(R.id.villageConfirm)
    SimpleDraweeView villageConfirm;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_count_info, null);
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
    public void onReceive(SortAndCountEntity entity){
         year.setEditText(entity.getYear());
         towns.setEditText(entity.getTowns());
         village.setEditText(entity.getVillage());
         villageGroup.setEditText(entity.getVillageGroup());
         name.setEditText(entity.getName());
         variety.setEditText(entity.getVariety());
         sigArea.setEditText(entity.getSigArea());
         sigStrains.setEditText(entity.getSigStrains());
         verification.setFieldChangeInfo(entity.getVerifications());
         totalNum.setEditText(entity.getTotalNum());
         totalArea.setEditText(entity.getTotalArea());
         mandatoryAmount.setEditText(entity.getMandatoryAmount());
         exportAmount.setEditText(entity.getExportAmount());
         pointTime.setEditText(entity.getPointTime());
         villageConfirm.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getVillageConfirm()));
    }
}
