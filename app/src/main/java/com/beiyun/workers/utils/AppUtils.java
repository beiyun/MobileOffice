package com.beiyun.workers.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.beiyun.workers.interf.IFromView;
import com.beiyun.workers.view.CountEditText;
import com.beiyun.workers.view.FormView;
import com.beiyun.workers.view.GrowTobaccoInfoFormLayout;
import com.beiyun.workers.view.SortStrainLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zqht on 2016/7/7 18:14
 * Email:zmm534635184@sina.com
 */
public class AppUtils {
    public static double MAX_NUM = 99999999.99;
    public static int DEFAULT_POT_AFTER = 2;
    public static int DEFAULT_POT_BEFORE = 8;
    public static String ILLEGAL_NUMBER = "数值超出限制范围";

    /**
     * 多选
     *
     * @param context   当前上下文
     * @param array     数据
     * @param codeArray 数据对应编码
     * @param et        输入框
     * @param title     提示
     */
    public static void createChoiceDialog(Activity context,
                                          final String[] array, final String[] codeArray, final FormView et, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final List<String> listData = new ArrayList<>();
        final List<String> listCode = new ArrayList<>();
        builder.setTitle(title);
        final boolean[] isCheck = new boolean[array.length];
        Arrays.fill(isCheck, false);
        // 设置多选项
        builder.setMultiChoiceItems(array, isCheck,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position,
                                        boolean isChoice) {
                        isCheck[position] = isChoice;
                        setData();
                    }

                    private void setData() {
                        listData.clear();
                        listCode.clear();
                        for (int i = 0; i < array.length; i++) {
                            if (isCheck[i]) {
                                listData.add(array[i]);
                                listCode.add(codeArray[i]);
                            }
                        }
                    }
                });
        // 设置确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                int size = listData.size();
                String[] result = new String[listData.size()];
                String[] dataCodes = new String[listCode.size()];
                for (int i = 0; i < size; i++) {
                    result[i] = listData.get(i);
                    dataCodes[i] = listCode.get(i);
                }
                String str = "";
                String code = "";
                for (int i = 0; i < result.length; i++) {
                    if (i != result.length - 1) {
                        code += dataCodes[i] + ",";
                        str += result[i] + ",";
                    } else {
                        code += dataCodes[i];
                        str += result[i];
                    }
                }
                et.setEditText(str);
                et.setCode(code);
            }
        });
        // 设置取消按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();
            }
        });
        builder.create().show();
    }

    public static void disableSubControls(ViewGroup group) {
        if (group != null) {
            for (int i = 0; i < group.getChildCount(); i++) {
                View v = group.getChildAt(i);
                if (v instanceof ViewGroup) {
                    if (v instanceof FormView) {
                        ((FormView) v).setEditable(false);
                    } else if (v instanceof GrowTobaccoInfoFormLayout) {
                        v.setFocusable(false);
                    } else if (v instanceof SortStrainLayout) {
                        v.setFocusable(false);
                    }else if(v instanceof CountEditText){
                        ((CountEditText)v).setEditable(false);}
                    else {
                        disableSubControls((ViewGroup) v);
                    }
                } else if (v instanceof Button) {
                    v.setEnabled(false);
                }
            }
        }
    }

    public static void defaultSubControls(ViewGroup group) {
        if (group != null) {
            for (int i = 0; i < group.getChildCount(); i++) {
                View v = group.getChildAt(i);
                if (v instanceof ViewGroup) {
                    if (v instanceof FormView) {
                        ((FormView) v).setEditable(true);
                    } else if (v instanceof GrowTobaccoInfoFormLayout) {
                        v.setFocusable(true);
                    }else if(v instanceof SortStrainLayout){
                        v.setFocusable(true);
                    }else if(v instanceof CountEditText){
                        ((CountEditText)v).setEditable(true);
                    }else {
                        defaultSubControls((ViewGroup) v);
                    }
                } else if (v instanceof Button) {
                    v.setEnabled(true);
                }
            }
        }
    }

    /**
     * 获取版本号
     *
     * @param context 上下文
     */
    public static String getVersionName(Context context) {
        PackageManager manager = context.getPackageManager();
        String versionName = "";
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取应用版本号
     *
     * @param context 上下文
     */
    public static int getVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int versionName = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            versionName = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void toastShow(Context context, String str) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void snackBar(View view, String text) {
        Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 小数点及数值限制
     *
     * @param view         输入控件
     * @param range        最大值
     * @param intCount     小数点前面位数
     * @param decimalCount 小数点后面位数
     */
    public static void doubleDecimalListener(final FormView view, double range, int intCount, int decimalCount) {
        final double compared = range == 0 ? MAX_NUM : range;
        final int dCount = decimalCount == 0 ? DEFAULT_POT_AFTER : decimalCount;
        final int iCount = intCount == 0 ? DEFAULT_POT_BEFORE : intCount;
        view.addTextChangeListener(new IFromView.TextChangeListener() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                if (charSequence.toString().contains(".")) {
                    if (charSequence.length() - 1 - charSequence.toString().indexOf(".") > dCount) {
                        charSequence = charSequence.toString().subSequence(0,
                                charSequence.toString().indexOf(".") + dCount + 1);
                        view.setEditText(charSequence.toString());
                        view.setSelection(charSequence.length());
                    }
                } else if (charSequence.length() > iCount) {
                    charSequence = charSequence.toString().subSequence(0, iCount);
                    view.setEditText(charSequence.toString());
                    view.setSelection(charSequence.length());
                } else if (charSequence.length() != 0) {
                    double src = Double.valueOf(view.getText());
                    if (src - compared > 0) {
                        snackBar(view, ILLEGAL_NUMBER);
                        charSequence = charSequence.toString().subSequence(0, iCount - 1);
                        view.setEditText(charSequence.toString());
                        view.setSelection(charSequence.length());
                    }
                }
                /**
                 * 第一位输入小数点的话自动变换为 0.
                 */
                if (charSequence.toString().trim().equals(".")) {
                    charSequence = "0" + charSequence;
                    view.setEditText(charSequence.toString());
                    view.setSelection(2);
                }

                /**
                 * 避免重复输入小数点前的0 ,没有意义
                 */
                if (charSequence.toString().startsWith("0")
                        && charSequence.toString().trim().length() > 1) {
                    if (!charSequence.toString().substring(1, 2).equals(".")) {
                        view.setEditText(charSequence.subSequence(0, 1).toString());
                        view.setSelection(1);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(view.getText())) {
                    double src = Double.valueOf(view.getText());
                    if (src - compared > 0) {
                        snackBar(view, ILLEGAL_NUMBER);
                        view.setEditText(String.valueOf(0));
                        view.setSelection(1);
                    }
                }
            }
        });
    }

    /**
     * 小数点及数值限制
     *
     * @param view         输入控件
     * @param intCount     小数点前面位数
     * @param decimalCount 小数点后面位数
     */
    public static void doubleDecimalListener(final FormView view, int intCount, int decimalCount) {
        final int dCount = decimalCount == 0 ? DEFAULT_POT_AFTER : decimalCount;
        final int iCount = intCount == 0 ? DEFAULT_POT_BEFORE : intCount;
        view.addTextChangeListener(new IFromView.TextChangeListener() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                if (charSequence.toString().contains(".")) {
                    if (charSequence.length() - 1 - charSequence.toString().indexOf(".") > dCount) {
                        charSequence = charSequence.toString().subSequence(0,
                                charSequence.toString().indexOf(".") + dCount + 1);
                        view.setEditText(charSequence.toString());
                        view.setSelection(charSequence.length());
                    }
                } else if (charSequence.length() > iCount) {
                    charSequence = charSequence.toString().subSequence(0, iCount);
                    view.setEditText(charSequence.toString());
                    view.setSelection(charSequence.length());
                }
                /**
                 * 第一位输入小数点的话自动变换为 0.
                 */
                if (charSequence.toString().trim().equals(".")) {
                    charSequence = "0" + charSequence;
                    view.setEditText(charSequence.toString());
                    view.setSelection(2);
                }

                /**
                 * 避免重复输入小数点前的0 ,没有意义
                 */
                if (charSequence.toString().startsWith("0")
                        && charSequence.toString().trim().length() > 1) {
                    if (!charSequence.toString().substring(1, 2).equals(".")) {
                        view.setEditText(charSequence.subSequence(0, 1).toString());
                        view.setSelection(1);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    /**
     * 小数点及数值限制
     *
     * @param view         输入控件
     * @param range        最大值
     * @param intCount     小数点前面位数
     * @param decimalCount 小数点后面位数
     */
    public static void decimalListener(final EditText view, double range, int intCount, int decimalCount) {
        final double compared = range == 0 ? MAX_NUM : range;
        final int dCount = decimalCount == 0 ? DEFAULT_POT_AFTER : decimalCount;
        final int iCount = intCount == 0 ? DEFAULT_POT_BEFORE : intCount;
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                if (charSequence.toString().contains(".")) {
                    if (charSequence.length() - 1 - charSequence.toString().indexOf(".") > dCount) {
                        charSequence = charSequence.toString().subSequence(0,
                                charSequence.toString().indexOf(".") + dCount + 1);
                        view.setText(charSequence.toString());
                        view.setSelection(charSequence.length());
                    }
                } else if (charSequence.length() > iCount) {
                    charSequence = charSequence.toString().subSequence(0, iCount);
                    view.setText(charSequence.toString());
                    view.setSelection(charSequence.length());
                } else if (charSequence.length() != 0) {
                    double src = Double.valueOf(charSequence.toString());
                    if (src - compared >= 0) {
                        snackBar(view, ILLEGAL_NUMBER);
                        charSequence = charSequence.toString().subSequence(0, iCount - 1);
                        view.setText(charSequence.toString());
                        view.setSelection(charSequence.length());
                    }
                }
                /**
                 * 第一位输入小数点的话自动变换为 0.
                 */
                if (charSequence.toString().trim().equals(".")) {
                    charSequence = "0" + charSequence;
                    view.setText(charSequence.toString());
                    view.setSelection(2);
                }

                /**
                 * 避免重复输入小数点前的0 ,没有意义
                 */
                if (charSequence.toString().startsWith("0")
                        && charSequence.toString().trim().length() > 1) {
                    if (!charSequence.toString().substring(1, 2).equals(".")) {
                        view.setText(charSequence.subSequence(0, 1).toString());
                        view.setSelection(1);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(view.getText())) {
                    double src = Double.valueOf(view.getText().toString().trim());
                    if (src - compared >= 0) {
                        snackBar(view, ILLEGAL_NUMBER);
                        view.setText(String.valueOf(0));
                        view.setSelection(1);
                    }
                }
            }
        });
    }


    /**
     * @param forView 被判断
     * @param appView 结果显示
     * @param data    比较的数值
     */
    public static void forView_AddText(final FormView forView, final TextView appView, final Integer data) {
        forView.addTextChangeListener(new IFromView.TextChangeListener() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String view = forView.getText();
                if (!TextUtils.isEmpty(view)) {
                    if (Integer.valueOf(view) > data) {
                        appView.setVisibility(View.VISIBLE);
                    } else {
                        appView.setVisibility(View.GONE);
                    }
                } else {
                    appView.setVisibility(View.GONE);
                }
            }
        });

    }


    public static boolean compareStr(String s, String s2) {
        return !(!TextUtils.isEmpty(s) && !TextUtils.isEmpty(s2)) || s.compareTo(s2) >= 0;
    }

    /**
     * 隐藏键盘
     * @param activity  当前activity
     */
    public static void hideSoftInput(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
