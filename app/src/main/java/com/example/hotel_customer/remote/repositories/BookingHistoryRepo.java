package com.example.hotel_customer.remote.repositories;

import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.RetrofitClient;
import com.example.hotel_customer.remote.repositories.interfaces.Repository;
import com.example.hotel_customer.remote.service.BookingService;

import retrofit2.Callback;

public class BookingHistoryRepo implements Repository {
    BookingService bookingService;
    public  BookingHistoryRepo(){
        bookingService = RetrofitClient.gI().getRetrofit().create(BookingService.class);
    }

    public void getUserBookings(int idUser, int status, Callback<ResData> callback){
        bookingService.getUserBookings(idUser, status).enqueue(callback);
    }

    public void cancleBooking(Integer id, Callback<ResData> cb) {
        bookingService.updateBookingStatus(id, 5).enqueue(cb);
    }
}
