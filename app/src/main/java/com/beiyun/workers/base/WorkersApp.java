package com.beiyun.workers.base;

import android.app.Application;

import com.beiyun.library.base.ProjectHelper;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by beiyun on 2018/3/28.
 * Workers
 */
public class WorkersApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ProjectHelper.init(this);
        Fresco.initialize(this);
    }
}
