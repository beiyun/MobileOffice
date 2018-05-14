package com.beiyun.workers.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.beiyun.workers.fragment.learnfragment.LearnFragPage1;
import com.beiyun.workers.fragment.learnfragment.LearnFragPage2;

import java.util.ArrayList;

/**
 * Created by beiyun on 2018/4/6.
 * Workers
 */
public class LearnFragViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public LearnFragViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new LearnFragPage1());
        fragments.add(new LearnFragPage2());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
