package com.example.hotel_customer.controller;

import android.util.Log;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.helper.RESCallback;
import com.example.hotel_customer.remote.repositories.ResRepo;
import com.example.hotel_customer.view.account.EditAvatarActivity;

public class EditAvatarController extends BaseController<EditAvatarActivity, ResRepo> {
    public EditAvatarController(EditAvatarActivity view){
        this.view = view;
        this.repository = new ResRepo();
    }

    public void updateUserAvatar(int idUser, byte[] data){
        view.showWaitingDialog();
        repository.updateUserAvatar(idUser, data, RESCallback.CB(resData -> {
            Log.d("ONUPDATEAVATAR", "UPDATE AVATAR SUCCESS");
            view.onUpdateUserAvatarSuccess();
            view.cancleWaitingDialog();
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
        }));
    }
}
