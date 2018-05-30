package com.beiyun.workers.ui;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.beiyun.library.util.Apps;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Sizes;
import com.beiyun.library.util.Sps;
import com.beiyun.library.util.Windows;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.base.BaseFragment;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.User;
import com.beiyun.workers.okhttp.OkHttpUtils;
import com.beiyun.workers.okhttp.callback.RequestCallBack;
import com.beiyun.workers.okhttp.helper.ResultData;
import com.beiyun.workers.utils.FragmentControl;
import com.beiyun.workers.utils.UpdateManager;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;

public class MainActivity extends BaseActivity implements ViewAnimator.ViewAnimatorListener {

    @BindView(R.id.main_search_fab)
    FloatingActionButton searchBtn;
    @BindView(R.id.content_overlay)
    LinearLayout contentOverlay;
    @BindView(R.id.content_frame)
    FrameLayout contentFrame;
    @BindView(R.id.share)
    BoomMenuButton share;
    @BindView(R.id.exitApp)
    ImageView exitApp;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.conteiner_frame)
    FrameLayout conteinerFrame;
    @BindView(R.id.left_drawer)
    LinearLayout linearLayout;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.list_fab)
    FloatingActionButton listFab;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private ActionBar actionBar;

    private FragmentControl fragmentControl;
    private RecyclerView attachRecyclerView;
    private int cy;
    private int cx;
    private int dx;
    private int dy;

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    public FloatingActionButton getSearchBtn() {
        return searchBtn;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Windows.setStatusBarColor(R.color.colorPrimaryDark);
        }
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        useFadeAnim();
        initLeftMenu();
        setActionBar();
        createMenuList();
        initFabBtn();
        initShareMenu();
        initExitMenu();
        /* initPermission();
         */
        UpdateManager.init().checkVersion(false);
        initXY();

    }



    private void initPermission() {
        try {
            List<PermissionItem> permissionItems = new ArrayList<>();
            permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", me.weyye.hipermission.R.drawable.permission_ic_camera));
            permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储卡", me.weyye.hipermission.R.drawable.permission_ic_storage));
            HiPermission.create(this)
                    .title("请求允许使用照相机和储存卡")
                    .msg("为保证程序正常运行，请开通以下权限")
                    .permissions(permissionItems)
                    .animStyle(me.weyye.hipermission.R.style.PermissionAnimScale)
                    .style(me.weyye.hipermission.R.style.PermissionDefaultBlueStyle)
                    .checkMutiPermission(new PermissionCallback() {
                        @Override
                        public void onClose() {
                            Logs.e("MainActivity onClose:" + "");
                        }

                        @Override
                        public void onFinish() {
                            Logs.e("MainActivity onFinish:" + "");

                        }

                        @Override
                        public void onDeny(String permission, int position) {
                            Logs.e("MainActivity onDeny:" + "");

                        }

                        @Override
                        public void onGuarantee(String permission, int position) {
                            Logs.e("MainActivity onGuarantee:" + "");

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initTargetView() {
        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(exitApp, "注销账户", "再次点击按钮将回到登录页面")
                        // All options below are optional
                        .outerCircleColor(R.color.colorPrimary)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .titleTextSize(18)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(12)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(R.color.white)  // Specify the color of the description text
                        .textColor(R.color.white)            // Specify a color for both the title and description text
                        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                        .dimColor(R.color.colorPrimaryDark)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(true)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                        .targetRadius(60),                  // Specify the target radius (in dp)
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                        signOut();
                    }
                });

    }

    /**
     * 登出
     */
    private void signOut() {
        final User user = (User) Sps.get(User.class);
        Sps.clear(User.class);
        HashMap<String,String> params = new HashMap<>();
        params.put("bo.userNumber",user.getUserNumber()== null?"":user.getUserNumber());
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();
        Logs.e("MainActivity signOut params = "+ params);
        OkHttpUtils.getQuery(AppUrl.get().SIGN_OUT, params, new RequestCallBack() {
            @Override
            public void success(ResultData data) {
                Logs.e("MainActivity signOut success "+data.getReason());

            }

            @Override
            public void onFailure(IOException e) {
                super.onFailure(e);
                Logs.e("MainActivity signOut failure >"+ e.getMessage());
            }
        });
    }

    private void initLeftMenu() {
        fragmentControl = new FragmentControl(this);
        BaseFragment fragment = (BaseFragment) fragmentControl.showFragment(BaseFragment.HOME);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        viewAnimator = new ViewAnimator<>(this, list, fragment, drawerLayout, this);
    }


    private void initShareMenu() {
        for (int i = 0; i < share.getButtonPlaceEnum().buttonNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .imagePadding(new Rect(Sizes.dp2px(16), Sizes.dp2px(16), Sizes.dp2px(16), Sizes.dp2px(16)))
                    .normalColor(Apps.getColor(R.color.colorPrimaryDark))
                    .highlightedColor(Apps.getColor(R.color.colorAccent))
                    .pieceColor(Color.WHITE)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index) {
                                case 0:
                                    toast("微信分享");
                                    break;
                                case 1:
                                    toast("QQ分享");
                                    break;
                                    default:
                            }
                        }
                    });

            switch (i) {
                case 0:
                    builder.normalImageRes(R.drawable.ic_supervisor_account_white_24dp)
                            .normalText("微信");
                    break;
                case 1:
                    builder.normalImageRes(R.drawable.ic_assessment_white_24dp)
                            .normalText("QQ");
                    break;
                    default:

            }

            share.addBuilder(builder);
        }
    }

    private void initExitMenu() {
        exitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTargetView();
            }
        });
    }

    private void initFabBtn() {
        listFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logs.e("initFabBtn click recyclerView = " + attachRecyclerView);
                if (attachRecyclerView != null) {
                    attachRecyclerView.smoothScrollToPosition(0);
                }
            }
        });
    }

    public void attachFab(RecyclerView rv) {
        this.attachRecyclerView = rv;
    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(BaseFragment.MESSAGE, R.drawable.ic_mail_white_24dp);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(BaseFragment.HOME, R.drawable.ic_home_white_24dp);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(BaseFragment.LEARNNING, R.drawable.ic_import_contacts_white_24dp);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(BaseFragment.PERSON, R.drawable.ic_person_white_24dp);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(BaseFragment.WORK, R.drawable.ic_note_white_24dp);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(BaseFragment.SETTING, R.drawable.ic_settings_white_24dp);
        list.add(menuItem5);


    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        new MaterialDialog.Builder(this)
                .content("确定要退出移动办公吗？")
                .positiveText("退出")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (JZVideoPlayer.backPress()) {
                            return;
                        }
                        dialog.dismiss();
                        Apps.exit();
                    }
                }).build().show();

    }

    private void setActionBar() {
        toolbar.setTitleTextColor(Apps.getColor(R.color.white));
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0) {
                    viewAnimator.showMenuContent();
                }
            }

            /** Called when a drawer has settled in a completely open state. */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_setting:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition, String name) {
        Logs.e("topPosition = " + topPosition);


        int finalRadius = Math.max(contentFrame.getWidth(), contentFrame.getHeight());
        Animator animator;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animator = ViewAnimationUtils.createCircularReveal(contentFrame, 0, topPosition, 0, finalRadius);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
            animator.start();
        }

        return (ScreenShotable) fragmentControl.showFragment(name);
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        return replaceFragment(screenShotable, position, slideMenuItem.getName());
    }

    @Override
    public void disableHomeButton() {
        actionBar.setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        actionBar.setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }


    /**
     * by moos on 2017.8.21
     * func:显示fab动画
     */
    public void showFABAnimation(final View view) {
        view.setVisibility(View.VISIBLE);
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(400).start();

    }

    /**
     * by moos on 2017.8.21
     * func:隐藏fab的动画
     */

    public void hideFABAnimation(final View view) {

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 0f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(400);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onFabListener(boolean show) {
        if (show) {
            showFABAnimation(listFab);
        } else {
            hideFABAnimation(listFab);
        }
    }

    /**
     * .makeSceneTransitionAnimation(this, searchBtn,getString(R.string.main_search_fab))
     */
    @OnClick(R.id.main_search_fab)
    public void onViewClicked() {
       startActivity(new Intent(this, SearchActivity.class),
                toolbar,searchBtn);

    }


    private void initXY() {

        searchBtn.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cx = (searchBtn.getLeft() + searchBtn.getRight()) / 2;
                cy = (searchBtn.getTop() + searchBtn.getBottom()) / 2;

                dx = Math.max(cx, drawerLayout.getWidth() - cx);
                dy = Math.max(cy, drawerLayout.getHeight() - cy);
                float finalRadius = (float) Math.hypot(dx, dy);
                Logs.e("cx = " + cx + " cy = " + cy + "  finalRadius = " + finalRadius);
                searchBtn.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });

    }

}
