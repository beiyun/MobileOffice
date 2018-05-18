package com.beiyun.workers.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.TransitionManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beiyun.library.util.Apps;
import com.beiyun.library.util.Events;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Sizes;
import com.beiyun.library.util.Times;
import com.beiyun.library.util.Windows;
import com.beiyun.workers.R;
import com.beiyun.workers.adapter.SearchPersonAdapter;
import com.beiyun.workers.adapter.SearchPlantAdapter;
import com.beiyun.workers.adapter.SearchPublicAdapter;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.entity.ApplyAcceptEntity;
import com.beiyun.workers.entity.CheckQualificationBean;
import com.beiyun.workers.entity.CuredPactEntity;
import com.beiyun.workers.entity.FormalContractBean;
import com.beiyun.workers.entity.NoticeEntity;
import com.beiyun.workers.entity.PreContractBean;
import com.beiyun.workers.entity.SearchPlantEntity;
import com.beiyun.workers.entity.SearchPublicEntity;
import com.beiyun.workers.entity.SortAndCountEntity;
import com.beiyun.workers.entity.TGBasicInfoEntity;
import com.beiyun.workers.entity.TGLetterOfCommitmentBean;
import com.beiyun.workers.enums.Plant;
import com.beiyun.workers.interf.SearchType;
import com.beiyun.workers.okhttp.callback.BaseInfo;
import com.beiyun.workers.okhttp.callback.ResponseTCallBack;
import com.beiyun.workers.utils.AddressSelector;
import com.beiyun.workers.utils.AppRequests;
import com.beiyun.workers.utils.GsonUtil;
import com.beiyun.workers.utils.TestSimpleDataUtil;
import com.beiyun.workers.view.DatePikerDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import org.angmarch.views.NiceSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator
 */
