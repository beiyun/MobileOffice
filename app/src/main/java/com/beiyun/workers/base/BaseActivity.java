package com.beiyun.workers.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.util.DialogUtils;
import com.beiyun.library.util.Logs;
import com.beiyun.workers.entity.ViewCircular;
import com.beiyun.workers.interf.OnFragmentInteractionListener;
import com.sdsmdg.tastytoast.TastyToast;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by beiyun on 2018/3/28.
 * Workers
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements OnFragmentInteractionListener{


    private MaterialDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void useExploderAnim(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(500);
            explode.setMode(Visibility.MODE_IN);
            getWindow().setEnterTransition(explode);
            getWindow().setTransitionBackgroundFadeDuration(500);
            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.setDuration(500);
            getWindow().setSharedElementEnterTransition(changeBounds);
        }


    }

    public void useFadeAnim(){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(500);
            fade.excludeTarget(android.R.id.statusBarBackground,true);
            fade.excludeTarget(android.R.id.navigationBarBackground,true);
            fade.setMode(Visibility.MODE_IN);
            Fade fade1 = new Fade();
            fade1.addTarget(android.R.id.statusBarBackground);
            fade1.addTarget(android.R.id.navigationBarBackground);
            fade1.setMode(Visibility.MODE_OUT);
            TransitionSet set = new TransitionSet();
            set.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);
            set.addTransition(fade);
            set.addTransition(fade1);
            getWindow().setEnterTransition(fade);
            getWindow().setReturnTransition(set);
            getWindow().setTransitionBackgroundFadeDuration(500);
            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.setDuration(500);
            getWindow().setSharedElementEnterTransition(changeBounds);
        }

    }


    public void userSlideEndAnimWithoutActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.END);
            slide.excludeTarget(android.R.id.statusBarBackground,true);
            slide.excludeTarget(android.R.id.navigationBarBackground,true);
            Fade fade = new Fade();
            fade.addTarget(android.R.id.statusBarBackground);
            fade.addTarget(android.R.id.navigationBarBackground);
            fade.setMode(Visibility.MODE_OUT);
            TransitionSet set = new TransitionSet();
            set.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);
            set.addTransition(slide);
            set.addTransition(fade);
            getWindow().setEnterTransition(slide);
            getWindow().setReturnTransition(set);
            getWindow().setTransitionBackgroundFadeDuration(500);
            ChangeBounds changeBounds = new ChangeBounds();
            getWindow().setSharedElementEnterTransition(changeBounds);


        }




    }


    private void getViewXY(final View viewRoot, final OnMeasureViewListener listener) {

        viewRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
                int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;

                int dx = Math.max(cx, viewRoot.getWidth() - cx);
                int dy = Math.max(cy, viewRoot.getHeight() - cy);
                float finalRadius = (float) Math.hypot(dx, dy);
                Logs.e("cx = " + cx + " cy = " + cy + "  finalRadius = " + finalRadius);
                viewRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if(listener != null){
                    listener.onMeasure(new ViewCircular(cx,cy,finalRadius));
                }

            }
        });
    }

    private OnMeasureViewListener measureViewListener;

    public void setOnMeasureListener(OnMeasureViewListener listener){
        this.measureViewListener = listener;

    }



    public interface OnMeasureViewListener{
        void onMeasure(ViewCircular circular);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public void startActivity(Intent intent, View shareElement){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,shareElement,shareElement.getTransitionName());
            ActivityCompat.startActivity(this,intent,options.toBundle());
        }else{
            startActivity(intent);
        }
    }


    public void startActivity(Intent intent, View... shareElements){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Pair[] pairs = new Pair[shareElements.length];
            for (int i = 0; i < shareElements.length; i++) {
                pairs[i] = Pair.create(shareElements[i],shareElements[i].getTransitionName());
            }
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
            ActivityCompat.startActivity(this,intent,options.toBundle());
        }else{
            startActivity(intent);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            ActivityCompat.finishAfterTransition(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFabListener(boolean show) {

    }

    public void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void toastSuccess(String msg){
        TastyToast.makeText(this,msg,TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
    }

    public void toastError(String msg){
        TastyToast.makeText(this,msg,TastyToast.LENGTH_SHORT,TastyToast.ERROR).show();
    }

    public MaterialDialog showProgressDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog = new MaterialDialog.Builder(this)
                .content("please wait...")
                .progress(true, 0)
                .cancelable(false)
                .show();
        return progressDialog;
    }

    public void dismissProgressDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
