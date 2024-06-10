package com.example.hotel_customer.remote.service;

import com.example.hotel_customer.model.ResData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HotelService {
    @GET("/hotel/hotels")
    public Call<ResData> getAllHotels(@Query("filter") String filter, @Query("page") int page);

    @GET("/hotel/hotels/{idHotel}")
    public Call<ResData> getHotel(@Path("idHotel") int idHotel);

    @GET("/hotel/communes/{idCommune}/hotels")
    public Call<ResData> getCommuneHotels(@Path("idCommune") int idCommune);

    @GET("/hotel/hotels/{idHotel}/near")
    public Call<ResData> getHotelsNear(@Path("idHotel") int idHotel, @Query("page") int page);

    // booking

    @GET("/hotel/hotels/{idHotel}/room-classes")
    public Call<ResData> getHotelRoomClasses(@Path("idHotel") int idHotel);
}
