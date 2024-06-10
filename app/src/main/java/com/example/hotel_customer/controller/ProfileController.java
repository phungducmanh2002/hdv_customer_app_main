package com.example.hotel_customer.controller;

import com.example.hotel_customer.cache.ImageRepoCache;
import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.helper.RESCallback;
import com.example.hotel_customer.model.Photo;
import com.example.hotel_customer.remote.data.Account;
import com.example.hotel_customer.remote.data.User;
import com.example.hotel_customer.remote.repositories.AccountRepository;
import com.example.hotel_customer.view.account.ProfileActivity;

public class ProfileController extends BaseController<ProfileActivity, AccountRepository> {
    ImageRepoCache imageRepoCache;

    public ProfileController(ProfileActivity view) {
        this.view = view;
        this.repository = new AccountRepository();
        this.imageRepoCache = ImageRepoCache.gI();
    }

    public void loadUser(String token) {
        view.showWaitingDialog();
        repository.decodeToken(token, RESCallback.CB(resData -> {
            User user = DataHelper.ConvertFromObject(resData.getData(), User.class);
            view.onLoadUserSuccess(user);
            view.cancleWaitingDialog();
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
        }));
    }

    public void loadAccount(int idUser) {
        view.showWaitingDialog();
        repository.getAccountByUser(idUser, RESCallback.CB(resData -> {
            Account account = DataHelper.ConvertFromObject(resData.getData(), Account.class);
            view.onLoadAccountSuccess(account);
            view.cancleWaitingDialog();
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
        }));
    }

    public void loadUserAvatar(Integer idUser) {
        view.showWaitingDialog();
        repository.getUserAvatarId(idUser, RESCallback.CB(resData -> {
            Photo photo = DataHelper.ConvertFromObject(resData.getData(), Photo.class);
            imageRepoCache.getImage(photo.getId(), bitmap -> {
                view.onLoadUserAvatarSuccess(bitmap);
            });
            view.cancleWaitingDialog();
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
        }));
    }
}
