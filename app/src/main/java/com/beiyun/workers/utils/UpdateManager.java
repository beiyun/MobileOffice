package com.beiyun.workers.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.beiyun.library.base.App;
import com.beiyun.library.entity.NetState;
import com.beiyun.workers.R;
import com.beiyun.workers.constant.AppUrl;
import com.beiyun.workers.entity.VersionInfo;
import com.beiyun.workers.okhttp.OkHttpManager;
import com.beiyun.workers.okhttp.OkHttpUtils;
import com.beiyun.workers.okhttp.callback.FileCallBack;
import com.beiyun.workers.okhttp.callback.StringCallBack;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.beiyun.library.util.Nets;
import com.beiyun.library.util.Apps;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


/**
 *检查更新Utils
 */
public class UpdateManager {

    private static UpdateManager updateManager;
    private String apkUrl;
    private MaterialDialog checkDialog = null;

    private static final String APK_NAME = "MobileOffice.apk";

    private String APK_PATH = Environment.getExternalStorageDirectory()
            +File.separator +"MobileOfficeApkFile";
    private MaterialDialog downDialog;

    public static synchronized UpdateManager init() {
        if (updateManager == null) {
            synchronized (UpdateManager.class) {
                if (updateManager == null) {
                    updateManager = new UpdateManager();
                }
            }
        }
        return updateManager;
    }


    private static final String TAG = "UpdateManager";

    public UpdateManager checkVersion(final boolean isFromAboutUi) {
        Log.d(TAG, "checkVersion: connectedType = "+ Nets.getNetState());
        File file = new File(APK_PATH,APK_NAME);
        if(file.exists()){
            PackageManager pm = Apps.getCurrentActivity().getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(file.getAbsolutePath(), PackageManager.GET_ACTIVITIES);
            if(info != null) {
                if(getVersionCode(Apps.getCurrentActivity()) < info.versionCode){
                    noticeInstall();
                    return this;
                }
            }
        }


        if(Nets.getNetState() == NetState.NONE){
            return this;
        }

        if (isFromAboutUi) {
            Log.d(TAG, "checkVersion: ProgressDialog");
            checkDialog = new MaterialDialog.Builder(Apps.getCurrentActivity())
            .content("检查更新").progress(true,100).build();
            checkDialog.show();
        }

        HashMap<String,String> params = new HashMap<>();
        params.put("bo.code","3");
        OkHttpUtils.postQuery(AppUrl.get().CHECK_VERSION, params, new StringCallBack() {
            @Override
            public void onFailure(IOException e) {
                Log.d(TAG, "onFailure: "+e.getMessage());
                if (checkDialog != null) {
                    checkDialog.dismiss();
                }
            }

            @Override
            public void onResponse(String response) throws IOException {
                if (checkDialog != null) {
                    checkDialog.dismiss();
                }
                VersionInfo info = new Gson().fromJson(response, new TypeToken<VersionInfo>() {
                }.getType());

                Log.d(TAG, "onResponse: "+info);
                if (info.getResultCode() == 100) {
                    if (getVersionCode(Apps.getCurrentActivity()) - info.getVersionCode() < 0) {
                        apkUrl = AppUrl.get().BASE_IMAGE_URL + info.getUrl();
                        Log.d(TAG, "onResponse: apkUrl = "+apkUrl);
                        if (!isFromAboutUi) {
                            noticeUser(info);
                        } else {
                            showNoticeDialog(info);
                        }
                    } else {
                        if (isFromAboutUi) {
                            toast("当前已是最新版本");
                        }

                    }
                } else {
                    if (isFromAboutUi) {
                        toast("当前已是最新版本");
                    }
                }
            }
        });

        return this;
    }

