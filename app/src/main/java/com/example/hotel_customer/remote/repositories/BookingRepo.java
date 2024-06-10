package com.example.hotel_customer.remote.repositories;

import android.util.Log;

import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.RetrofitClient;
import com.example.hotel_customer.remote.data.Booking;
import com.example.hotel_customer.remote.repositories.interfaces.Repository;
import com.example.hotel_customer.remote.service.BookingService;
import com.example.hotel_customer.remote.service.HotelService;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;

public class BookingRepo implements Repository {
    BookingService bookingService;
    HotelService hotelService;

    public BookingRepo() {
        this.bookingService = RetrofitClient.gI().getRetrofit().create(BookingService.class);
        this.hotelService = RetrofitClient.gI().getRetrofit().create(HotelService.class);
    }

    public void getHotelRoomClasses(int idHotel, Callback<ResData> callback) {
        hotelService.getHotelRoomClasses(idHotel).enqueue(callback);
    }

    public void getHotelRoomClassRooms(int idHotel, int idRoomClass, Date startTime, Date endTime, Callback<ResData> callback) {
        String startTimeStr= DataHelper.Date2String(startTime, "yyyy-MM-dd");
        String endTimeStr= DataHelper.Date2String(endTime, "yyyy-MM-dd");

        bookingService.getRoomsCanBooking(idHotel, idRoomClass, startTimeStr, endTimeStr).enqueue(callback);
    }

    public void createBooking(Booking booking, Callback<ResData> callback){
        bookingService.createBooking(booking).enqueue(callback);
    }

    public void getBooking(int idBooking, Callback<ResData> callback){
        bookingService.getBooking(idBooking).enqueue(callback);
    }
}
