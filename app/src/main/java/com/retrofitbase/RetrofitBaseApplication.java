package com.retrofitbase;

import android.app.Application;
import android.content.Context;

public class RetrofitBaseApplication extends Application {

    /**
     * Instance
     */
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        /* Set context */
        RetrofitBaseApplication.context = getApplicationContext();

    }

    public static Context getInstance() {
        return RetrofitBaseApplication.context;
    }
}