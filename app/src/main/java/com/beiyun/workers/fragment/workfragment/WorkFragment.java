package com.beiyun.workers.fragment.workfragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.beiyun.library.util.Windows;
import com.beiyun.workers.R;
import com.beiyun.workers.adapter.ViewPagerAdapter;
import com.beiyun.workers.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkFragment extends BaseFragment {


    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    public WorkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewPager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(mainActivity.getSupportFragmentManager());
        appBarLayout = mainActivity.findViewById(R.id.app_bar);
        changeSize(true);
        viewPager.setAdapter(pagerAdapter);
        initTabLayout();

    }

    private void initTabLayout() {
        tabLayout = mainActivity.getTabLayout();
        if(tabLayout.getTabCount() == 0){
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
        }
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("下达");
        tabLayout.getTabAt(1).setText("跟踪");
    }

    private void changeSize(final boolean bigger) {

        appBarLayout.post(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(appBarLayout);
                ViewGroup.LayoutParams params = appBarLayout.getLayoutParams();
                if(bigger){
                    params.width = WindowManager.LayoutParams.MATCH_PARENT;
                    params.height = Windows.getActionBarHeight()*2;
                    tabLayout.setVisibility(View.VISIBLE);
                }else{
                    params.width = WindowManager.LayoutParams.MATCH_PARENT;
                    params.height = Windows.getActionBarHeight();
                    tabLayout.setVisibility(View.GONE);
                }

                appBarLayout.setLayoutParams(params);
            }
        });

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            changeSize(false);
        }else {
            changeSize(true);
           initTabLayout();
        }
    }
}
