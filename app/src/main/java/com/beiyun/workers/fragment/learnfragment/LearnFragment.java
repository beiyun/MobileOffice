package com.beiyun.workers.fragment.learnfragment;


import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.transition.TransitionManager;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beiyun.library.util.AppCaches;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Sizes;
import com.beiyun.library.util.Times;
import com.beiyun.library.util.Windows;
import com.beiyun.workers.R;
import com.beiyun.workers.adapter.LearnFragViewPagerAdapter;
import com.beiyun.workers.base.BaseFragment;
import com.beiyun.workers.entity.VideoEntity;
import com.beiyun.workers.view.VideoListDialog;
import com.dd.processbutton.FlatButton;
import com.facebook.common.util.UriUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import me.weyye.hipermission.HiPermission;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * 培训页面
 */
public class LearnFragment extends BaseFragment {


    @BindView(R.id.video_viewPager)
    ViewPager viewPager;
    @BindView(R.id.view_title)
    MaterialEditText viewTitle;
    @BindView(R.id.video_subtitle)
    MaterialEditText videoSubtitle;
    @BindView(R.id.video_content)
    MaterialEditText videoContent;
    @BindView(R.id.video_add_from_local)
    FlatButton videoAddFromLocal;
    @BindView(R.id.video_create_new_video)
    FlatButton videoCreateNewVideo;
    @BindView(R.id.video_preView)
    JZVideoPlayerStandard videoPreView;
    @BindView(R.id.video_preView_layout)
    LinearLayout videoPreViewLayout;
    @BindView(R.id.video_upload)
    FloatingActionButton videoUpload;
    @BindView(R.id.video_show_fab)
    FloatingActionButton videoShowFab;
    @BindView(R.id.video_add_layout)
    NestedScrollView video_add_layout;
    Unbinder unbinder;
    TabLayout tabLayout;
    AppBarLayout appBarLayout;
    @BindView(R.id.fragment_view_title)
    TextView fragmentViewTitle;
    @BindView(R.id.fragment_view_size)
    TextView fragmentViewSize;
    @BindView(R.id.fragment_view_duration)
    TextView fragmentViewDuration;
    @BindView(R.id.video_fab_layout)
    LinearLayout videoFabLayout;
    private int cx;
    private int cy;
    private float finalRadius;
    private String imageFileName;
    private String videoPath;
    private boolean showAddLayout = false;
    private int pagePosition;

