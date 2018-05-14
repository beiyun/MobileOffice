package com.beiyun.workers.fragment.workfragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Sizes;
import com.beiyun.workers.R;
import com.beiyun.workers.base.BaseWorkPageFragment;
import com.beiyun.workers.view.DatePikerDialog;
import com.dd.processbutton.FlatButton;
import com.sdsmdg.tastytoast.TastyToast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link WorkFragPage1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkFragPage1 extends BaseWorkPageFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AutoCompleteTextView spinner;
    private AutoCompleteTextView completeTime;
    private TextView completeTimeTile;
    private LinearLayout completeLayout;
    private LinearLayout workLayout;
    private TextView workTitle;
    private AutoCompleteTextView work;
    private FlatButton button;
    private String[] spinnerItems;
    private AutoCompleteTextView workName;
    private LinearLayout workNameLayout;
    private TextView workNameTitle;
    private NestedScrollView scrollView_root;
    private LinearLayout inputLayout;
    private TextView spinnerTile;


    public WorkFragPage1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkFragPage1.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkFragPage1 newInstance(String param1, String param2) {
        WorkFragPage1 fragment = new WorkFragPage1();
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
        return inflater.inflate(R.layout.fragment_work_frag_page1, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         initSpinner(view);
         initCompleteTime(view);
         initWorkName(view);
         initWorkContent(view);
        initSubmitButton(view);

    }

    private void initWorkName(View view) {
        workNameLayout = (LinearLayout) view.findViewById(R.id.workNameLayout);
        workNameTitle = (TextView) view.findViewById(R.id.workNameTitle);
        workName = (AutoCompleteTextView) view.findViewById(R.id.workName);
        workName.setFocusableInTouchMode(false);
        workName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeWorkNameState(true);
            }
        });
        workName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
               changeWorkNameState(hasFocus);
            }
        });

        workName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(workNameTitle.getVisibility() == View.VISIBLE){
                    workNameTitle.setText("任务名称设定："+s);
                }

            }
        });

    }

    private void changeWorkNameState(boolean hasFocus) {
        if(hasFocus){
            TransitionManager.beginDelayedTransition(workNameLayout);
            workNameTitle.setVisibility(View.VISIBLE);
            workNameTitle.setText("任务名称设定："+ workName.getText());
            workName.setFocusableInTouchMode(true);
            scrollView_root.smoothScrollTo(0,workName.getScrollY());

        }else if(TextUtils.isEmpty(workName.getText().toString())){
            TransitionManager.beginDelayedTransition(workNameLayout);
            workNameTitle.setVisibility(View.GONE);
            workName.clearFocus();
            workName.setFocusableInTouchMode(false);
        }else{
            workName.clearFocus();
            workName.setFocusableInTouchMode(false);
        }
    }

    private void initSubmitButton(View view) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog p = new ProgressDialog(getActivity());
                p.setMessage("任务下达中...");
                p.setCancelable(false);
                p.show();
                button.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TastyToast.makeText(getContext(),"任务下达成功",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS).show();
                        spinner.setText(null);
                        workName.setText(null);
                        work.setText(null);
                        changeWorkNameState(false);
                        changeWorkState(false);
                        changeSpinnerState(false);
                        setDate(null,false);
                        p.dismiss();
                    }
                },2000);
            }
        });


    }

    private void initWorkContent(View view) {
        scrollView_root = (NestedScrollView) view.findViewById(R.id.scrollView_root);
        workLayout = (LinearLayout) view.findViewById(R.id.work_layout);
        workTitle = (TextView) view.findViewById(R.id.work_title);
        work = (AutoCompleteTextView) view.findViewById(R.id.work);
        button = (FlatButton) view.findViewById(R.id.submit);
        work.setFocusableInTouchMode(false);
        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeWorkState(true);
            }
        });

        work.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                changeWorkState(hasFocus);
                Logs.e("onFocusChange  "+hasFocus);
            }
        });

        work.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Logs.e("beforeTextChanged "+s);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Logs.e("onTextChanged "+s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Logs.e("afterTextChanged "+s);
                if(workTitle.getVisibility() == View.VISIBLE){
                    workTitle.setText("任务内容设定："+s);
                }
            }
        });

    }


    private boolean hasExpandle;
    public void changeWorkState(boolean hasFocus){
        this.hasExpandle = hasFocus;
        if(hasFocus){
            TransitionManager.beginDelayedTransition(workLayout);
            work.setHeight(Sizes.dp2px(200));
            workTitle.setVisibility(View.VISIBLE);
            work.setGravity(Gravity.TOP|Gravity.START);
            button.setVisibility(View.VISIBLE);
            work.setSingleLine(false);
            workTitle.setText("任务内容设定："+work.getText());
            work.setFocusableInTouchMode(true);
            scrollView_root.smoothScrollTo(0,work.getScrollY());
        }else{
            TransitionManager.beginDelayedTransition(workLayout);
            workTitle.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            work.setGravity(Gravity.CENTER_VERTICAL);
            work.setHeight(Sizes.dp2px(48));
            work.setSingleLine();
            work.clearFocus();
            work.setFocusableInTouchMode(false);
        }
    }

    private void initCompleteTime(View view) {
        completeTime = (AutoCompleteTextView) view.findViewById(R.id.completeTime);
        completeTimeTile = (TextView) view.findViewById(R.id.completeTime_title);
        completeLayout = (LinearLayout) view.findViewById(R.id.completeTime_layout);
        completeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(hasExpandle && TextUtils.isEmpty(work.getText().toString())){
                    changeWorkState(false);
                }
                if (android.os.Build.VERSION.SDK_INT >= 24) {
                    DatePickerDialog pickerDialog = new DatePickerDialog(getContext());
                    pickerDialog.show();
                    pickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            String date = year + "-" + (month +1)+ "-" + dayOfMonth;
                            setDate(date,true);
                        }
                    });
                }else{
                    DatePikerDialog d = new DatePikerDialog(getContext());
                    d.setOnDateSetListener(new DatePikerDialog.OnDateSetListener() {
                        @Override
                        public void dateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                            String date = year + "-" + month + "-" + dayOfMonth;
                            setDate(date,true);
                        }
                    });

                    d.show();



                }
            }
        });
    }

    private void setDate(String date,boolean visiable) {
        completeTime.setText(date);
        TransitionManager.beginDelayedTransition(completeLayout);
        if(visiable){
            completeTimeTile.setText(getActivity().getString(R.string.completeTime)+date);
            completeTimeTile.setVisibility(View.VISIBLE);
        }else{
            completeTimeTile.setText("");
            completeTimeTile.setVisibility(View.GONE);
        }
    }

    private void initSpinner(View view) {
        spinner = (AutoCompleteTextView) view.findViewById(R.id.spinner);
        spinnerItems = new String[]{"全部辅导员","古力娜扎","张学友","周杰伦","安倍晋三","特朗普","艾森豪威尔"
                ,"麦克阿瑟","李奇微","爱新觉罗.玄烨","泰森","马云","玛丽莲.梦露","MaterialSpinner","WorkFrag"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.item_spinner,R.id.item_spinner_text,spinnerItems);
        spinner.setAdapter(adapter);
        inputLayout = (LinearLayout) view.findViewById(R.id.textInputLayout);
        spinnerTile = (TextView) view.findViewById(R.id.spinner_title);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeSpinnerState(true);
            }
        });


        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.showDropDown();
                Drawable drawableUp = getResources().getDrawable(R.drawable.ic_arrow_drop_up_24dp);
                if (drawableUp != null) {
                    drawableUp.setBounds(0,0,drawableUp.getMinimumWidth(), drawableUp.getMinimumHeight());
                    spinner.setCompoundDrawables(null,null,drawableUp,null);
                }

                if(hasExpandle && TextUtils.isEmpty(work.getText().toString())){
                    changeWorkState(false);
                }

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            spinner.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
                @Override
                public void onDismiss() {
                    Drawable drawableDown = getResources().getDrawable(R.drawable.ic_arrow_drop_down_24dp);
                    if (drawableDown != null) {
                        drawableDown.setBounds(0,0,drawableDown.getMinimumWidth(), drawableDown.getMinimumHeight());
                        spinner.setCompoundDrawables(null,null,drawableDown,null);
                    }
                }
            });
        }


    }

    private void changeSpinnerState(boolean b) {
        TransitionManager.beginDelayedTransition(inputLayout);
        if(b){
            spinnerTile.setVisibility(View.VISIBLE);
            spinnerTile.setText("执行任务人员："+spinner.getText());
        }else{
            spinnerTile.setText("");
            spinnerTile.setVisibility(View.GONE);
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
