package com.example.hotel_customer.helper;

import com.example.hotel_customer.interfaces.OnEvent;
import com.example.hotel_customer.interfaces.OnRES;
import com.example.hotel_customer.model.ResData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RESCallback {
    public static Callback<ResData> CB(OnRES.Success success, OnRES.Failed failed) {
        return new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() > 201) {
                            throw new Exception(response.body().getMessage());
                        }
                        success.action(response.body());
                    } else {
                        throw new Exception("Gọi api thất bại");
                    }
                } catch (Exception ex) {
                    failed.action(ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable t) {
                failed.action(t.getMessage());

            }
        };
    }
}
