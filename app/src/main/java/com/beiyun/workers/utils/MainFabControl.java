package com.beiyun.workers.utils;

import android.support.v7.widget.RecyclerView;
import android.view.ViewConfiguration;

import com.beiyun.library.util.Apps;
import com.beiyun.library.util.Logs;
import com.beiyun.workers.interf.OnFragmentInteractionListener;

/**
 * Created by beiyun on 2018/4/1.
 * Workers
 */
public class MainFabControl {

    private int distance;

    private boolean visible = true;

    private int state;

    public static final int SCROLL_UP = 0;
    public static final int SCROLL_DOWN = 1;


    public interface OnScrollListener{
        void onScrollState(int scrollState);
    }

    private OnScrollListener mListener;
    public void setOnScrollListener(OnScrollListener listener){
        this.mListener = listener;
    }


    public void controlFab(final RecyclerView rv, final OnFragmentInteractionListener listener){

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(distance < -ViewConfiguration.get(Apps.getContext()).getScaledTouchSlop() && !visible){
                    //显示fab
                    //iv_go_uploading.setVisibility(View.VISIBLE);
                    if(listener != null){
                        listener.onFabListener(true);
                    }
                    distance = 0;
                    visible = true;
                }else if(distance > ViewConfiguration.get(Apps.getContext()).getScaledTouchSlop() && visible){
                    //隐藏
                    //iv_go_uploading.setVisibility(View.GONE);

                    if(listener != null){
                        listener.onFabListener(false);
                    }
                    distance = 0;
                    visible = false;
                }
                if ((dy > 0 && visible) || (dy < 0 && !visible))//向下滑并且可见  或者  向上滑并且不可见
                    distance += dy;

                if(mListener != null){
                    mListener.onScrollState(dy>0? SCROLL_DOWN:SCROLL_UP);
                }
            }



            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                state = newState;
                Logs.e("scrollState"+state + "----visiable---"+ visible);
                if(visible && state == RecyclerView.SCROLL_STATE_IDLE){
                    rv.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(visible && state == RecyclerView.SCROLL_STATE_IDLE && listener != null){
                                listener.onFabListener(false);
                                visible = false;
                                distance = 0;
                            }

                        }
                    },2000);
                }



            }
        });

    }
}
