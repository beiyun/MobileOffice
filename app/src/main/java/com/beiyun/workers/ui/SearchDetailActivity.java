package com.beiyun.workers.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.beiyun.library.util.Logs;
import com.beiyun.library.anot.Receiver;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.util.Events;
import com.beiyun.library.util.Windows;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseActivity;
import com.beiyun.workers.entity.ApplyAcceptEntity;
import com.beiyun.workers.entity.BaseStationEntity;
import com.beiyun.workers.entity.CheckQualificationBean;
import com.beiyun.workers.entity.CuredPactEntity;
import com.beiyun.workers.entity.FormalContractBean;
import com.beiyun.workers.entity.NoticeEntity;
import com.beiyun.workers.entity.PreContractBean;
import com.beiyun.workers.entity.SearchPublicEntity;
import com.beiyun.workers.entity.SortAndCountEntity;
import com.beiyun.workers.entity.TGBasicInfoEntity;
import com.beiyun.workers.entity.TGLetterOfCommitmentBean;
import com.beiyun.workers.fragment.PersonFragment;
import com.beiyun.workers.fragment.searchfragment.ApplyPlantInfoFragment;
import com.beiyun.workers.fragment.searchfragment.BasicStationFragment;
import com.beiyun.workers.fragment.searchfragment.BuyNoticeFragment;
import com.beiyun.workers.fragment.searchfragment.CurePactFragment;
import com.beiyun.workers.fragment.searchfragment.PersonInfoFragment;
import com.beiyun.workers.fragment.searchfragment.PlantContractInfoFragment;
import com.beiyun.workers.fragment.searchfragment.PlantCountInfoFragment;
import com.beiyun.workers.fragment.searchfragment.PlantPromiseFragment;
import com.beiyun.workers.fragment.searchfragment.PlantQualificationInfoFragment;
import com.beiyun.workers.fragment.searchfragment.PreContractInfoFragment;
import com.beiyun.workers.fragment.searchfragment.PublicFragment;
import com.beiyun.workers.interf.SearchType;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator
 */
