package com.example.hotel_customer.remote.repositories;

import android.util.Log;

import com.example.hotel_customer.helper.RESCallback;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.RetrofitClient;
import com.example.hotel_customer.remote.repositories.interfaces.Repository;
import com.example.hotel_customer.remote.service.ResService;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;

public class ResRepo implements Repository {
    ResService resService;
    public ResRepo(){
        this.resService = RetrofitClient.gI().getRetrofit().create(ResService.class);
    }

    public void updateUserAvatar(int idUser, byte[] data, Callback<ResData> callback){
        Log.d("ONUPDATEAVATAR", String.format("id user update avatar: %d\ndata length: %d", idUser, data.length));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), data);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);
        Log.d("ONUPDATEAVATAR", String.format("on request file success"));

        resService.updateUserAvatar(idUser, body).enqueue(callback);
    }
}
