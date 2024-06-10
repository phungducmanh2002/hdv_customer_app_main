package com.example.hotel_customer.controller;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.helper.RESCallback;
import com.example.hotel_customer.remote.data.Booking;
import com.example.hotel_customer.remote.repositories.BookingRepo;
import com.example.hotel_customer.view.booking.BookingActivity;

public class BookingController3 extends BaseController<BookingActivity, BookingRepo> {
    public BookingController3(BookingActivity view){
        this.view = view;
        this.repository = new BookingRepo();
    }

    public void loadBooking(int id){
        view.showWaitingDialog();
        repository.getBooking(id, RESCallback.CB(resData -> {
            Booking booking = DataHelper.ConvertFromObject(resData.getData(), Booking.class);
            view.onLoadBookingSuccess(booking);
            view.cancleWaitingDialog();
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
        }));
    }
}
