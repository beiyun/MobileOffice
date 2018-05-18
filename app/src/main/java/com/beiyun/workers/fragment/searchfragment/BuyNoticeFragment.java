package com.beiyun.workers.fragment.searchfragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beiyun.library.anot.Subscribe;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseSearchFragment;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.BaseStationEntity;
import com.beiyun.workers.entity.NoticeEntity;
import com.beiyun.workers.view.FormView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyNoticeFragment extends BaseSearchFragment {


    @BindView(R.id.points)
    FormView points;
    @BindView(R.id.name)
    FormView name;
    @BindView(R.id.phone)
    FormView phone;
    @BindView(R.id.weight)
    FormView weight;
    @BindView(R.id.classificationPlace)
    FormView classificationPlace;
    @BindView(R.id.startDate)
    FormView startDate;
    @BindView(R.id.startTime)
    FormView startTime;
    @BindView(R.id.endDate)
    FormView endDate;
    @BindView(R.id.endTime)
    FormView endTime;
    @BindView(R.id.sellPlace)
    FormView sellPlace;
    @BindView(R.id.beginDate)
    FormView beginDate;
    @BindView(R.id.beginTime)
    FormView beginTime;
    @BindView(R.id.finishDate)
    FormView finishDate;
    @BindView(R.id.finishTime)
    FormView finishTime;
    @BindView(R.id.signature)
    SimpleDraweeView signature;
    @BindView(R.id.signTime)
    FormView signTime;
    Unbinder unbinder;

    public BuyNoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy_notice, container, false);
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
    public void onReceive(NoticeEntity entity){
         points.setEditText(entity.getPoints());
         name.setEditText(entity.getName());
         phone.setEditText(entity.getPhone());
         weight.setEditText(entity.getWeight());
         classificationPlace.setEditText(entity.getClassificationPlace());
         startDate.setEditText(entity.getStartDate());
         startTime.setEditText(entity.getStartTime());
         endDate.setEditText(entity.getEntDate());
         endTime.setEditText(entity.getEndTime());
         sellPlace.setEditText(entity.getSellPlace());
         beginDate.setEditText(entity.getBeginDate());
         beginTime.setEditText(entity.getBeginTime());
         finishDate.setEditText(entity.getFinishDate());
         finishTime.setEditText(entity.getFinishTime());
         signature.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getSignature()));
         signTime.setEditText(entity.getSignTime());


    }
}
