package com.example.hotel_customer.controller;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.helper.RESCallback;
import com.example.hotel_customer.remote.data.Booking;
import com.example.hotel_customer.remote.repositories.BookingRepo;
import com.example.hotel_customer.view.booking.BookingActivity;
import com.example.hotel_customer.view.booking.InfoBookingActivity;

import java.util.Date;

public class BookingController2 extends BaseController<InfoBookingActivity, BookingRepo> {
    public  BookingController2(InfoBookingActivity view){
        this.view = view;
        this.repository = new BookingRepo();
    }

    public void createBooking(Booking booking){
        view.showWaitingDialog();
        repository.createBooking(booking, RESCallback.CB(resData -> {
                Booking booking1 = DataHelper.ConvertFromObject(resData.getData(), Booking.class);
                view.onCreateBookingSuccess(booking1);
                view.cancleWaitingDialog();
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
        }));
    }
}
