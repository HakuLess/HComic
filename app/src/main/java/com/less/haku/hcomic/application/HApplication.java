package com.less.haku.hcomic.application;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
//import com.squareup.leakcanary.LeakCanary;

/**
 * Created by HaKu on 15/12/22.
 * 全局Application
 */
public class HApplication extends Application {
    //全局单例模式覆盖application，onCreate方法为真正应用入口
    private static HApplication _instance;

    public static HApplication instance() {
        if (_instance == null) {
            throw new IllegalStateException("Application not init!!!");
        }
        return _instance;
    }

    public HApplication() {
        _instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("init", "初始化应用");

        Fresco.initialize(this);
//        LeakCanary.install(this);
    }
}