    private void noticeUser(VersionInfo info) {
        new MaterialDialog.Builder(Apps.getCurrentActivity())
                .positiveText("允许下载")
                .negativeText("以后再说")
                .content("本次更新内容如下：\n\t\t" +info.getDescription()+
                        "\n\t\t是否允许系统在后台为您下载最新版本，下载完毕后将提示您安装")
                .title("最新版本发布 V"+info.getVersionName())
                .onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
                downLoad();
            }
        }).build().show();


    }

    private void toast(String text){

        Toast.makeText(Apps.getCurrentActivity(),text,Toast.LENGTH_SHORT).show();
    }

    /**
     * 更新提醒
     */
    private void showNoticeDialog(VersionInfo info) {
        new MaterialDialog.Builder(Apps.getCurrentActivity())
                .positiveText("立即更新")
                .negativeText("取消")
                .cancelable(false)
                .content("本次更新内容如下：\n\t\t" +info.getDescription())
                .title("最新版本发布 V"+info.getVersionName())
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        downLoad();
                    }
                }).build().show();

    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if(message.what == 0){
                noticeInstall();
            }else if(message.what == 1){
                Float progress = (Float) message.obj;
                downDialog.setProgress((int) (progress*100));
                if(progress == 1){
                    downDialog.dismiss();
                }
            }
            return true;
        }
    });

    //提示安装
    private void noticeInstall() {
        new MaterialDialog.Builder(Apps.getCurrentActivity())
                .positiveText("立即替换")
                .negativeText("稍后替换")
                .cancelable(false)
                .content("此APP有新版本，请替换新版本后再使用！")
                .title("版本更新提示")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        installApk();
                    }
                }).build().show();


    }

    /**
     * 下载对话框
     */
    private void downLoad() {

        if (!android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return;
        }

        File apkFile = new File(APK_PATH, APK_NAME);
        if (!apkFile.exists()) {
            boolean delete = apkFile.delete();
            Log.d(TAG, "downLoad: delete same file result = "+delete);
        }

        showProgressDialog();

        OkHttpManager.downLoad(apkUrl, new FileCallBack(APK_PATH,APK_NAME) {
            @Override
            public void onFailure(IOException e) {
                toast(e.getMessage());
            }

            @Override
            public void onResponse(Object response) throws IOException {
                handler.sendEmptyMessage(0);
            }

            @Override
            public void inProgress(float progress, long total, boolean hasDone) {
                super.inProgress(progress, total, hasDone);
                Log.d(TAG, "inProgress: "+progress+"----"+total+"---"+hasDone);
                if(mProgressListener != null){
                    mProgressListener.inProgress(progress,total,hasDone);
                }

                handler.obtainMessage(1,progress).sendToTarget();
            }
        });

    }

    private void showProgressDialog() {
        downDialog = new MaterialDialog.Builder(Apps.getCurrentActivity())
                .content("下载中，请勿关闭窗口")
                .progress(false, 100, true)
                .cancelable(false).build();
        downDialog.show();

    }

    private OnProgressChangedListener mProgressListener;

    public interface OnProgressChangedListener{
        void inProgress(float progress, long total, boolean hasDone);
    }

    public void setOnProgressChangedListener(OnProgressChangedListener listener){
        this.mProgressListener = listener;
    }

    private void installApk() {
        File apkFile = new File(APK_PATH, APK_NAME);
        if (!apkFile.exists()) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Log.d(TAG, "installApk: 6.0");
            openFile(apkFile,Apps.getCurrentActivity());
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(Uri.fromFile(apkFile),
                "application/vnd.android.package-archive");
        Apps.getCurrentActivity().startActivity(intent);
        updateManager = null;
    }

    private void openFile(File file, Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.intent.action.VIEW");
        String var3 = getMIMEType(file);
        intent.setDataAndType(Uri.fromFile(file), var3);
        try {
            context.startActivity(intent);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(context, "没有找到打开此类文件的程序", Toast.LENGTH_SHORT).show();
        }
    }

    private String getMIMEType(File file) {
        String var1 = "";
        String var2 = file.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
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

}
