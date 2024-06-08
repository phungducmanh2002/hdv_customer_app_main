package com.example.hotel_customer.controller;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.remote.repositories.callback.RepositoryCallback;
import com.example.hotel_customer.remote.repositories.UserRepositoryImpl;
import com.example.hotel_customer.view.UserActivity;

public class UserController extends BaseController<UserActivity, UserRepositoryImpl> {
    public UserController(UserActivity view){
        repository = new UserRepositoryImpl(null);
        this.view = view;
    }
    public void showUserInfo(){
        repository.showUserInfo(new RepositoryCallback() {
            @Override
            public void onsuccess(Object object) {
                view.binding.username.setText((String)object);
            }

            @Override
            public void onfailed(Object object) {

            }
        });
    }
}
