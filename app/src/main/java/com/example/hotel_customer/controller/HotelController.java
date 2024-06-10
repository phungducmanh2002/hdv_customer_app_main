package com.example.hotel_customer.controller;

import android.util.Log;

import com.example.hotel_customer.Application;
import com.example.hotel_customer.R;
import com.example.hotel_customer.cache.ImageRepoCache;
import com.example.hotel_customer.cache.OnLoadIMG;
import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.helper.RESCallback;
import com.example.hotel_customer.model.Photo;
import com.example.hotel_customer.remote.data.Hotel;
import com.example.hotel_customer.remote.data.ResImage;
import com.example.hotel_customer.remote.repositories.HotelRepo;
import com.example.hotel_customer.remote.service.ResService;
import com.example.hotel_customer.view.hotel.HotelActivity;

import java.util.ArrayList;
import java.util.List;

public class HotelController extends BaseController<HotelActivity, HotelRepo> {
    ImageRepoCache imageRepoCache;
    public HotelController(HotelActivity view) {
        this.view = view;
        this.repository = new HotelRepo();
        this.imageRepoCache = ImageRepoCache.gI();
    }
    public void loadHotelsNear(int idHotel){
        repository.getHotelsNear(idHotel, RESCallback.CB(resData -> {
            Hotel[] hotels = DataHelper.ConvertFromObjectArr(resData.getData(), Hotel[].class);
            view.renderHotelsNear(hotels);
        }, message -> {
        }));
    }
    public void loadHotel(int idHotel) {
        repository.getHotel(idHotel, RESCallback.CB(resData -> {
            Hotel hotel = DataHelper.ConvertFromObject(resData.getData(), Hotel.class);
            view.renderHotel(hotel);
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
        }));
        loadHotelImagesId(idHotel);
        loadHotelsNear(idHotel);
    }
    public void loadHotelImagesId(int idHotel) {
        repository.getHotelImagesId(idHotel, RESCallback.CB(resData -> {
            Integer[] ids = DataHelper.ConvertFromObjectArr(resData.getData(), Integer[].class);
            if(ids.length == 0){
                view.addDefaultPhoto();
                return;
            }
            for (Integer id : ids) {
                imageRepoCache.getImage(id, bitmap -> {
                    Photo photo = new Photo();
                    photo.setBitmap(bitmap);
                    view.addPhoto(photo);
                });
            }
        }, message -> {
        }));
    }

    public void loadHotelAvatar(int idHotel, OnLoadIMG onLoadIMG){
        repository.getHotelIdAvatar(idHotel, RESCallback.CB(resData -> {
            ResImage image = DataHelper.ConvertFromObject(resData.getData(), ResImage.class);
            imageRepoCache.getImage(image.getId(), bitmap -> {
                onLoadIMG.action(bitmap);
            });
        }, message -> {
            view.cancleWaitingDialog();
            onLoadIMG.action(DataHelper.drawableToBitmap(Application.GetContext().getDrawable(R.drawable.img_hotel_hai)));
        }));
    }
}
