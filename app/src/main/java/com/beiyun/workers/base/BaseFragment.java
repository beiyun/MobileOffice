package com.beiyun.workers.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.beiyun.workers.fragment.SettingFragment;
import com.beiyun.workers.ui.MainActivity;
import com.beiyun.workers.interf.OnFragmentInteractionListener;
import com.sdsmdg.tastytoast.TastyToast;

import yalantis.com.sidemenu.interfaces.ScreenShotable;


public class BaseFragment extends Fragment implements ScreenShotable{

    public static final String MESSAGE = "消息";
    public static final String HOME = "主页";
    public static final String LEARNNING = "培训";
    public static final String PERSON = "我的";
    public static final String WORK = "任务";
    public static final String SETTING = "设置";
    public static final String SHARE = "分享";
    public MainActivity mainActivity;


    public OnFragmentInteractionListener mListener;

    public BaseFragment() {
        // Required empty public constructor
    }

    Bitmap bitmap;
    View containerView;

    public void toast(String msg, int type){
        TastyToast.makeText(mainActivity,msg,TastyToast.LENGTH_SHORT,type).show();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        containerView = view;
        mainActivity = (MainActivity) getActivity();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void takeScreenShot() {
        containerView.post(new Runnable() {
            @Override
            public void run() {

                try {
                    Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                            containerView.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    containerView.draw(canvas);
                    BaseFragment.this.bitmap = bitmap;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }



}