@Receiver
public class SearchDetailActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.search_detail_frameContent)
    FrameLayout searchFrameContent;

    private TGBasicInfoEntity personEntity;
    private ApplyAcceptEntity plantApplyEntity;
    private FormalContractBean plantContractBean;
    private PreContractBean plantPreContractEntity;
    private TGLetterOfCommitmentBean plantPromiseEntity;
    private SortAndCountEntity plantCountEntity;
    private CheckQualificationBean plantQualificationEntity;
    private CuredPactEntity curedPactEntity;
    private NoticeEntity noticeEntity;
    private ActionBar actionBar;
    private BaseStationEntity baseStationEntity;
    private SearchPublicEntity searchPublicEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        ButterKnife.bind(this);
        userSlideEndAnimWithoutActionBar();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Windows.setStatusBarColor(R.color.colorPrimaryDark);
        }
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        int searchType = getIntent().getIntExtra(SearchType.EXTRA_NAME, -1);

        initFragment(searchType);
    }
    
    
    @Subscribe
    public void onReceive(TGBasicInfoEntity entity){
        this.personEntity = entity;
        Logs.e(personEntity.toString());
    }


    @Subscribe
    public void onReceive(FormalContractBean entity){
        plantContractBean = entity;
        Logs.e(plantContractBean.toString());
    }


    @Subscribe
    public void onReceive(PreContractBean entity){
        plantPreContractEntity = entity;
        Logs.e(entity.toString());
    }

    @Subscribe
    public void onReceive(TGLetterOfCommitmentBean entity){
        plantPromiseEntity = entity;
        Logs.e(entity.toString());
    }


    @Subscribe
    public void onReceive(SortAndCountEntity entity){
        plantCountEntity = entity;
        Logs.e(entity.toString());
    }


    @Subscribe
    public void onReceive(CheckQualificationBean entity){
        plantQualificationEntity = entity;
        Logs.e(entity.toString());
    }

    @Subscribe
    public void onReceive(ApplyAcceptEntity entity){
        plantApplyEntity = entity;
        Logs.e(entity.toString());
    }


    @Subscribe
    public void onReceive(CuredPactEntity entity){
        curedPactEntity = entity;
        Logs.e(entity.toString());
    }


    @Subscribe
    public void onReceive(NoticeEntity entity){
        noticeEntity = entity;
        Logs.e(entity.toString());
    }

    @Subscribe
    public void onReceive(BaseStationEntity entity){
        baseStationEntity = entity;
        Logs.e(entity.toString());
    }

    @Subscribe
    public void onReceive(SearchPublicEntity entity){
        searchPublicEntity = entity;
        Logs.e(entity.toString());
    }





    public Toolbar getToolbar() {
        return toolbar;
    }

    public AppBarLayout getAppBar() {
        return appBar;
    }

    public FrameLayout getSearchFrameContent() {
        return searchFrameContent;
    }

    public TGBasicInfoEntity getPersonEntity() {
        return personEntity;
    }

    public ApplyAcceptEntity getPlantApplyEntity() {
        return plantApplyEntity;
    }

    public FormalContractBean getPlantContractBean() {
        return plantContractBean;
    }

    public PreContractBean getPlantPreContractEntity() {
        return plantPreContractEntity;
    }

    public TGLetterOfCommitmentBean getPlantPromiseEntity() {
        return plantPromiseEntity;
    }

    public SortAndCountEntity getPlantCountEntity() {
        return plantCountEntity;
    }

    public CheckQualificationBean getPlantQualificationEntity() {
        return plantQualificationEntity;
    }


    private void initFragment(int searchType) {


        switch (searchType){
            case SearchType.SEARCH_PERSON:
                actionBar.setTitle("个人信息");
                Events.post(personEntity);
                showFragment(new PersonInfoFragment());
                break;
            case SearchType.SEARCH_PLANT_PROMISE:
                actionBar.setTitle("种植承诺信息");
                Events.post(plantPromiseEntity);
                showFragment(new PlantPromiseFragment());
                break;
            case SearchType.SEARCH_PLANT_APPLY:
                Events.post(plantApplyEntity);
                showFragment(new ApplyPlantInfoFragment());
                actionBar.setTitle("种植申请信息");
                break;
            case SearchType.SEARCH_PLANT_QUALIFICATION:
                Events.post(plantQualificationEntity);
                showFragment(new PlantQualificationInfoFragment());
                actionBar.setTitle("资格审核信息");
                break;
            case SearchType.SEARCH_PRE_CONTRACT:
                Events.post(plantPreContractEntity);
                showFragment(new PreContractInfoFragment());
                actionBar.setTitle("预签合同信息");
                break;
            case SearchType.SEARCH_PLANT_COUNT:
                Events.post(plantCountEntity);
                actionBar.setTitle("清塘点株信息");
                showFragment(new PlantCountInfoFragment());
                break;
            case SearchType.SEARCH_PLANT_CONTRACT:
                Events.post(plantContractBean);
                actionBar.setTitle("种植合同信息");
                showFragment(new PlantContractInfoFragment());
                break;
            case SearchType.SEARCH_CURE_RESERVATION:
                Events.post(curedPactEntity);
                actionBar.setTitle("烤烟协议");
                showFragment(new CurePactFragment());
                break;
            case SearchType.SEARCH_BUY_RESERVATION:
                Events.post(noticeEntity);
                actionBar.setTitle("预约通知单");
                showFragment(new BuyNoticeFragment());
                break;
            case SearchType.SEARCH_WORK:
                break;
            case SearchType.SEARCH_PUBLIC:
                Events.post(searchPublicEntity);
                actionBar.setTitle("公示信息");
                showFragment(new PublicFragment());
                break;
            case SearchType.SEARCH_INFRASTRUCTURE:
                Events.post(baseStationEntity);
                actionBar.setTitle("基础设施");
                showFragment(new BasicStationFragment());
                break;
            case SearchType.SEARCH_DATA:
                break;
                default:
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.search_detail_frameContent,fragment);
        transaction.commitAllowingStateLoss();
    }


}
