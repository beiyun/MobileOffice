package com.beiyun.workers.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.beiyun.workers.R;


/**
 * Created by mpb on 2016/6/30.
 */
public class ExpandableView extends LinearLayout implements View.OnClickListener {

    private SectionalLine mTitleView;

    private String mTitle;

    private ImageView mPointer;

    private FormView mContentView;

    private String mContent;

    private RelativeLayout mTitleLayout;

    private boolean isExpandable = false;//是否展开

    private Animation mOpenAnim,mCloseAnim;



    public ExpandableView(Context context) {
        this(context,null);
    }

    public ExpandableView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExpandableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpandableView,defStyleAttr,0);
        mTitle = a.getString(R.styleable.ExpandableView_Expandable_title);
        mContent = a.getString(R.styleable.ExpandableView_Expandable_content);

        init();

        setTitle(mTitle);
        setContent(mContent);


    }

    private void init() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.expandable_view,null,true);
        mTitleView = (SectionalLine) view.findViewById(R.id.expandable_title);
        mContentView = (FormView) view.findViewById(R.id.expandable_content);
        mPointer = (ImageView) view.findViewById(R.id.expandable_pointer);
        mTitleLayout = (RelativeLayout) view.findViewById(R.id.expandable_title_layout);
        view.setLayoutParams(new LayoutParams(-1,-2));
        this.addView(view);
        mTitleLayout.setOnClickListener(this);
        mOpenAnim = AnimationUtils.loadAnimation(getContext(),android.R.anim.fade_in);
        mCloseAnim = AnimationUtils.loadAnimation(getContext(),android.R.anim.fade_out);

    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String Content) {
        this.mContent = Content;
        if(mContent != null){
            mContentView.setText(mContent);
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String Title) {
        this.mTitle = Title;
        if(mTitle != null){
            mTitleView.setSectionalLineTitle(mTitle);
        }
    }

    @Override
    public void onClick(View v) {
        isExpandable = !isExpandable;


        if(isExpandable){
            mPointer.setImageResource(R.drawable.ic_arrow_drop_up_24dp);
            mContentView.startAnimation(mOpenAnim);
            mContentView.setVisibility(View.VISIBLE);

        }else{
            mPointer.setImageResource(R.drawable.ic_arrow_drop_down_24dp);
            mContentView.startAnimation(mCloseAnim);
            mContentView.setVisibility(View.GONE);
        }
    }
}
