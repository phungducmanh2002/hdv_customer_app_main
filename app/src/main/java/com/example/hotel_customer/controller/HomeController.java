package com.example.hotel_customer.controller;

import android.widget.ImageView;

import com.example.hotel_customer.Application;
import com.example.hotel_customer.R;
import com.example.hotel_customer.cache.ImageRepoCache;
import com.example.hotel_customer.cache.OnLoadIMG;
import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.helper.RESCallback;
import com.example.hotel_customer.remote.data.Hotel;
import com.example.hotel_customer.remote.data.ResImage;
import com.example.hotel_customer.remote.repositories.HotelRepo;
import com.example.hotel_customer.view.hotel.HomeActivity;

public class HomeController extends BaseController<HomeActivity, HotelRepo> {
    ImageRepoCache imageRepoCache;
    public HomeController(HomeActivity view){
        this.view = view;
        this.repository = new HotelRepo();
        this.imageRepoCache = ImageRepoCache.gI();
    }
    public void loadHotelData(){
        loadHotelNewest();
        loadHotelBest();
    }
    public void loadHotelNewest(){
        view.showWaitingDialog();
        this.repository.getAllHotelsNewest(1, RESCallback.CB(resData -> {
            Hotel[] hotels = DataHelper.ConvertFromObjectArr(resData.getData(), Hotel[].class);
            view.cancleWaitingDialog();
            view.renderHotelsNewest(hotels);
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
        }));
    }
    public void loadHotelBest(){
        view.showWaitingDialog();
        this.repository.getAllHotelsBest(1, RESCallback.CB(resData -> {
            Hotel[] hotels = DataHelper.ConvertFromObjectArr(resData.getData(), Hotel[].class);
            view.cancleWaitingDialog();
            view.renderHotelsBest(hotels);
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
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
