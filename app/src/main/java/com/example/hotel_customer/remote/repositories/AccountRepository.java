package com.example.hotel_customer.remote.repositories;

import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.RetrofitClient;
import com.example.hotel_customer.remote.data.Account;
import com.example.hotel_customer.remote.repositories.interfaces.Repository;
import com.example.hotel_customer.remote.service.UserService;

import retrofit2.Callback;

public class AccountRepository implements Repository {
    UserService userService;
    public AccountRepository(){
        userService = RetrofitClient.gI().getRetrofit().create(UserService.class);
    }

    public void createUser(Account account, Callback<ResData> callback){
        userService.createAccount(account).enqueue(callback);
    }
}
