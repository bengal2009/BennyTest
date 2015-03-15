package com.example.blin.bennytest;

import android.app.Application;

/**
 * Created by Lin on 2015/3/15.
 */
public class ApplicationHelper extends Application {

    private boolean session_enter = false; // 默认为false

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