package com.example.hotel_customer.remote.repositories;

import android.widget.ImageView;

import com.example.hotel_customer.cache.OnLoadIMG;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.RetrofitClient;
import com.example.hotel_customer.remote.repositories.interfaces.Repository;
import com.example.hotel_customer.remote.service.HotelService;
import com.example.hotel_customer.remote.service.ResService;

import retrofit2.Callback;

public class HotelRepo implements Repository {
    HotelService hotelService;
    ResService resService;
    public HotelRepo(){
        hotelService = RetrofitClient.gI().getRetrofit().create(HotelService.class);
        resService = RetrofitClient.gI().getRetrofit().create(ResService.class);
    }

    // home activity
    public void getAllHotelsNewest(int page,Callback<ResData> callback){
        hotelService.getAllHotels("newest", page).enqueue(callback);
    }
    public void getAllHotelsBest(int page,Callback<ResData> callback){
        hotelService.getAllHotels("best", page).enqueue(callback);
    }
    public void getHotelIdAvatar(int idHotel, Callback<ResData> callback){
        resService.getHotelAvatar(idHotel, 0).enqueue(callback);
    }

    // hotel activity
    public void getHotel(int idHotel, Callback<ResData> callback){
        hotelService.getHotel(idHotel).enqueue(callback);
    }
    public void getHotelImagesId(int idHotel, Callback<ResData> callback){
        resService.getHotelImagesId(idHotel).enqueue(callback);
    }

    public void getCommuneHotels(int idCommune, Callback<ResData> callback){
        hotelService.getCommuneHotels(idCommune).enqueue(callback);
    }

    public void getHotelsNear(int idHotel, Callback<ResData> callback){
        hotelService.getHotelsNear(idHotel, 1).enqueue(callback);
    }
}
