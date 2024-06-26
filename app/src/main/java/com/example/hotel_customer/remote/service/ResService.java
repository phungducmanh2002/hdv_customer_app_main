package com.example.hotel_customer.remote.service;

import com.example.hotel_customer.model.ResData;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ResService {
    @GET("/res/hotels/{idHotel}/avatar")
    public Call<ResData> getHotelAvatar(@Path("idHotel") int idHotel, @Query("type") int type);

    @GET("/res/hotels/{idHotel}/images")
    public Call<ResData> getHotelImagesId(@Path("idHotel") int idHotel);

    @GET("/res/avatar/{idUser}/images")
    public Call<ResData> getUserAvatar(@Path("idUser") int idUser, @Query("type") int type);

    @POST("/res/avatar/{idUser}/images")
    @Multipart
    public Call<ResData> updateUserAvatar(@Path("idUser") int idUser, @Part MultipartBody.Part image);

}
