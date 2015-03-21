package com.example.blin.bennytest;

import android.app.Application;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Lin on 2015/3/15.
 */
public class ApplicationHelper extends Application {

    private boolean session_enter = false; // 默认为false

    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        Log.i("Application","SDk Initial");
    }

    /**
     * 设置session状态
     *
     * @param bol
     *            true为以登录，false未登录
     */
    public void putSession(boolean bol) {
        session_enter = bol;
    }

    /**
     * 是否登录
     *
     * @return true是，false否
     */
    public boolean isEnter() {
        return session_enter;
    }

}