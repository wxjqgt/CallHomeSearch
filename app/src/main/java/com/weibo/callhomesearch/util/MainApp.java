package com.weibo.callhomesearch.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class MainApp extends Application {

    private static Context context;
    private static SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = this.getSharedPreferences("meishijie",MODE_PRIVATE);
        context = getApplicationContext();
        VolleyUtil.initVolley(this);
    }

    public static SharedPreferences getSp(){
        return sp;
    }

    public static void closeSp(){
        if (sp != null){
            sp = null;
        }
    }

    public static Context getAppContext(){
        return context;
    }
}
