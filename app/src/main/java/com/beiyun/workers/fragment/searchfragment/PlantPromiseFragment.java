package com.beiyun.workers.fragment.searchfragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.beiyun.library.anot.Subscribe;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseSearchFragment;
import com.beiyun.workers.base.BaseWorkPageFragment;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.SortAndCountEntity;
import com.beiyun.workers.entity.TGLetterOfCommitmentBean;
import com.beiyun.workers.view.FormView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlantPromiseFragment extends BaseSearchFragment {


    @BindView(R.id.townName)
    FormView townName;
    @BindView(R.id.village)
    FormView village;
    @BindView(R.id.villageGroup)
    FormView villageGroup;
    @BindView(R.id.expectArea)
    FormView expectArea;
    @BindView(R.id.variety)
    FormView variety;
    @BindView(R.id.year)
    FormView year;
    @BindView(R.id.signature)
    SimpleDraweeView signature;
    @BindView(R.id.promise)
    FormView promise;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    Unbinder unbinder;

    public PlantPromiseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_promise, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
    public void onReceive(TGLetterOfCommitmentBean entity){
         townName.setEditText(entity.getTownName());
         village.setEditText(entity.getVillage());
         villageGroup.setEditText(entity.getVillageGroup());
         expectArea.setEditText(entity.getExpectArea());
         variety.setEditText(entity.getVariety());
         year.setEditText(entity.getYear());
         signature.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getSignature()));
         promise.setEditText(entity.getPromise());
    }
}