public class SearchActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    @BindView(R.id.search_activity_list)
    RecyclerView searchList;
    @BindView(R.id.search_activity_searchFab)
    BoomMenuButton searchBtn;
    @BindView(R.id.search_activity_send)
    FloatingActionButton searchSend;
    @BindView(R.id.search_search_province)
    MaterialSpinner searchProvince;
    @BindView(R.id.search_search_city)
    MaterialSpinner searchCity;
    @BindView(R.id.search_search_county)
    MaterialSpinner searchCounty;
    @BindView(R.id.search_search_station)
    MaterialSpinner searchStation;
    @BindView(R.id.search_search_village)
    MaterialSpinner searchVillage;
    @BindView(R.id.search_search_village_group)
    MaterialSpinner searchVillageGroup;
    @BindView(R.id.search_activity_search_scroll_group)
    NestedScrollView searchScrollGroup;
    @BindView(R.id.search_activity_refreshLayout)
    SwipeRefreshLayout searchRefreshLayout;
    @BindView(R.id.search_activity_search_item_layout)
    FrameLayout searchItemLayout;
    @BindView(R.id.search_search_name)
    EditText searchName;
    @BindView(R.id.search_search_layout)
    LinearLayout searchLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_activity_headerLayout)
    LinearLayout searchHeaderLayout;
    @BindView(R.id.search_activity_searchLayout)
    LinearLayout searchActivitySearchLayout;
    @BindView(R.id.search_activity_appBar)
    AppBarLayout searchAppBar;
    @BindView(R.id.search_search_addressLayout_switch)
    SwitchCompat searchAddressLayoutSwitch;
    @BindView(R.id.search_search_addressLayout)
    LinearLayout searchAddressLayout;
    @BindView(R.id.search_search_year)
    MaterialSpinner searchYear;
    private float finalRadius;
    private int cx;
    private int cy;
    private View personView;
    private View plantView;
    private View publicView;
    private View infrastructureView;
    private View dataView;
    private NiceSpinner plantCategory;
    private NiceSpinner personCategory;
    private NiceSpinner infrastructureCategory;
    private TextView dataStartTime;
    private TextView dataEndTime;
    private NiceSpinner dataArea;
    private NiceSpinner dataText;
    private ActionBar supportActionBar;
    private int pagePosition;
    private SearchPersonAdapter searchPersonAdapter;
    private SearchPlantAdapter searchPlantAdapter;
    private SearchPublicAdapter searchPublicAdapter;
    private static final int SEARCH_PERSON = 0;
    private static final int SEARCH_PLANT = 1;
    private static final int SEARCH_WORK = 2;
    private static final int SEARCH_PUBLIC = 3;
    private static final int SEARCH_INFRASTRUCTURE = 4;
    private static final int SEARCH_DATA = 5;
    private boolean hideScrollGroup;
    private Plant plantType = Plant.PLANT_PROMISE;
    private int currentPage = 1;
    private List<String> years;
    private ArrayList<TGLetterOfCommitmentBean> commitmentBeans;
    private ArrayList<ApplyAcceptEntity> acceptEntities;
    private ArrayList<CheckQualificationBean> qualificationBeans;
    private ArrayList<PreContractBean> preContractBeans;
    private ArrayList<SortAndCountEntity> sortAndCountEntities;
    private ArrayList<FormalContractBean> formalContractBeans;
    private ArrayList<CuredPactEntity> curedPactEntities;
    private ArrayList<NoticeEntity> noticeEntities;
    private int totalSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        userSlideEndAnimWithoutActionBar();
        initActionBar();
        initBmbBtn();
        showSearchPage(0);
        initXY();
        initRefreshLayout();
        initRecyclerView();
        initSwitch();
        initSearchYear();
    }

    private void initSearchYear() {
        int year = Times.getYear();
        years = new ArrayList<>();
        years.add(String.valueOf(year));
        years.add(String.valueOf(year-1));
        years.add(String.valueOf(year-2));
        years.add(String.valueOf(year-3));
        searchYear.setItems(years);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }


    private void initSwitch() {
        searchAddressLayoutSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    TransitionManager.beginDelayedTransition(searchAddressLayout);
                    searchAddressLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                    AddressSelector.attachSpinners(searchAddressLayout, searchProvince, searchCity, searchCounty,
                            searchStation, searchVillage, searchVillageGroup);
                } else {
                    TransitionManager.beginDelayedTransition(searchAddressLayout);
                    searchAddressLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, 0));
                }
            }
        });
    }


    private void initRecyclerView() {
        searchList.setLayoutManager(new LinearLayoutManager(this));
        searchList.setHasFixedSize(true);

    }

    private void initActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Windows.setStatusBarColor(R.color.colorPrimaryDark);
        }
        setSupportActionBar(toolbar);
        supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle(getString(R.string.searchPersonInfo));
        }
    }

    private void initRefreshLayout() {
        searchRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        searchRefreshLayout.setOnRefreshListener(this);
    }

    private void initBmbBtn() {
        searchBtn.setButtonEnum(ButtonEnum.Ham);
        searchBtn.setPiecePlaceEnum(PiecePlaceEnum.HAM_6);
        searchBtn.setButtonPlaceEnum(ButtonPlaceEnum.HAM_6);
        searchBtn.setHamWidth(0);
        searchBtn.setHamHeight(0);
        for (int i = 0; i < searchBtn.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .pieceColor(Color.WHITE)
                    .imagePadding(new Rect(Sizes.dp2px(16), Sizes.dp2px(16), Sizes.dp2px(16), Sizes.dp2px(16)))
                    .normalColor(Apps.getColor(R.color.colorPrimaryDark))
                    .highlightedColor(Apps.getColor(R.color.colorAccent))
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {

                            if(index == 2 || index == 5){
                                toast("正在开发中...");
                                return;
                            }

                            String text = null;
                            resetUI();
                            showSearchPage(index);
                            if (!isScrollGroupVisible()) {
                                displayScrollGroup();
                            }

                            switch (index) {
                                case 0:
                                    text = getString(R.string.searchPersonInfo);
                                    break;
                                case 1:
                                    text = getString(R.string.searchPlantInfo);
                                    break;
                                case 2:
                                    text = getString(R.string.searchWorkInfo);
                                    break;
                                case 3:
                                    text = getString(R.string.searchPublicInfo);
                                    break;
                                case 4:
                                    text = getString(R.string.searchInfrastructureInfo);
                                    break;
                                case 5:
                                    text = getString(R.string.searchDataInfo);
                                    break;
                                default:
                            }

                            supportActionBar.setTitle(text);
                        }

                    });

            switch (i) {
                case 0:
                    builder.normalTextRes(R.string.person_msg)
                            .normalImageRes(R.drawable.ic_supervisor_account_white_24dp)
                            .subNormalTextRes(R.string.person_msg_detail);
                    break;
                case 1:
                    builder.normalTextRes(R.string.plant)
                            .normalImageRes(R.drawable.ic_assessment_white_24dp)
                            .subNormalTextRes(R.string.plant_detail);
                    break;
                case 2:
                    builder.normalTextRes(R.string.check_work)
                            .normalImageRes(R.drawable.ic_find_in_page_white_24dp)
                            .subNormalTextRes(R.string.check_work_detail);
                    break;
                case 3:
                    builder.normalTextRes(R.string.public_msg)
                            .normalImageRes(R.drawable.ic_content_paste_white_24dp)
                            .subNormalTextRes(R.string.public_msg_detail);
                    break;
                case 4:
                    builder.normalTextRes(R.string.base_build)
                            .normalImageRes(R.drawable.ic_business_white_24dp)
                            .subNormalTextRes(R.string.base_build_detail);
                    break;
                case 5:
                    builder.normalTextRes(R.string.data_analysis)
                            .normalImageRes(R.drawable.ic_timeline_white_24dp)
                            .subNormalTextRes(R.string.data_analysis_detail);
                    break;
                default:
            }

            searchBtn.addBuilder(builder);
        }
    }

    private void resetUI() {
        searchAddressLayoutSwitch.setChecked(false);
        searchProvince.setVisibility(View.GONE);
        searchCity.setVisibility(View.GONE);
        searchCounty.setVisibility(View.GONE);
        searchStation.setVisibility(View.GONE);
        searchVillage.setVisibility(View.GONE);
        searchVillageGroup.setVisibility(View.GONE);
        searchYear.setVisibility(View.GONE);

    }

    private void showSearchPage(int index) {

        searchLayout.setVisibility(View.VISIBLE);
        pagePosition = index;
        searchName.setText("");
        AddressSelector.clearAddressMap();
        searchAddressLayoutSwitch.setChecked(false);

        switch (index) {
            //人员信息
            case SEARCH_PERSON:
                initPersonView();
                break;
            //种植信息
            case SEARCH_PLANT:
                initPlantView();
                break;
            //工作考核
            case SEARCH_WORK:
                //todo
                initPlantView();
                break;
            //公示信息
            case SEARCH_PUBLIC:
                initPublicView();
                break;
            //基础设施
            case SEARCH_INFRASTRUCTURE:
                initInfrastructureView();
                break;
            //数据分析
            case SEARCH_DATA:
                initDataView();
                break;
            default:
        }
    }

    private void initDataView() {
        searchYear.setVisibility(View.VISIBLE);
        if (dataView == null) {
            dataView = Apps.getLayoutInflater().inflate(R.layout.layout_search_data, null);
            dataStartTime = dataView.findViewById(R.id.search_data_startTime);
            dataEndTime = dataView.findViewById(R.id.search_data_endtTime);
            dataArea = dataView.findViewById(R.id.search_data_area);
            dataText = dataView.findViewById(R.id.search_data_text);
            dataArea.setArrowDrawable(R.drawable.arrow);
            dataArea.setTintColor(R.color.colorPrimary);
            dataText.setArrowDrawable(R.drawable.arrow);
            dataText.setTintColor(R.color.colorPrimary);
            dataStartTime.setOnClickListener(this);
            dataEndTime.setOnClickListener(this);
            dataArea.attachDataSource(TestSimpleDataUtil.getPlantCategory());
            dataText.attachDataSource(TestSimpleDataUtil.getPlantCategory());
        }
        searchItemLayout.removeAllViews();
        searchItemLayout.addView(dataView);
        searchLayout.setVisibility(View.GONE);
    }

    private void initInfrastructureView() {
        if (infrastructureView == null) {
            infrastructureView = Apps.getLayoutInflater().inflate(R.layout.layout_search_infrastructure, null);
            infrastructureCategory = infrastructureView.findViewById(R.id.search_infrastructure_style);
            infrastructureCategory.setArrowDrawable(R.drawable.arrow);
            infrastructureCategory.setTintColor(R.color.colorPrimary);
            infrastructureCategory.attachDataSource(TestSimpleDataUtil.getPlantCategory());
        }
        searchItemLayout.removeAllViews();
        searchItemLayout.addView(infrastructureView);
        searchName.setVisibility(View.GONE);
    }

    private void initPublicView() {
        searchYear.setVisibility(View.VISIBLE);
        searchItemLayout.removeAllViews();
        searchName.setVisibility(View.GONE);
    }

    private void initPlantView() {
        searchYear.setVisibility(View.VISIBLE);
        if (plantView == null) {
            plantView = Apps.getLayoutInflater().inflate(R.layout.layout_search_plant, null);
            plantCategory = plantView.findViewById(R.id.search_plant_category);
            plantCategory.setArrowDrawable(R.drawable.arrow);
            plantCategory.setTintColor(R.color.colorPrimary);
            plantCategory.attachDataSource(TestSimpleDataUtil.getPlantCategory());
            plantCategory.addOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            plantType = Plant.PLANT_PROMISE;
                            break;
                        case 1:
                            plantType = Plant.PLANT_APPLY;
                            break;
                        case 2:
                            plantType = Plant.PLANT_QUALIFICATION;
                            break;
                        case 3:
                            plantType = Plant.PRE_CONTRACT;
                            break;
                        case 4:
                            plantType = Plant.PLANT_COUNT;
                            break;
                        case 5:
                            plantType = Plant.PLANT_CONTRACT;
                            break;
                        case 6:
                            plantType = Plant.CURE_RESERVATION;
                            break;
                        case 7:
                            plantType = Plant.BUY_RESERVATION;
                            break;
                        default:
                    }
                }
            });
        }
        searchItemLayout.removeAllViews();
        searchItemLayout.addView(plantView);
        searchName.setVisibility(View.VISIBLE);
    }

    private void initPersonView() {
        if (personView == null) {
            personView = Apps.getLayoutInflater().inflate(R.layout.layout_search_person, null);
            searchName.setVisibility(View.VISIBLE);
            personCategory = personView.findViewById(R.id.search_person_category);
            personCategory.setArrowDrawable(R.drawable.arrow);
            personCategory.setTintColor(R.color.colorPrimary);
            personCategory.attachDataSource(TestSimpleDataUtil.getPersons());

        }
        searchItemLayout.removeAllViews();
        searchItemLayout.addView(personView);

    }


    private boolean isScrollGroupVisible() {
        return searchScrollGroup.getVisibility() == View.VISIBLE;
    }


    private void initXY() {

        searchSend.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cx = (searchSend.getLeft() + searchSend.getRight()) / 2;
                cy = (searchSend.getTop() + searchSend.getBottom()) / 2;

                double dx = Math.max(cx, searchScrollGroup.getWidth() - cx);
                double dy = Math.max(cy, searchScrollGroup.getHeight() - cy);
                finalRadius = (float) Math.hypot(dx, dy);
                Logs.e("cx = " + cx + " cy = " + cy + "  finalRadius = " + finalRadius);
                searchSend.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });

    }


    private void displayScrollGroup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isScrollGroupVisible()) {
                hideScrollGroup = true;
                Animator animator = ViewAnimationUtils.createCircularReveal(searchScrollGroup, cx, cy, finalRadius, Sizes.dp2px(40));
                animator.setDuration(500);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.start();
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        searchScrollGroup.setVisibility(View.GONE);
                    }
                });


                TransitionManager.beginDelayedTransition(searchAppBar);
                searchHeaderLayout.setVisibility(View.VISIBLE);
                searchList.setVisibility(View.VISIBLE);

            } else {
                hideScrollGroup = false;
                searchScrollGroup.setVisibility(View.VISIBLE);
                Animator animator = ViewAnimationUtils.createCircularReveal(searchScrollGroup, cx, cy, Sizes.dp2px(40), finalRadius);
                animator.setDuration(500);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.start();
                TransitionManager.beginDelayedTransition(searchAppBar);
                searchHeaderLayout.setVisibility(View.GONE);
                searchList.setVisibility(View.INVISIBLE);
                if(searchRefreshLayout.isRefreshing()){
                    searchRefreshLayout.setRefreshing(false);
                }


            }
        } else {

            if (isScrollGroupVisible()) {
                hideScrollGroup = true;
                searchScrollGroup.setVisibility(View.GONE);
                searchHeaderLayout.setVisibility(View.VISIBLE);
                searchList.setVisibility(View.VISIBLE);
            } else {
                hideScrollGroup = false;
                searchScrollGroup.setVisibility(View.VISIBLE);
                searchHeaderLayout.setVisibility(View.GONE);
                searchList.setVisibility(View.INVISIBLE);
                if(searchRefreshLayout.isRefreshing()){
                    searchRefreshLayout.setRefreshing(false);
                }
            }


        }
    }

    @OnClick(R.id.search_activity_send)
    public void onViewClicked() {

        searchRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                Logs.e("send click >" + AddressSelector.getAddressMap());
                displayScrollGroup();
                if (hideScrollGroup) {
                    Logs.e("刷新页面 = " + hideScrollGroup);
                    searchRefreshLayout.setRefreshing(true);
                    onRefresh();
                }
            }
        });


    }

    @Override
    public void onRefresh() {
        this.currentPage = 1;
        searchRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                showListTitle();
                switch (pagePosition) {
                    case SEARCH_PERSON:
                        setPersonData();
                        break;
                    case SEARCH_PLANT:
                        setPlantData();
                        break;
                    case SEARCH_WORK:
                        //todo
//                        setPlantData();
                        break;
                    case SEARCH_PUBLIC:
                        setPublicData();
                        break;
                    case SEARCH_INFRASTRUCTURE:
                        //todo
                        setInfrastructureData();

                        break;
                    case SEARCH_DATA:
//                        setPlantData();
                        break;
                    default:
                }
            }

        });
    }


    //基础设施
    private void setInfrastructureData() {

        final int type = infrastructureCategory.getSelectedIndex()+1;
        String s = years.get(searchYear.getSelectedIndex());


    }


    private void showListTitle() {
        searchHeaderLayout.removeAllViews();
        switch (pagePosition) {
            case SEARCH_PERSON:
                searchHeaderLayout.addView(getHeaderTextView("单位名称"));
                searchHeaderLayout.addView(getHeaderTextView("姓名"));
                searchHeaderLayout.addView(getHeaderTextView("联系方式"));
                break;
            case SEARCH_PLANT:
                searchHeaderLayout.addView(getHeaderTextView("姓名"));
                searchHeaderLayout.addView(getHeaderTextView("联系方式"));
                break;
            case SEARCH_WORK:
                searchHeaderLayout.addView(getHeaderTextView("SEARCH_WORK1"));
                searchHeaderLayout.addView(getHeaderTextView("SEARCH_WORK3"));
                break;
            case SEARCH_PUBLIC:
                searchHeaderLayout.addView(getHeaderTextView("公示单位"));
                searchHeaderLayout.addView(getHeaderTextView("公示标题"));
                searchHeaderLayout.addView(getHeaderTextView("公示时间"));
                break;
            case SEARCH_INFRASTRUCTURE:
                searchHeaderLayout.addView(getHeaderTextView("INFRAST1"));
                searchHeaderLayout.addView(getHeaderTextView("INFRAST2"));
                searchHeaderLayout.addView(getHeaderTextView("INFRAST3"));
                break;
            case SEARCH_DATA:
                searchHeaderLayout.addView(getHeaderTextView("SEARCH_DATA1"));
                searchHeaderLayout.addView(getHeaderTextView("SEARCH_DATA2"));
                break;
            default:
        }

    }

    private TextView getHeaderTextView(String text) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, -1);
        params.weight = 1;
        params.gravity = Gravity.CENTER;
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextAppearance(this, R.style.list_header_text_style);
        textView.setText(text);
        return textView;
    }

    private void setPublicData() {
        AppRequests.getPlantInfo(plantCategory.getSelectedIndex()+1,Times.getYear(),currentPage, new ResponseTCallBack<BaseInfo<ArrayList<SearchPublicEntity>>>() {
            @Override
            public void onFailure(IOException e) {
                Logs.e("SearchActivity onFailure:" + ""+e.getMessage());
                if(searchRefreshLayout.isRefreshing()){
                    searchRefreshLayout.setRefreshing(false);
                }


            }

            @Override
            protected void onSuccess(BaseInfo<ArrayList<SearchPublicEntity>> data) {

                if(searchRefreshLayout.isRefreshing()){
                    searchRefreshLayout.setRefreshing(false);
                }


                if(data.getResultCode() != 100 ){
                    toastError(data.getReason());
                    return;
                }
                ArrayList<SearchPublicEntity> publicEntities = data.getData().getList();
                if(publicEntities == null || publicEntities.isEmpty()){
                    toastError("没有数据");
                    return;
                }


                Logs.e("SearchActivity onSuccess:" + data);



            }
        });

        if (searchPublicAdapter == null) {
            searchPublicAdapter = new SearchPublicAdapter(TestSimpleDataUtil.getSearchPublicData());
            searchPublicAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    searchRefreshLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (searchPublicAdapter.getItemCount() <= 100) {
                                searchPublicAdapter.addData(TestSimpleDataUtil.getSearchPublicData());
                                searchPublicAdapter.loadMoreComplete();
                            } else {
                                searchPublicAdapter.loadMoreEnd();
                            }
                        }
                    }, 500);
                }
            }, searchList);
            searchPublicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    toast("点击了：" + position);
                }
            });
        } else {
            searchPublicAdapter.setNewData(TestSimpleDataUtil.getSearchPublicData());
        }
        searchList.setAdapter(searchPublicAdapter);
    }

    private void setPlantData() {
        
        final int type = plantCategory.getSelectedIndex()+1;
        String s = years.get(searchYear.getSelectedIndex());

        AppRequests.getPlantInfo(type, Integer.valueOf(s),currentPage, new ResponseTCallBack<BaseInfo>() {
            @Override
            public void onFailure(IOException e) {
                Logs.e("getplantInfo onFailure >>"+e.getMessage());
                if(searchRefreshLayout.isRefreshing()){
                    searchRefreshLayout.setRefreshing(false);
                }

            }

            @Override
            protected void onSuccess(BaseInfo data) {
                if(searchRefreshLayout.isRefreshing()){
                    searchRefreshLayout.setRefreshing(false);
                }

                if(data == null || data.getResultCode() != 100){
                    assert data != null;
                    toastError(data.getReason());
                    return;
                }


                totalSize = data.getData().total;
                Logs.e("getplantInfo success >>" +data.getData().getList());

                List<SearchPlantEntity> searchPlantEntities = new ArrayList<>();

                switch (type){
                    case 1:
                        ArrayList<TGLetterOfCommitmentBean> commitmentBeans = GsonUtil.parseJson(data.getData().getList().toString(), new TypeToken<ArrayList<TGLetterOfCommitmentBean>>() {
                        });
                        if(commitmentBeans != null && commitmentBeans.size() != 0){
                            for (TGLetterOfCommitmentBean bean:
                                 commitmentBeans) {
                                SearchPlantEntity entity = setUpSearchPlantEntities(bean.getName(), bean.getFarmerTel());
                                searchPlantEntities.add(entity);
                            }
                        }else{
                            toastError("没有数据");
                        }

                        if(SearchActivity.this.commitmentBeans == null || currentPage == 1){
                            SearchActivity.this.commitmentBeans = commitmentBeans;
                        }else{
                            SearchActivity.this.commitmentBeans.addAll(commitmentBeans);
                        }

                        initPlantAdapter(searchPlantEntities);

                        break;
                    case 2:
                        ArrayList<ApplyAcceptEntity> acceptEntities = GsonUtil.parseJson(data.getData().getList().toString(), new TypeToken<ArrayList<ApplyAcceptEntity>>() {
                        });
                        if(acceptEntities != null && acceptEntities.size() != 0){
                            for (ApplyAcceptEntity bean:
                                    acceptEntities) {
                                SearchPlantEntity entity = setUpSearchPlantEntities(bean.getName(), bean.getFarmerTel());
                                searchPlantEntities.add(entity);
                            }
                        }else{
                            toastError("没有数据");
                        }

                        if(SearchActivity.this.acceptEntities == null || currentPage == 1){
                            SearchActivity.this.acceptEntities = acceptEntities;
                        }else{
                            SearchActivity.this.acceptEntities.addAll(acceptEntities);
                        }

                        initPlantAdapter(searchPlantEntities);

                        break;
                    case 3:
                        ArrayList<CheckQualificationBean> qualificationBeans = GsonUtil.parseJson(data.getData().getList().toString(), new TypeToken<ArrayList<CheckQualificationBean>>() {
                        });
                        if(qualificationBeans != null && qualificationBeans.size() != 0){
                            for (CheckQualificationBean bean:
                                    qualificationBeans) {
                                SearchPlantEntity entity = setUpSearchPlantEntities(bean.getName(), "未返回");
                                searchPlantEntities.add(entity);
                            }
                        }else{
                            toastError("没有数据");
                        }

                        if(SearchActivity.this.qualificationBeans == null || currentPage == 1){
                            SearchActivity.this.qualificationBeans = qualificationBeans;
                        }else{
                            SearchActivity.this.qualificationBeans.addAll(qualificationBeans);
                        }

                        initPlantAdapter(searchPlantEntities);

                        break;
                    case 4:
                        ArrayList<PreContractBean> preContractBeans = GsonUtil.parseJson(data.getData().getList().toString(), new TypeToken<ArrayList<PreContractBean>>() {
                        });
                        if(preContractBeans != null && preContractBeans.size() != 0){
                            for (PreContractBean bean:
                                    preContractBeans) {
                                SearchPlantEntity entity = setUpSearchPlantEntities(bean.getName(), bean.getFarmerTel());
                                searchPlantEntities.add(entity);
                            }
                        }else{
                            toastError("没有数据");
                        }

                        if(SearchActivity.this.preContractBeans == null || currentPage == 1){
                            SearchActivity.this.preContractBeans = preContractBeans;
                        }else{
                            SearchActivity.this.preContractBeans.addAll(preContractBeans);
                        }

                        initPlantAdapter(searchPlantEntities);

                        break;
                    case 5:
                        ArrayList<SortAndCountEntity>  sortAndCountEntities = GsonUtil.parseJson(data.getData().getList().toString(), new TypeToken<ArrayList<SortAndCountEntity>>() {
                        });
                        if(sortAndCountEntities != null && sortAndCountEntities.size() != 0){
                            for (SortAndCountEntity bean:
                                    sortAndCountEntities) {
                                SearchPlantEntity entity = setUpSearchPlantEntities(bean.getName(), bean.getFarmerTel());
                                searchPlantEntities.add(entity);
                            }
                        }else{
                            toastError("没有数据");
                        }

                        if(SearchActivity.this.sortAndCountEntities == null || currentPage == 1){
                            SearchActivity.this.sortAndCountEntities = sortAndCountEntities;
                        }else{
                            SearchActivity.this.sortAndCountEntities.addAll(sortAndCountEntities);
                        }

                        initPlantAdapter(searchPlantEntities);

                        break;
                    case 6:
                        ArrayList<FormalContractBean> formalContractBeans = GsonUtil.parseJson(data.getData().getList().toString(), new TypeToken<ArrayList<FormalContractBean>>() {
                        });
                        if(formalContractBeans != null && formalContractBeans.size() != 0){
                            for (FormalContractBean bean:
                                    formalContractBeans) {
                                SearchPlantEntity entity = setUpSearchPlantEntities(bean.getName(), "未返回");
                                searchPlantEntities.add(entity);
                            }
                        }else{
                            toastError("没有数据");
                        }

                        if(SearchActivity.this.formalContractBeans == null || currentPage == 1){
                            SearchActivity.this.formalContractBeans = formalContractBeans;
                        }else{
                            SearchActivity.this.formalContractBeans.addAll(formalContractBeans);
                        }

                        initPlantAdapter(searchPlantEntities);

                        break;
                    case 7:
                        ArrayList<CuredPactEntity> curedPactEntities = GsonUtil.parseJson(data.getData().getList().toString(), new TypeToken<ArrayList<CuredPactEntity>>() {
                        });
                        if(curedPactEntities != null && curedPactEntities.size() != 0){
                            for (CuredPactEntity bean:
                                    curedPactEntities) {
                                SearchPlantEntity entity = setUpSearchPlantEntities(bean.getName(), bean.getFarmerTel());
                                searchPlantEntities.add(entity);
                            }
                        }else{
                            toastError("没有数据");
                        }

                        if(SearchActivity.this.curedPactEntities == null || currentPage == 1){
                            SearchActivity.this.curedPactEntities = curedPactEntities;
                        }else{
                            SearchActivity.this.curedPactEntities.addAll(curedPactEntities);
                        }

                        initPlantAdapter(searchPlantEntities);
                        break;
                    case 8:
                        ArrayList<NoticeEntity> noticeEntities = GsonUtil.parseJson(data.getData().getList().toString(), new TypeToken<ArrayList<NoticeEntity>>() {
                        });
                        if(noticeEntities != null && noticeEntities.size() != 0){
                            for (NoticeEntity bean:
                                    noticeEntities) {
                                SearchPlantEntity entity = setUpSearchPlantEntities(bean.getName(), bean.getPhone());
                                searchPlantEntities.add(entity);
                            }
                        }else{
                            toastError("没有数据");
                        }

                        if(SearchActivity.this.noticeEntities == null || currentPage == 1){
                            SearchActivity.this.noticeEntities = noticeEntities;
                        }else{
                            SearchActivity.this.noticeEntities.addAll(noticeEntities);
                        }

                        initPlantAdapter(searchPlantEntities);
                        break;
                        default:

                }


            }
        });
        

    }

    private SearchPlantEntity setUpSearchPlantEntities(String name, String farmerTel) {
        SearchPlantEntity entity = new SearchPlantEntity();
        entity.setPlantName(name);
        entity.setPlantCall(farmerTel);
        return entity;
    }


    public void initPlantAdapter(final List<SearchPlantEntity> list){

        if(currentPage == 1){
            if (searchPlantAdapter == null) {
                searchPlantAdapter = new SearchPlantAdapter(list);
                searchList.setAdapter(searchPlantAdapter);
            } else {
                searchPlantAdapter.setNewData(list);
            }
            if(searchRefreshLayout.isRefreshing()){
                searchRefreshLayout.setRefreshing(false);
            }
        }else{
            searchPlantAdapter.addData(list);
            searchPlantAdapter.loadMoreComplete();
        }

        searchPlantAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toast("点击了：" + position);
               try{
                   switch (plantType) {
                       case PLANT_PROMISE:
                           Events.post(commitmentBeans.get(position));
                           goToSearchDetailActivity(SearchType.SEARCH_PLANT_PROMISE);
                           break;
                       case PLANT_APPLY:
                           Events.post(acceptEntities.get(position));
                           goToSearchDetailActivity(SearchType.SEARCH_PLANT_APPLY);
                           break;
                       case PLANT_QUALIFICATION:
                           Events.post(qualificationBeans.get(position));
                           goToSearchDetailActivity(SearchType.SEARCH_PLANT_QUALIFICATION);
                           break;
                       case PRE_CONTRACT:
                           Events.post(preContractBeans.get(position));
                           goToSearchDetailActivity(SearchType.SEARCH_PRE_CONTRACT);
                           break;
                       case PLANT_COUNT:
                           Events.post(sortAndCountEntities.get(position));
                           goToSearchDetailActivity(SearchType.SEARCH_PLANT_COUNT);
                           break;
                       case PLANT_CONTRACT:
                           Events.post(formalContractBeans.get(position));
                           goToSearchDetailActivity(SearchType.SEARCH_PLANT_CONTRACT);
                           break;
                       case CURE_RESERVATION:
                           Events.post(curedPactEntities.get(position));
                           goToSearchDetailActivity(SearchType.SEARCH_CURE_RESERVATION);
                           break;
                       case BUY_RESERVATION:
                           Events.post(noticeEntities.get(position));
                           goToSearchDetailActivity(SearchType.SEARCH_BUY_RESERVATION);
                           break;
                       default:
                   }
               }catch (Exception e){
                   Logs.e("onItemClick error >>" + e.getMessage());
               }

            }
        });

        searchPlantAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                searchRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        if (searchPlantAdapter.getItemCount() <= totalSize) {
                            currentPage ++;
                            setPlantData();
                        } else {
                            searchPlantAdapter.loadMoreEnd();
                        }
                    }
                });
            }
        }, searchList);


    }


    private void setPersonData() {

        final int type = personCategory.getSelectedIndex() + 1;


        AppRequests.getPersonInfo(currentPage, type, new AppRequests.CallBackListener<TGBasicInfoEntity>() {
            @Override
            public void success(final List<TGBasicInfoEntity> data, final int totalSize) {
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setCategory(type);
                }
                if (currentPage == 1) {
                    if (searchPersonAdapter == null) {
                        searchPersonAdapter = new SearchPersonAdapter(data);
                        searchList.setAdapter(searchPersonAdapter);
                    } else {
                        searchPersonAdapter.setNewData(data);
                    }
                    if (searchRefreshLayout.isRefreshing()) {
                        searchRefreshLayout.setRefreshing(false);
                    }
                } else {
                    searchPersonAdapter.addData(data);
                    searchPersonAdapter.loadMoreComplete();
                }

                searchPersonAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        searchRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                currentPage++;
                                if (searchPersonAdapter.getItemCount() < totalSize) {
                                    setPersonData();
                                } else {
                                    searchPersonAdapter.loadMoreEnd();
                                }
                            }
                        });
                    }
                }, searchList);

                searchPersonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        toast("点击了：" + position);
                        Events.post(data.get(position));
                        goToSearchDetailActivity(SearchType.SEARCH_PERSON);
                    }
                });
            }

            @Override
            public void failed(IOException e) {
                if (searchRefreshLayout.isRefreshing()) {
                    searchRefreshLayout.setRefreshing(false);
                }

            }
        });


    }


    private void goToSearchDetailActivity(int searchType) {
        Intent intent = new Intent(SearchActivity.this, SearchDetailActivity.class);
        intent.putExtra(SearchType.EXTRA_NAME, searchType);
        startActivity(intent, toolbar);
    }

    @Override
    public void onClick(final View v) {


        DatePikerDialog dialog = new DatePikerDialog(this);
        dialog.show();
        dialog.setOnDateSetListener(new DatePikerDialog.OnDateSetListener() {
            @Override
            public void dateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String date = year + "-" + month + "-" + dayOfMonth;
                switch (v.getId()) {
                    case R.id.search_data_startTime:
                        dataStartTime.setText(date);
                        break;
                    case R.id.search_data_endtTime:
                        dataEndTime.setText(date);
                        break;
                    default:
                }
            }
        });

    }
}
