package com.example.hotel_customer.controller;

import android.util.Log;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.model.AccountLogin;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.data.Account;
import com.example.hotel_customer.remote.repositories.AccountRepository;
import com.example.hotel_customer.view.account.SigninActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninController extends BaseController<SigninActivity, AccountRepository> {
    public SigninController(SigninActivity view){
        this.view = view;
        this.repository = new AccountRepository();
    }

    public void signin(String email, String password){
        AccountLogin accountLogin = AccountLogin.builder().email(email).password(password).idRole(3).build();

        view.showWaitingDialog();
        repository.getUserToken(accountLogin, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                try {
                    if (response.isSuccessful()){
                        if (response.body().getCode() == 200){
                            String token = response.body().getData().toString();
                            view.onGenUserTokenSuccess(token);
                        }
                        else if(response.body().getCode() == 404){
                            Account account = DataHelper.ConvertFromObject(response.body().getData(), Account.class);
                            view.onUserNotFound(account);
                        }
                        else{
                            throw new Exception(response.body().getMessage());
                        }
                    }
                    else{
                        throw new Exception("call api failed");
                    }
                }
                catch (Exception ex){
                    view.cancleWaitingDialog();
                    view.showNotifyDialog(ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable t) {
                view.cancleWaitingDialog();
                view.showNotifyDialog(t.getMessage());
            }
        });
    }

    public void getUser(String token) {
    }
}
