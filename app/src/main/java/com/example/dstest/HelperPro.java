package com.example.dstest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;


import androidx.annotation.NonNull;

import com.example.dstest.utils.YandexMetriсaHelper;

public class HelperPro extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;

        YandexMetriсaHelper.activate(getResources().getString(R.string.appmetrica_key)); }

    @NonNull
    public static Context getAppContext() {
        return appContext;
    }
}

