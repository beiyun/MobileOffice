package com.beiyun.workers.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beiyun.workers.R;


/**
 * Created by mpb on 2016/6/29.
 */
public class SectionalLine extends LinearLayout {

    private TextView mTextView;

    private String mSectionalLineTitle;

    public SectionalLine(Context context) {
        this(context,null);
    }

    public SectionalLine(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SectionalLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SectionalLine,defStyleAttr,0);
        mSectionalLineTitle = a.getString(R.styleable.SectionalLine_sectional_line_title);
        setSectionalLineTitle(mSectionalLineTitle);

    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.sectional_line,null,true);
        mTextView = (TextView) view.findViewById(R.id.sectional_line_text);
        view.setLayoutParams(new LayoutParams(-1,-2));
        this.addView(view);



    }

    public String getSectionalLineTitle() {
        return mSectionalLineTitle;
    }

    public void setSectionalLineTitle(String mSectionalLineTitle) {
        this.mSectionalLineTitle = mSectionalLineTitle;
        if(mSectionalLineTitle != null && mTextView != null){
            mTextView.setText(mSectionalLineTitle);
        }
    }
}
