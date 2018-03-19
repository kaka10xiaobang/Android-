package com.kaka.retrofitdemo;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;

/**
 * Created by kaka on 2018/3/19.
 * email:375120706@qq.com
 */

public class RetrofitApp extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        Utils.init(this);
    }

    public static Context getContext(){
        return mContext;
    }

}
