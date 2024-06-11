package com.example.hotel_customer.remote.repositories;

import android.widget.ImageView;

import com.example.hotel_customer.cache.OnLoadIMG;
import com.example.hotel_customer.model.ContainToken;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.RetrofitClient;
import com.example.hotel_customer.remote.repositories.interfaces.Repository;
import com.example.hotel_customer.remote.service.HotelService;
import com.example.hotel_customer.remote.service.ResService;
import com.example.hotel_customer.remote.service.UserService;

import retrofit2.Callback;

public class HotelRepo implements Repository {
    HotelService hotelService;
    ResService resService;
    UserService userService;
    public HotelRepo(){
        hotelService = RetrofitClient.gI().getRetrofit().create(HotelService.class);
        resService = RetrofitClient.gI().getRetrofit().create(ResService.class);
        userService = RetrofitClient.gI().getRetrofit().create(UserService.class);
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

    // user
    public void decodeToken(String token, Callback<ResData> callback) {
        ContainToken containToken = new ContainToken();
        containToken.setToken(token);
        userService.decodeToken(containToken).enqueue(callback);
    }
}
