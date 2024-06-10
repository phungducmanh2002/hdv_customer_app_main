package com.example.hotel_customer.controller;

import android.util.Log;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.helper.RESCallback;
import com.example.hotel_customer.remote.data.Room;
import com.example.hotel_customer.remote.data.RoomClass;
import com.example.hotel_customer.remote.repositories.BookingRepo;
import com.example.hotel_customer.view.booking.ChooseBookingActivity;

import java.util.Date;

public class BookingController extends BaseController<ChooseBookingActivity, BookingRepo> {
    public BookingController(ChooseBookingActivity view) {
        this.view = view;
        this.repository = new BookingRepo();
    }

    public void loadHotelRoomClasses(int idHotel) {
        view.showWaitingDialog();
        repository.getHotelRoomClasses(idHotel, RESCallback.CB(resData -> {
            RoomClass[] roomClasses = DataHelper.ConvertFromObjectArr(resData.getData(), RoomClass[].class);
            view.onGetHotelRoomClassesSuccess(roomClasses);
            view.cancleWaitingDialog();
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);

        }));
    }

    public void loadHotelRoomCLassRooms(int idHotel, Integer idRoomClass, Date checkin, Date checkout) {
        view.showWaitingDialog();
        repository.getHotelRoomClassRooms(
                idHotel,
                idRoomClass,
                checkin,
                checkout,
                RESCallback.CB(resData -> {
                    Room[] rooms = DataHelper.ConvertFromObjectArr(resData.getData(), Room[].class);
                    view.onGetHotelRoomClassRoomsSuccess(rooms);
                    view.cancleWaitingDialog();
                }, message -> {
                    view.cancleWaitingDialog();
                    view.showNotifyDialog(message);
                }));
    }
}
