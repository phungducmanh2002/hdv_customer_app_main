package com.example.hotel_customer;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.hotel_customer.remote.data.Account;
import com.example.hotel_customer.remote.data.User;

public class Application extends android.app.Application {
    public static User user;
    public static Account account;
    public static String token = null;
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
