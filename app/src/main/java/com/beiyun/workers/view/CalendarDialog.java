package com.beiyun.workers.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.beiyun.workers.R;


/**
 * Created by mpb on 2016/7/11.
 * 日期和时间选择控件
 */
public class CalendarDialog extends Dialog implements View.OnClickListener {

    private CardView mCardView;
    private Activity mContext;
    private android.widget.DatePicker datePicker;
    private TimePicker timePicker;
    public static final int DATE_PICKER = 0;
    public static final int TIME_PICKER = 1;
    private int showStyle;


    public CalendarDialog(Context context, int showStyle) {
        super(context, R.style.FullScreenDialog);
        this.showStyle = showStyle;
        init((Activity) context);
    }




    private void init(final Activity context) {
        mContext = context;
        setContentView(R.layout.dialog_calendar);
        mCardView = (CardView) findViewById(R.id.calendar_cardView);
        datePicker = (android.widget.DatePicker) findViewById(R.id.date_picker);
        timePicker = (TimePicker) findViewById(R.id.time_picker);
        findViewById(R.id.rootView).setOnClickListener(this);
        findViewById(R.id.submit).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        config();
    }

    private void config() {
        Display display  = mContext.getWindowManager().getDefaultDisplay();
        int screenWidth = display.getWidth();
        RelativeLayout.LayoutParams ll = new RelativeLayout.LayoutParams(-1,-2);
        ll.leftMargin = (int) (screenWidth*0.15);
        ll.rightMargin = (int) (screenWidth*0.15);
        ll.addRule(RelativeLayout.CENTER_IN_PARENT);
        mCardView.setLayoutParams(ll);
    }

    @Override
    public void show() {
        if(isShowing()) return;
        if(showStyle == DATE_PICKER){
            datePicker.setVisibility(View.VISIBLE);
        }else if(showStyle == TIME_PICKER){
            timePicker.setVisibility(View.VISIBLE);
        }
        super.show();

    }


    @Override
    public void dismiss() {
        super.dismiss();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rootView:
            case R.id.cancel:
                dismiss();
                break;
            case R.id.submit:
                if(showStyle == DATE_PICKER){
                    if(mOnDateSelectedListener != null){
                        Date date = new Date();
                        date.year = datePicker.getYear();
                        date.month = datePicker.getMonth()+1;
                        date.day = datePicker.getDayOfMonth();
                        mOnDateSelectedListener.onDateSelected(date);
                    }
                }else if(showStyle == TIME_PICKER){
                    if(mOnTimeSelectedListener != null){
                        Time time = new Time();
                        time.hour = timePicker.getCurrentHour();
                        time.minute = timePicker.getCurrentMinute();
                        mOnTimeSelectedListener.onTimeSelected(time);
                    }

                }

                break;
        }
    }

    private OnDateSelectedListener mOnDateSelectedListener;
    private OnTimeSelectedListener mOnTimeSelectedListener;

    public void setOnDateSelectedListener(OnDateSelectedListener listener){
        this.mOnDateSelectedListener = listener;
    }

    public void setOnTimeSelectedListener(OnTimeSelectedListener listener){
        this.mOnTimeSelectedListener = listener;
    }




    public interface OnDateSelectedListener{
        void onDateSelected(Date date);
    }

    public interface OnTimeSelectedListener{
        void onTimeSelected(Time time);
    }


    class Date{
        public int year;
        public int month;
        public int day;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    class Time{
        public int hour;
        public int minute;

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }
    }

}
