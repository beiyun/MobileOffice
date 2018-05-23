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
import com.beiyun.workers.entity.SearchPublicEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicFragment extends BaseSearchFragment {


    @BindView(R.id.public_image)
    SimpleDraweeView publicImage;
    Unbinder unbinder;

    public PublicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_public, container, false);
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
    public void onReceive(SearchPublicEntity entity){
        publicImage.setImageURI(Uri.parse(AppUrl.get().BASE_IMAGE_URL + entity.getImage()));
    }
}
