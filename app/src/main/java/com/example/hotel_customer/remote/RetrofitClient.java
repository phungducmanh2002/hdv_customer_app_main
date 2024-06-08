package com.example.hotel_customer.remote;

import com.example.hotel_customer.helper.FileHelper;

import lombok.Getter;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    @Getter
    private Retrofit retrofit;
    private String serverIp;
    private int serverPort;
    private static RetrofitClient instance;
    public String baseUrl;
    private RetrofitClient(){
        this.serverIp = FileHelper.GetConfigValue("SERVER_IP");
        this.serverPort = Integer.parseInt(FileHelper.GetConfigValue("SERVER_PORT"));
        this.baseUrl = String.format("http://%s:%d", this.serverIp, this.serverPort);
        this.retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static RetrofitClient gI(){
        if(instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }
    public void setToken(String token){
        // Tạo header
        token = "Bearer " + token;
        Headers headers = new Headers.Builder().set("Authorization", token).build();
        // Setup http client
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request();
            request = request.newBuilder().headers(headers).build();
            return chain.proceed(request);
        }).build();
        // rebuild retrofit
        retrofit = retrofit.newBuilder().client(okHttpClient).build();
    }
    public static void Reset(){
        instance = null;
    }
}
