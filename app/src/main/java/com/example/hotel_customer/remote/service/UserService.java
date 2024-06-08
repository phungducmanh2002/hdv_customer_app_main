package com.example.hotel_customer.remote.service;

import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.data.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/accounts")
    public Call<ResData> createAccount(@Body Account account);
}
