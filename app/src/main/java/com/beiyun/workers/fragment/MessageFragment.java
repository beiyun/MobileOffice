package com.beiyun.workers.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beiyun.library.util.Events;
import com.beiyun.workers.ui.MessageDetailActivity;
import com.beiyun.workers.R;
import com.beiyun.workers.utils.TestSimpleDataUtil;
import com.beiyun.workers.adapter.MessageFragAdapter;
import com.beiyun.workers.base.BaseFragment;
import com.beiyun.workers.entity.MessageEntity;
import com.beiyun.workers.utils.MainFabControl;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private RecyclerView rv;
    private SwipeRefreshLayout refreshLayout;
    private MessageFragAdapter mAdapter;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent,R.color.deepBlue);
        refreshLayout.setOnRefreshListener(this);
        rv = view.findViewById(R.id.messageList);
        loadData();



    }

    private void loadData() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                onRefresh();
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        if(itemPosition != -1){
            rv.requestLayout();
        }

    }

    private int itemPosition = -1;
    private void initAdapter() {
        mAdapter = new MessageFragAdapter(getData());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SimpleDraweeView image = (SimpleDraweeView) view.findViewById(R.id.item_message_image);
                MessageEntity o = (MessageEntity) adapter.getData().get(position);
                Events.post(o);
                mainActivity.startActivity(new Intent(getActivity(), MessageDetailActivity.class),
                        image);
                itemPosition = position;

            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
              rv.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      if(mAdapter.getItemCount()> 100){
                          mAdapter.loadMoreEnd();
                      } else{
                          mAdapter.addData(getData());
                          mAdapter.loadMoreComplete();
                      }
                  }
              },1000);
            }
        },rv);

        rv.setAdapter(mAdapter);

    }

    private void initRecyclerView() {
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(mainActivity));
        mainActivity.attachFab(rv);
        new MainFabControl().controlFab(rv,mListener);
    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            mainActivity.attachFab(rv);
        }
    }


    public List<MessageEntity> getData() {
        List<MessageEntity> data = new ArrayList<>();
        int[] imageRes = TestSimpleDataUtil.IMAGE_RES;
        for (int i = 0; i < imageRes.length; i++) {
            MessageEntity entity = new MessageEntity();
            if(i%2 == 0){
                entity.setTitle("Android floating action button which reacts on scrolling events. Becomes visible when an attached target is scrolled up and invisible when scrolled down.");
                entity.setDate("一个月前");
            }else{
                entity.setTitle("默认使用一个 shader 绘制圆角，但是仅仅占位图和所要显示的图有圆角效果。失败示意图和重下载示意图无圆角效果，且这种圆角方式不支持动画。");
                entity.setDate("5天前");
            }
            entity.setImageRes(imageRes[i]);
            data.add(entity);
        }


        return data;
    }

    @Override
    public void onRefresh() {
        if(mAdapter != null) {
            mAdapter.setEnableLoadMore(false);
        }
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mAdapter == null){
                    initRecyclerView();
                    initAdapter();
                }else{
                    mAdapter.setNewData(getData());
                    mAdapter.setEnableLoadMore(true);
                }

                refreshLayout.setRefreshing(false);
            }
        },500);
    }
}
