package com.example.hotel_customer.controller;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.remote.repositories.BookingHistoryRepo;
import com.example.hotel_customer.view.booking.BookingHistoryActivity;

public class BookingHistoryController
        extends BaseController<BookingHistoryActivity, BookingHistoryRepo> {

    public BookingHistoryController(BookingHistoryActivity view){
        this.view = view;
        this.repository = new BookingHistoryRepo();
    }


}
