package com.beiyun.workers.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.beiyun.library.util.Logs;
import com.beiyun.workers.ui.MainActivity;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseFragment;
import com.beiyun.workers.fragment.learnfragment.LearnFragment;
import com.beiyun.workers.fragment.MainFragment;
import com.beiyun.workers.fragment.MessageFragment;
import com.beiyun.workers.fragment.PersonFragment;
import com.beiyun.workers.fragment.SettingFragment;
import com.beiyun.workers.fragment.workfragment.WorkFragment;

/**
 * Created by beiyun on 2018/3/30.
 * Workers
 */
public class FragmentControl {

    MainFragment mainFragment;
    LearnFragment learnFragment;
    MessageFragment messageFragment;
    PersonFragment personFragment;
    SettingFragment settingFragment;
    WorkFragment workFragment;


    private String currentFragmentName;

    MainActivity activity;


    public FragmentControl(MainActivity activity) {
        this.activity = activity;

    }

    public String getCurrentFragmentName() {
        return currentFragmentName;
    }


    public Fragment showFragment(String name){
        Logs.e("name = "+ name);
        currentFragmentName = name;
        Fragment fragment = null;
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(mainFragment != null) transaction.hide(mainFragment);
        if(learnFragment != null) transaction.hide(learnFragment);
        if(messageFragment != null) transaction.hide(messageFragment);
        if(personFragment != null) transaction.hide(personFragment);
        if(settingFragment != null) transaction.hide(settingFragment);
        if(workFragment != null) transaction.hide(workFragment);

        if(BaseFragment.HOME.equals(name)){
            if(mainFragment == null){
                fragment = mainFragment = new MainFragment();
                transaction.add(R.id.content_frame,mainFragment);
            }else {
                fragment = mainFragment;
                transaction.show(mainFragment);
            }
        }else if(BaseFragment.LEARNNING.equals(name)){
            if(learnFragment == null){
                fragment = learnFragment = new LearnFragment();
                transaction.add(R.id.content_frame, learnFragment);
            }else {
                fragment = learnFragment;
                transaction.show(learnFragment);
            }
        }else if(BaseFragment.MESSAGE.equals(name)){
            if(messageFragment == null){
                fragment = messageFragment = new MessageFragment();
                transaction.add(R.id.content_frame,messageFragment);
            }else {
                fragment = messageFragment;
                transaction.show(messageFragment);
            }
        }else if(BaseFragment.PERSON.equals(name)){
            if(personFragment == null){
                fragment = personFragment = new PersonFragment();
                transaction.add(R.id.content_frame,personFragment);
            }else {
                fragment = personFragment;
                transaction.show(personFragment);
            }
        }else if(BaseFragment.SETTING.equals(name)){
            if(settingFragment == null){
                fragment = settingFragment = new SettingFragment();
                transaction.add(R.id.content_frame,settingFragment);
            }else {
                fragment = settingFragment;
                transaction.show(settingFragment);
            }
        }else if(BaseFragment.WORK.equals(name)){
            if(workFragment == null){
                fragment = workFragment = new WorkFragment();
                transaction.add(R.id.content_frame,workFragment);
            }else {
                fragment = workFragment;
                transaction.show(workFragment);
            }
        }


        transaction.commitAllowingStateLoss();

        return fragment;
    }


}
