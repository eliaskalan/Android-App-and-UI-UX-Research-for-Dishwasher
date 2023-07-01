package com.example.dishwasherux;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication instance;
    private boolean isSoundOpen = true;

    public static MyApplication getInstance() {
        return instance;
    }

    public boolean getIsSoundOpen() {
        return isSoundOpen;
    }

    public void setIsSoundOpen(boolean value) {
        isSoundOpen = value;
    }

    public int getSoundDrawable(){
        if(isSoundOpen){
            return R.drawable.baseline_volume_up_24;
        }
        return R.drawable.baseline_volume_off_24;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        isSoundOpen = true;
    }
}
