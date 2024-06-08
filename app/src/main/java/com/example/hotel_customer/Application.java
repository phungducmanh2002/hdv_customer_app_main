package com.example.hotel_customer;

import android.content.Context;

public class Application extends android.app.Application {
    public static boolean isLogined = false;
    private static Context applicationContext;
    public static Context GetContext(){
        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }
}
