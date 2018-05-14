package com.beiyun.workers.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.beiyun.workers.fragment.workfragment.WorkFragPage1;
import com.beiyun.workers.fragment.workfragment.WorkFragPage2;

import java.util.ArrayList;

/**
 * Created by beiyun on 2018/4/4.
 * Workers
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new WorkFragPage1());
        fragments.add(new WorkFragPage2());
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
