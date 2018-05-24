package com.beiyun.workers.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beiyun.library.util.Apps;
import com.beiyun.library.util.Events;
import com.beiyun.library.util.Logs;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.News;
import com.beiyun.workers.okhttp.OkHttpUtils;
import com.beiyun.workers.okhttp.callback.BaseInfo;
import com.beiyun.workers.okhttp.callback.ResponseTCallBack;
import com.beiyun.workers.ui.BannerDetailActivity;
import com.beiyun.workers.ui.MainListDetailActivity;
import com.beiyun.workers.R;
import com.beiyun.workers.adapter.MainFragmentAdapter;
import com.beiyun.workers.base.BaseFragment;
import com.beiyun.workers.utils.MainFabControl;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * @author Administrator
 */
public class MainFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private Banner bannerView;
    int[] BANNERS = {R.mipmap.banner1,R.mipmap.banner2,
    R.mipmap.banner3,R.mipmap.banner4,R.mipmap.banner5
    ,R.mipmap.banner6};
    private RecyclerView rv;
    private MainFragmentAdapter mAdapter;
    private SwipeRefreshLayout refreshLayout;
    private int totalSize;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        if(bannerView != null) {
            bannerView.startAutoPlay();
        }
        Logs.e("itemPosition ==" +itemPosition);
        if(itemPosition != -1){
            mAdapter.notifyItemChanged(itemPosition+1);
        }

        Logs.e("MainFragment onResume ----");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            mainActivity.attachFab(rv);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(bannerView != null) {
            bannerView.stopAutoPlay();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefreshLayout(view);
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

    private void initRefreshLayout(View view) {
        refreshLayout = view.findViewById(R.id.refreshLayout);
        rv = view.findViewById(R.id.recyclerView);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent,R.color.deepBlue);
        refreshLayout.setOnRefreshListener(this);
    }

    private int itemPosition = -1;
    private void initAdapter(List<News> news) {
        mAdapter = new MainFragmentAdapter(news);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SimpleDraweeView image = (SimpleDraweeView) view.findViewById(R.id.item_main_image);
                News o = (News) adapter.getData().get(position);
                Events.post(o);
                mainActivity.startActivity(new Intent(getActivity(), MainListDetailActivity.class),image);
                itemPosition = position;
            }
        });



        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rv.post(new Runnable() {
                    @Override
                    public void run() {
                        if(mAdapter.getItemCount()>=totalSize){
                            mAdapter.loadMoreEnd();
                        }else{
                            loadListData();
//                            initLocalData();
                        }
                    }
                });

                Logs.e("onLoadMoreRequested -----------");
            }

        },rv);


        rv.setAdapter(mAdapter);
        rv.scrollTo(0,0);


    }

    private void initBannerHeader() {
        View view = Apps.getLayoutInflater().inflate(R.layout.layout_main_header,null);
        bannerView = view.findViewById(R.id.banner);
        mAdapter.addHeaderView(view);
        OkHttpUtils.postQuery(AppUrl.get().BANNER, null, new ResponseTCallBack<BaseInfo<ArrayList<com.beiyun.workers.entity.Banner>>>() {
            @Override
            public void onFailure(IOException e) {
                Logs.e("MainFragment banner failure = "+e.getMessage());

            }

            @Override
            protected void onSuccess(BaseInfo<ArrayList<com.beiyun.workers.entity.Banner>> data) {
                if(data.getResultCode() == 100){
                    ArrayList<com.beiyun.workers.entity.Banner> list = data.getData().getList();
                    if(list != null && list.size() != 0){
                        setBannerData(list);
                    }

                }else{
                    mainActivity.toastError(data.getReason());
                }
            }
        });
    }

    private void setBannerData(final ArrayList<com.beiyun.workers.entity.Banner> banners) {
        List<String> urls = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < banners.size(); i++) {
            urls.add(banners.get(i).getBannerUrl());
            titles.add(banners.get(i).getExplain());
        }




        //设置banner样式
        bannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//        bannerView.setIndicatorGravity(Banner.SCROLL_INDICATOR_RIGHT);
        //设置图片加载器
        bannerView.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bannerView.setImages(urls);
        //设置banner动画效果
        bannerView.setBannerAnimation(Transformer.ZoomOut);
        //设置标题集合（当banner样式有显示title时）
        bannerView.setBannerTitles(titles);
        //设置自动轮播，默认为true
        bannerView.isAutoPlay(true);
        //设置轮播时间
        bannerView.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bannerView.setIndicatorGravity(BannerConfig.CENTER);

        bannerView.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Events.post(banners.get(position).getBannerDetailUrl());
                mainActivity.startActivity(new Intent(mainActivity, BannerDetailActivity.class),bannerView);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        bannerView.start();

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */


            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse(AppUrl.get().BASE_IMAGE_URL+path);
            imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {

            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            return Apps.getLayoutInflater().inflate(R.layout.item_banner_image,null).<SimpleDraweeView>findViewById(R.id.item_banner_imageview);
        }
    }




    private void initRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(Apps.getContext()));
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);
        mainActivity.attachFab(rv);
        new MainFabControl().controlFab(rv,mListener);
    }



    @Override
    public void onRefresh() {
        if(mAdapter != null){
            mAdapter.setEnableLoadMore(false);
        }
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                if(mAdapter == null){
                    initRecyclerView();
                    loadListData();
                    mainActivity.supportStartPostponedEnterTransition();
                }else{
                    loadListData();
                }

            }
        },500);
    }


    private int page = 1;

    private void loadListData() {
        final HashMap<String,String> params = new HashMap<>();
        params.put("bo.page", String.valueOf(page));

        OkHttpUtils.postQuery(AppUrl.get().NEWS, params, new ResponseTCallBack<BaseInfo<ArrayList<News>>>() {
            @Override
            public void onFailure(IOException e) {
                refreshLayout.setRefreshing(false);
                Logs.e("MainFragment load News Data failed >"+e.getMessage());
            }

            @Override
            protected void onSuccess(BaseInfo<ArrayList<News>> data) {
                Logs.e("MainFragment load News Data success >"+ page +">"+data);
                if(data.getResultCode()!= 100){
                    mainActivity.toastError(data.getReason());
                    return;
                }

                totalSize = data.getData().total;

                ArrayList<News> news = data.getData().getList();
                if(news == null || news.size() == 0){
                    mainActivity.toastError("没有数据");
                    return;
                }

                for (int i = 0; i < news.size(); i++) {
                    news.get(i).setTitleImage(News.getTitleImageRes(i));
                    news.get(i).setCounter(News.getCounter(i));
                }


                refreshLayout.setRefreshing(false);
                if(page == 1){
                    initAdapter(news);
                    initBannerHeader();
                    mAdapter.setEnableLoadMore(true);
                }else{
                    mAdapter.addData(news);
                    mAdapter.loadMoreComplete();
                }
                page ++;

            }
        });

    }


}
