package com.beiyun.workers.fragment.learnfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beiyun.library.util.Events;
import com.beiyun.workers.R;
import com.beiyun.workers.utils.TestSimpleDataUtil;
import com.beiyun.workers.ui.VideoActivity;
import com.beiyun.workers.adapter.LearnPage1Adapter;
import com.beiyun.workers.base.BaseWorkPageFragment;
import com.beiyun.workers.entity.LearnPage1Entity;
import com.beiyun.workers.utils.MainFabControl;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragPage1 extends BaseWorkPageFragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.learn_page1_recyclerView)
    RecyclerView mPage1Rv;
    @BindView(R.id.learn_page1_refreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    Unbinder unbinder;
    private LearnPage1Adapter mAdapter;

    public LearnFragPage1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learn_frag_page1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefreshLayout();
        loadData();
    }

    private void loadData() {
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
                onRefresh();

            }
        });

    }



    private void initAdapter() {
        mAdapter = new LearnPage1Adapter(getData());
        mPage1Rv.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mAdapter.getItemCount() >= 100){
                            mAdapter.loadMoreEnd();
                        }else{
                            mAdapter.addData(getData());
                            mAdapter.loadMoreComplete();
                        }
                    }
                },1500);
            }
        },mPage1Rv);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SimpleDraweeView image = view.findViewById(R.id.item_learn_page1_image);
                LearnPage1Entity entity = (LearnPage1Entity) adapter.getData().get(position);
                Events.post(entity);
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(mainActivity, image,getString(R.string.videoImage));
                ActivityCompat.startActivity(mainActivity, new Intent(getActivity(), VideoActivity.class),
                        compat.toBundle());
                itemPosition = position;
            }
        });

    }




    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && mPage1Rv!= null){
            mainActivity.attachFab(mPage1Rv);
        }
    }

    private List<LearnPage1Entity> getData() {

        List<LearnPage1Entity> data = new ArrayList<>();

        for (int i = 0; i < TestSimpleDataUtil.IMAGE_RES.length; i++) {
            LearnPage1Entity entity = new LearnPage1Entity();
            entity.setImageRes(TestSimpleDataUtil.IMAGE_RES[i]);
            entity.setVideoUrl(TestSimpleDataUtil.VIDEO_URLS[i]);
            entity.setThumbImageUrl(TestSimpleDataUtil.VIDEO_THUMBS[i]);
            if(i%2 == 0){

                entity.setAuthor("村上春树");
                entity.setPlayTimes(10000);
                entity.setTitle("盈盈一水间，脉脉不相语");
                entity.setSubTitle("云想衣裳花想容，春风拂槛露华浓。若非群玉山山头见，会向瑶台月下逢。");
                entity.setVideoLength("0:45:04");
                entity.setUploadDate("2018-2-12");
                entity.setContent("ConstraintLayout对可见性被标记View.GONE的控件（后称“GONE控件”）有特殊的处理。一般情况下，GONG控件是不可见的，且不再是布局的一部分，但是在布局计算上，ConstraintLayout与传统布局有一个很重要的区别：");
            }else{
                entity.setAuthor("CardView Google");
                entity.setPlayTimes(5300);
                entity.setUploadDate("2017-12-23");
                entity.setTitle("CardView is useful in layout,the theme is very beautiful. when you scrolled,you can use some animations with it.");
                entity.setSubTitle("Using the Hello World guide, you’ll create a repository, start a branch, \n" +
                        "write comments, and open a pull request.");
                entity.setVideoLength("1:20:46");
                entity.setContent(getString(R.string.large_text));

            }

            data.add(entity);

        }
        return data;
    }

    private void initRecyclerView() {
        mPage1Rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPage1Rv.setHasFixedSize(true);
        mPage1Rv.setNestedScrollingEnabled(false);
        mainActivity.attachFab(mPage1Rv);
        MainFabControl control = new MainFabControl();
        control.controlFab(mPage1Rv,mListener);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent);
        mRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mAdapter == null){
                    initRecyclerView();
                    initAdapter();
                }else{
                    mAdapter.setNewData(getData());
                }
                mRefreshLayout.setRefreshing(false);
            }
        },500);
    }


    private int itemPosition = -1;
    @Override
    public void onResume() {
        super.onResume();
        if(itemPosition != -1){
            mAdapter.notifyItemChanged(itemPosition+1);
        }

    }
}