    public LearnFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager();
        changeSize(true);
        initTabLayout();
        initXY();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!showAddLayout) {
            Logs.e("LearnFragment onResume:" + "dismiss videoUpload");
            videoUpload.setVisibility(View.GONE);
        }
    }

    private void initTabLayout() {
        tabLayout = mainActivity.getTabLayout();
        if (tabLayout.getTabCount() == 0) {
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
        }
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("精品课堂");
        tabLayout.getTabAt(1).setText("我的作品");

    }

    private void initViewPager() {
        appBarLayout = mainActivity.findViewById(R.id.app_bar);
        LearnFragViewPagerAdapter pagerAdapter = new LearnFragViewPagerAdapter(mainActivity.getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Logs.e("onPageSelected  position = " + position);
                if (position == 1) {

                    pagePosition = position;
                    videoShowFab.setVisibility(View.VISIBLE);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, videoShowFab.getWidth() / 2, videoShowFab.getHeight() / 2);
                    scaleAnimation.setStartOffset(300);
                    scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                    scaleAnimation.setDuration(500);
                    videoShowFab.startAnimation(scaleAnimation);
                    mainActivity.getSearchBtn().setVisibility(View.GONE);
                } else {
                    videoShowFab.setVisibility(View.GONE);
                    mainActivity.getSearchBtn().setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void changeSize(final boolean bigger) {

        appBarLayout.post(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(appBarLayout);
                ViewGroup.LayoutParams params = appBarLayout.getLayoutParams();
                if (bigger) {
                    params.width = WindowManager.LayoutParams.MATCH_PARENT;
                    params.height = Windows.getActionBarHeight() * 2;
                    tabLayout.setVisibility(View.VISIBLE);
                } else {
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
        if (hidden) {
            changeSize(false);
            if (pagePosition == 1) {
                mainActivity.getSearchBtn().setVisibility(View.VISIBLE);
            }

        } else {
            if (!showAddLayout) {
                changeSize(true);
            }
            initTabLayout();
            if (pagePosition == 1) {
                mainActivity.getSearchBtn().setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.video_add_from_local)
    public void onVideoAddFromLocalClicked() {

        if (!HiPermission.checkPermission(mainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            toast("存储卡不被允许使用", TastyToast.ERROR);
            return;
        }

        VideoListDialog dialog = new VideoListDialog(mainActivity);
        dialog.setOnItemClickListener(new VideoListDialog.OnItemClickListener() {
            @Override
            public void onItemClick(VideoEntity entity) {
                Logs.e("LearnFragment onItemClick:" + "" + entity);
                videoPreViewLayout.setVisibility(View.VISIBLE);
                videoPreView.setUp(entity.getPath(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
                videoPreView.thumbImageView.setImageURI(UriUtil.getUriForFile(new File(entity.getThumbImage())));
                fragmentViewTitle.setText(entity.getTitle());
                fragmentViewDuration.setText(JZUtils.stringForTime(entity.getDuration()));
                fragmentViewSize.setText(AppCaches.getFormatSize(entity.getSize()));
                if (TextUtils.isEmpty(viewTitle.getText().toString())) {
                    viewTitle.setText(entity.getTitle());
                }

            }
        });
        dialog.show();


    }

    @OnClick(R.id.video_create_new_video)
    public void onVideoCreateNewVideoClicked() {
        if (HiPermission.checkPermission(mainActivity, Manifest.permission.CAMERA)
                && HiPermission.checkPermission(mainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            File mediaFile = createMediaFile();
            if (mediaFile != null) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                // create a file to save the video
                Uri fileUri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String authority = getActivity().getPackageName() + ".provider";
                    fileUri = FileProvider.getUriForFile(mainActivity, authority, mediaFile);
                } else {
                    fileUri = Uri.fromFile(mediaFile);
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high
                // start the Video Capture Intent
                startActivityForResult(intent, 1);
            }
        } else {
            toast("相机或存储卡不被允许使用", TastyToast.ERROR);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Video captured and saved to fileUri specified in the Intent

                setPreViewAfterVideoRecord();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the video capture
                toast("录像取消", TastyToast.ERROR);
            } else {
                // Video capture failed, advise user
                toast(" Video capture failed", TastyToast.ERROR);
            }
        }
    }

    //录像之后显示预览视频
    private void setPreViewAfterVideoRecord() {
        try {
            videoPreViewLayout.setVisibility(View.VISIBLE);
            videoPreView.setUp(videoPath, JZVideoPlayer.SCREEN_WINDOW_NORMAL);
            videoPreView.thumbImageView.setImageBitmap(getVideoThumbnail(videoPath));
            fragmentViewTitle.setText(imageFileName);
            if (TextUtils.isEmpty(viewTitle.getText().toString())) {
                viewTitle.setText(imageFileName);
            }

            File file = new File(videoPath);
            fragmentViewDuration.setText(JZUtils.stringForTime(getDuration(file)));
            fragmentViewSize.setText(AppCaches.getFormatSize(file.length()));


        } catch (Exception e) {
            Logs.e("LearnFragment Exception:" + "" + e);
            e.printStackTrace();
        }
    }

    //获取视频文件时长
    private int getDuration(File file) {
        try {
            return MediaPlayer.create(getActivity(), Uri.fromFile(file)).getDuration();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //获取视频文件缩略图
    public Bitmap getVideoThumbnail(String videoPath) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(videoPath);
        return media.getFrameAtTime();
    }

    private File createMediaFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "workers");
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Logs.e("failed to create directory");
                    return null;
                }
            }

            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date(Times.currentTimeMillis()));
            imageFileName = "mo_" + timeStamp;
            String suffix = ".mp4";
            videoPath = mediaStorageDir + File.separator + imageFileName + suffix;
            return new File(videoPath);
        }
        return null;
    }

    @OnClick(R.id.video_upload)
    public void onVideoUploadClicked() {
        toast("视频上传", TastyToast.DEFAULT);
    }


    @OnClick(R.id.video_show_fab)
    public void onVideoShowFabClicked() {
        viewTitle.setFocusableInTouchMode(false);
        videoSubtitle.setFocusableInTouchMode(false);
        videoContent.setFocusableInTouchMode(false);

        videoShowFab.post(new Runnable() {
            @Override
            public void run() {
                if (!showAddLayout) {
                    changeSize(false);
                    video_add_layout.setVisibility(View.VISIBLE);
                    viewTitle.setFocusableInTouchMode(true);
                    videoSubtitle.setFocusableInTouchMode(true);
                    videoContent.setFocusableInTouchMode(true);
                    Animator animator = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        animator = ViewAnimationUtils.createCircularReveal(video_add_layout, cx, cy, Sizes.dp2px(40), finalRadius);
                        animator.setDuration(600).setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.start();
                    }

                    RotateAnimation rotateAnimation = new RotateAnimation(0, 45, videoShowFab.getWidth() / 2, videoShowFab.getHeight() / 2);
                    rotateAnimation.setDuration(500);
                    rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                    rotateAnimation.setFillAfter(true);
                    videoShowFab.setAnimation(rotateAnimation);
                    videoUpload.setVisibility(View.VISIBLE);
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
                    alphaAnimation.setDuration(500);
                    alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                    alphaAnimation.setFillAfter(true);
                    videoUpload.startAnimation(alphaAnimation);
                } else {
                    viewTitle.setFocusableInTouchMode(false);
                    videoSubtitle.setFocusableInTouchMode(false);
                    videoContent.setFocusableInTouchMode(false);
                    changeSize(true);
                    Animator animator = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        animator = ViewAnimationUtils.createCircularReveal(video_add_layout, cx, cy, finalRadius, Sizes.dp2px(40));
                        animator.setDuration(600).setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.start();
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                video_add_layout.setVisibility(View.GONE);
                            }
                        });
                    }

                    RotateAnimation rotateAnimation = new RotateAnimation(45, 0, videoShowFab.getWidth() / 2, videoShowFab.getHeight() / 2);
                    rotateAnimation.setDuration(500);
                    rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                    videoShowFab.setAnimation(rotateAnimation);
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
                    alphaAnimation.setDuration(500);
                    alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                    videoUpload.startAnimation(alphaAnimation);
                    alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            videoUpload.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    if (videoPreView.isCurrentPlay()) {
                        videoPreView.release();
                    }
                }

                showAddLayout = !showAddLayout;
            }
        });


    }


    private void initXY() {

        videoShowFab.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cx = (videoFabLayout.getLeft() + videoFabLayout.getRight()) / 2;
                cy = (videoFabLayout.getTop() + videoFabLayout.getBottom()) / 2 + Sizes.dp2px(32);

                int dx = Math.max(cx, video_add_layout.getWidth() - cx);
                int dy = Math.max(cy, video_add_layout.getHeight() - cy);
                finalRadius = (float) Math.hypot(dx, dy);
                Logs.e("cx = " + cx + " cy = " + cy + "  finalRadius = " + finalRadius);
                videoShowFab.setVisibility(View.GONE);
                video_add_layout.setVisibility(View.GONE);
                videoShowFab.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });

    }
}
