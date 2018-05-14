package com.beiyun.workers.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;

import com.beiyun.workers.R;

/**
 * Created by beiyun on 2018/4/5.
 * Workers
 */
public class DatePikerDialog extends AlertDialog implements DialogInterface.OnClickListener{


    private OnDateSetListener mListener;
    private DatePicker datePicker;

    public DatePikerDialog(@NonNull Context context) {
        this(context,true,null);
    }

    private DatePikerDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        View view = getLayoutInflater().inflate(R.layout.view_date_piker, null);
        this.setView(view);
        datePicker = view.findViewById(R.id.date_picker);
        setButton(BUTTON_POSITIVE, context.getString(R.string.ok), this);
        setButton(BUTTON_NEGATIVE, context.getString(R.string.cancel), this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case BUTTON_NEGATIVE:
                cancel();
                break;
            case BUTTON_POSITIVE:
                if(mListener != null){
                    mListener.dateSet(datePicker,datePicker.getYear(),datePicker.getMonth()+1,datePicker.getDayOfMonth());
                }
                dismiss();
                break;
        }

    }

    public interface OnDateSetListener{
        void dateSet(DatePicker datePicker,int year,int month,int dayOfMonth);
    }

    public void setOnDateSetListener(OnDateSetListener listener){
        mListener = listener;
    }

    }
