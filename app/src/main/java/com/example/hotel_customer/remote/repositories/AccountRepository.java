package com.example.hotel_customer.remote.repositories;

import android.util.Log;

import com.example.hotel_customer.model.AccountLogin;
import com.example.hotel_customer.model.ContainToken;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.model.UserCreate;
import com.example.hotel_customer.remote.RetrofitClient;
import com.example.hotel_customer.remote.data.Account;
import com.example.hotel_customer.remote.repositories.interfaces.Repository;
import com.example.hotel_customer.remote.service.ResService;
import com.example.hotel_customer.remote.service.UserService;

import retrofit2.Callback;

public class AccountRepository implements Repository {
    UserService userService;
    ResService resService;

    public AccountRepository() {
        userService = RetrofitClient.gI().getRetrofit().create(UserService.class);
        resService = RetrofitClient.gI().getRetrofit().create(ResService.class);
    }

    public void createAccount(Account account, Callback<ResData> callback) {
        userService.createAccount(account).enqueue(callback);
    }

    public void getUserToken(AccountLogin accountLogin, Callback<ResData> callback) {
        userService.genUserToken(accountLogin).enqueue(callback);
    }

    public void createUser(int idAccount, String username, Callback<ResData> callback) {
        UserCreate userCreate = new UserCreate();
        userCreate.setUsername(username);
        userCreate.setIdRole(3);
        Log.d("IHKBNOJL", userCreate.toString());
        Log.d("IHKBNOJL", Integer.toString(idAccount));
        userService.createUser(idAccount, userCreate).enqueue(callback);
    }

    public void decodeToken(String token, Callback<ResData> callback) {
        ContainToken containToken = new ContainToken();
        containToken.setToken(token);
        userService.decodeToken(containToken).enqueue(callback);
    }

    public void getAccountByUser(int idUser, Callback<ResData> callback) {
        userService.getAccountByUser(idUser).enqueue(callback);
    }

    public void getUserAvatarId(int idUser, Callback<ResData> callback) {
        resService.getUserAvatar(idUser, 0).enqueue(callback);
    }
}
