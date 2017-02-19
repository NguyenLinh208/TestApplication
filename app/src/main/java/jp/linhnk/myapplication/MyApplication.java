package jp.linhnk.myapplication;

import android.support.multidex.MultiDexApplication;

/**
 * Created by usr0200475 on 2017/02/13.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */


public class MyApplication extends MultiDexApplication {

    //Singletonパターン
    private static MyApplication instance;

    public MyApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
