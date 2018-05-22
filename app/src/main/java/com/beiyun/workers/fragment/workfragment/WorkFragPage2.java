package com.beiyun.workers.fragment.workfragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Sizes;
import com.beiyun.workers.R;
import com.beiyun.workers.adapter.WorkFragPage2Adapter;
import com.beiyun.workers.base.BaseWorkPageFragment;
import com.beiyun.workers.entity.WorkFrag2Entity;
import com.beiyun.workers.entity.WorkFrag2ExpandReasonEntity;
import com.beiyun.workers.entity.WorkNameSearchEntity;
import com.beiyun.workers.okhttp.callback.BaseInfo;
import com.beiyun.workers.okhttp.callback.ResponseTCallBack;
import com.beiyun.workers.utils.AppRequests;
import com.beiyun.workers.utils.MainFabControl;
import com.beiyun.workers.view.DatePikerDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkFragPage2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkFragPage2 extends BaseWorkPageFragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private LinearLayout searchTimeLayout;
    private TextView searchTimeTitle;
    private AutoCompleteTextView searchTime;
    private LinearLayout searchWorkNameLayout;
    private TextView searchWorkNameTitle;
    private AutoCompleteTextView searchWorkName;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private WorkFragPage2Adapter mAdapter;
    private LinearLayout titleLayout;
    private LinearLayout titleLayoutCovert;
    private RelativeLayout contentView;
    private FloatingActionButton contentViewFab;
    private int cx;
    private int cy;
    private float finalRadius;
    private ArrayList<WorkNameSearchEntity> workNameEntities;
    private WorkNameSearchEntity selectedItemEntity;
    private String selectedDate;


    public WorkFragPage2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkFragPage2.
     */
    public static WorkFragPage2 newInstance(String param1, String param2) {
        WorkFragPage2 fragment = new WorkFragPage2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_frag_page2, container, false);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initXY();
        iniRefreshLayout();
        initRecyclerView();
        initSearchWorkName();
        initSearchTime();
    }

    private void iniRefreshLayout() {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        mainActivity.attachFab(recyclerView);
        MainFabControl mainFabControl = new MainFabControl();
        mainFabControl.controlFab(recyclerView,mListener);
    }


    public void initWorkAdapter(List<WorkFrag2Entity> data){
        mAdapter = new WorkFragPage2Adapter(getData(data));
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mAdapter.setNotDoAnimationCount(5);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                refreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        if(mAdapter.getItemCount() >= totalSize){
                            mAdapter.loadMoreEnd();
                        }else{
                           currentPage ++;
                           loadWorkData();
                        }
                    }
                });

            }
        },recyclerView);

        recyclerView.setAdapter(mAdapter);
        mAdapter.expandAll();
        recyclerView.smoothScrollToPosition(0);

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            mainActivity.attachFab(recyclerView);
        }
    }

    /**
     * 转化数据
     * @param entities
     * @return
     */
    private List<MultiItemEntity> getData(List<WorkFrag2Entity> entities) {

        List<MultiItemEntity> list = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            WorkFrag2Entity entity = entities.get(i);
            if(!entity.isComplete()){
                entity.addSubItem(new WorkFrag2ExpandReasonEntity(entity.getNoneCompleteReason()));
            }

            list.add(entity);
        }

        return list;
    }

    private void initSearchTime() {
        searchTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= 24) {
                    DatePickerDialog pickerDialog = new DatePickerDialog(getContext());
                    pickerDialog.show();
                    pickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            String date = year + "-" + (month +1)+ "-" + dayOfMonth;
                            setDate(date);
                        }
                    });
                }else{
                    DatePikerDialog d = new DatePikerDialog(getContext());
                    d.setOnDateSetListener(new DatePikerDialog.OnDateSetListener() {
                        @Override
                        public void dateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                            String date = year + "-" + month + "-" + dayOfMonth;
                            setDate(date);
                        }
                    });

                    d.show();

                }
            }
        });

    }

    private void setDate(final String date) {
        selectedDate = date;
        searchTime.setText(date);
        searchTimeTitle.setText("查询日期设定："+date);
        TransitionManager.beginDelayedTransition(searchTimeLayout);
        searchTimeTitle.setVisibility(View.VISIBLE);

        loadWorkNameData();

    }

    private void loadWorkNameData() {
        if(selectedDate == null){
            return;
        }

        // TODO: 2018/5/21 根据日期查询任务名称 initSearchWorkName
        AppRequests.workNameSearch(selectedDate, new ResponseTCallBack<BaseInfo<ArrayList<WorkNameSearchEntity>>>() {
            @Override
            protected void onSuccess(BaseInfo<ArrayList<WorkNameSearchEntity>> data) {
                if(data.getResultCode() == 100){
                    workNameEntities = data.getData().getList();
                    if(workNameEntities == null || workNameEntities.isEmpty()){
                        return;
                    }

                    List<String> workNames = new ArrayList<>();
                    for (WorkNameSearchEntity entity:workNameEntities) {
                        workNames.add(entity.getTitle());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.item_spinner,R.id.item_spinner_text,workNames);
                    searchWorkName.setAdapter(adapter);

                }else{
                    mainActivity.toastError(data.getReason());
                }
            }

            @Override
            public void onFailure(IOException e) {
                Logs.e("load workName failed >>"+e.getMessage());

            }
        });
    }

    private void initSearchWorkName() {

        searchWorkName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TransitionManager.beginDelayedTransition(searchWorkNameLayout);
                searchWorkNameTitle.setVisibility(View.VISIBLE);
                searchWorkNameTitle.setText(getString(R.string.work_name_set)+searchWorkName.getText().toString());
                if(workNameEntities != null){
                    selectedItemEntity = workNameEntities.get(position);
                }
                preToLoad();
            }
        });


        searchWorkName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(searchTime.getText().toString())){
                    mainActivity.toastError("请设定查询日期");
                    return;
                }

                if(workNameEntities == null || workNameEntities.isEmpty()){
                    mainActivity.toastError("没有找到任务名称数据");
                    return;
                }

                searchWorkName.showDropDown();
                Drawable drawableUp = getResources().getDrawable(R.drawable.ic_arrow_drop_up_24dp);
                if (drawableUp != null) {
                    drawableUp.setBounds(0,0,drawableUp.getMinimumWidth(), drawableUp.getMinimumHeight());
                    searchWorkName.setCompoundDrawables(null,null,drawableUp,null);
                }


            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            searchWorkName.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
                @Override
                public void onDismiss() {
                    Drawable drawableDown = getResources().getDrawable(R.drawable.ic_arrow_drop_down_24dp);
                    if (drawableDown != null) {
                        drawableDown.setBounds(0,0,drawableDown.getMinimumWidth(), drawableDown.getMinimumHeight());
                        searchWorkName.setCompoundDrawables(null,null,drawableDown,null);
                    }
                }
            });
        }

    }

    private void preToLoad() {
        // TODO: 2018/5/21 根据任务名称 查询任务完成情况
        final String time = searchTime.getText().toString();
        String workName = searchWorkName.getText().toString();
        if(!TextUtils.isEmpty(time) && !TextUtils.isEmpty(workName)){
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    startAnim(false);
                    refreshLayout.setRefreshing(true);
                    onRefresh();
                }
            });
        }
    }

    private void initViews(View view) {
        contentView = view.findViewById(R.id.contentView);
        recyclerView = view.findViewById(R.id.recyclerView);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        searchTimeLayout = view.findViewById(R.id.search_time_layout);
        searchTimeTitle = view.findViewById(R.id.search_time_title);
        searchTime = view.findViewById(R.id.search_time);
        searchWorkNameLayout = view.findViewById(R.id.search_work_name_layout);
        searchWorkNameTitle = view.findViewById(R.id.search_work_name_title);
        searchWorkName = view.findViewById(R.id.search_work_name);
        titleLayout = view.findViewById(R.id.list_title_layout);
        contentViewFab = view.findViewById(R.id.contentView_fab);
        contentViewFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnim(true);
            }
        });
     }

    private boolean isFirst = true;
    private int currentPage = 1;
    private int totalSize;
    @Override
    public void onRefresh() {

        currentPage = 1;
        loadWorkData();
    }

    private void loadWorkData() {
        if(selectedItemEntity == null){
            return;
        }
        AppRequests.workSearch(currentPage, selectedItemEntity, new ResponseTCallBack<BaseInfo<ArrayList<WorkFrag2Entity>>>() {
            @Override
            protected void onSuccess(BaseInfo<ArrayList<WorkFrag2Entity>> data) {

                if(refreshLayout.isRefreshing()){
                    refreshLayout.setRefreshing(false);
                }

                if(data.getResultCode() != 100){
                    mainActivity.toastError(data.getReason());
                    return;
                }



                ArrayList<WorkFrag2Entity> entities = data.getData().getList();
                if(entities == null || entities.isEmpty()){
                    mainActivity.toastError("数据未找到");
                    return;
                }

                totalSize = data.getData().total;

                if(currentPage == 1){
                    if(mAdapter == null){
                        initListTitleLayout();
                        initWorkAdapter(entities);
                    }else{
                        mAdapter.setNewData(getData(entities));
                        mAdapter.setNotDoAnimationCount(5);
                        recyclerView.smoothScrollToPosition(0);
                        mAdapter.expandAll();
                    }
                }else{
                    mAdapter.addData(getData(entities));
                    mAdapter.expandAll();
                    mAdapter.loadMoreComplete();
                }

            }

            @Override
            public void onFailure(IOException e) {

                if(refreshLayout.isRefreshing()){
                    refreshLayout.setRefreshing(false);
                }

                Logs.e(e.getMessage());

            }
        });

    }


    // get the center for the clipping circle
