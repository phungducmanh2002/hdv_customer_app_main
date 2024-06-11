package com.example.hotel_customer.controller;

import android.util.Log;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.helper.RESCallback;
import com.example.hotel_customer.remote.data.Booking;
import com.example.hotel_customer.remote.repositories.BookingHistoryRepo;
import com.example.hotel_customer.view.booking.BookingHistoryActivity;

public class BookingHistoryController
        extends BaseController<BookingHistoryActivity, BookingHistoryRepo> {


    public BookingHistoryController(BookingHistoryActivity view){
        this.view = view;
        this.repository = new BookingHistoryRepo();
    }

    public void loadUserBookings(int idUser, int status){
        view.showWaitingDialog();
        repository.getUserBookings(idUser, status, RESCallback.CB(resData -> {
            Booking[] bookings = DataHelper.ConvertFromObjectArr(resData.getData(), Booking[].class);
            view.onLoadBookingsSuccess(bookings);
            view.cancleWaitingDialog();
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
        }));
    }

    public void cancleBooking(Integer id) {
        Log.d("CANCACAC", id.toString());
        view.showWaitingDialog();
        repository.cancleBooking(id, RESCallback.CB(resData -> {
            view.onCancleBookingSuccess();
            view.cancleWaitingDialog();
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
        }));
    }
}
