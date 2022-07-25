package com.hx.mdesign.utils;

import android.app.Application;
import android.content.Context;

/**
 * @author: Hx
 * @date: 2022年03月02日 0:33
 */
public final class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取Context
        mContext = getApplicationContext();
    }

    //获取Context方法
    public static Context getContext() {
        return mContext;
    }
}