//            int cx = (myView.getLeft() + myView.getRight()) / 2;
//            int cy = (myView.getTop() + myView.getBottom()) / 2;
//
//            // get the final radius for the clipping circle
//            int dx = Math.max(cx, myView.getWidth() - cx);
//            int dy = Math.max(cy, myView.getHeight() - cy);
//            float finalRadius = (float) Math.hypot(dx, dy);
    private void startAnim(boolean visiable) {

        if(!visiable){
            contentViewFab.post(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Animator animator = ViewAnimationUtils.createCircularReveal(contentView, cx, cy, finalRadius, Sizes.dp2px(40));
                        animator.setDuration(600);
                        animator.start();
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                changeUI();
                            }
                        });
                    }else{
                        changeUI();
                    }
                }
            });


        }else{

            contentViewFab.post(new Runnable() {
                @Override
                public void run() {
                    searchTimeLayout.setVisibility(View.VISIBLE);
                    searchWorkNameLayout.setVisibility(View.VISIBLE);
                    contentView.setBackgroundResource(R.color.bottom_background_color);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Animator animator = ViewAnimationUtils.createCircularReveal(contentView, cx, cy, Sizes.dp2px(40), finalRadius);
                        animator.setDuration(600);
                        animator.start();
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                changeUI2();

                            }

                            @Override
                            public void onAnimationStart(Animator animation) {
                                super.onAnimationStart(animation);
                                Logs.e("onAnimationStart");
                            }
                        });

                    }else{
                        changeUI2();
                    }
                }
            });


        }

    }

    private void changeUI2() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f,0f,1f,0f,contentViewFab.getWidth()/2,contentViewFab.getHeight()/2);
        scaleAnimation.setStartOffset(300);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        contentViewFab.startAnimation(scaleAnimation);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                contentViewFab.setVisibility(View.GONE);
                contentViewFab.setClickable(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void changeUI() {
        contentViewFab.setVisibility(View.VISIBLE);
        searchTimeLayout.setVisibility(View.GONE);
        searchWorkNameLayout.setVisibility(View.GONE);
        contentView.setBackgroundResource(android.R.color.transparent);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0f,1f,0f,1f,contentViewFab.getWidth()/2,contentViewFab.getHeight()/2);
        scaleAnimation.setStartOffset(300);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimation.setDuration(500);
        contentViewFab.setClickable(true);
        contentViewFab.startAnimation(scaleAnimation);
    }

    private void initXY() {

        contentViewFab.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                cx = (contentViewFab.getLeft() + contentViewFab.getRight())/2;
                cy = (contentViewFab.getTop() + contentViewFab.getBottom())/2;

                int dx = Math.max(cx,contentView.getWidth()- cx);
                int dy = Math.max(cy,contentView.getHeight() - cy);
                finalRadius = (float) Math.hypot(dx,dy);
                contentViewFab.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    private void initListTitleLayout() {
        TransitionManager.beginDelayedTransition(titleLayout);
        ViewGroup.LayoutParams layoutParams = titleLayout.getLayoutParams();
        layoutParams.height = Sizes.dp2px(48);
        layoutParams.width = -1;
        titleLayout.setLayoutParams(layoutParams);
        titleLayout.setVisibility(View.VISIBLE);
    }
}
