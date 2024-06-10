package com.example.hotel_customer.controller;

import android.util.Log;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.helper.RESCallback;
import com.example.hotel_customer.model.AccountLogin;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.model.UserCreate;
import com.example.hotel_customer.remote.data.Account;
import com.example.hotel_customer.remote.data.User;
import com.example.hotel_customer.remote.repositories.AccountRepository;
import com.example.hotel_customer.view.account.CreateCustomerActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserController extends BaseController<CreateCustomerActivity, AccountRepository> {
    public CreateUserController(CreateCustomerActivity view){
        this.view = view;
        this.repository = new AccountRepository();
    }
    public void createCustomer(int idAccount, String username){
        view.showWaitingDialog();
        repository.createUser(idAccount, username, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                try{
                    if (response.isSuccessful()){
                        if(response.body().getCode() == 200 || response.body().getCode() == 201){
                            Object o = response.body().getData();
                            Log.d("FGYBHUNJKM",  o.toString());
                            User user = DataHelper.convert(o, User.class);
                            Log.d("FGYBHUNJKM", user.toString());
                            view.onCreateUserSuccess(user);
                        }
                        else {
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
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable t) {
                t.printStackTrace();
                view.cancleWaitingDialog();
                view.showNotifyDialog(t.getMessage());
            }
        });
    }
    public void signin(String email, String password){
        AccountLogin accountLogin = AccountLogin.builder().email(email).password(password).idRole(3).build();

        view.showWaitingDialog();
        repository.getUserToken(accountLogin, RESCallback.CB(resData -> {
            String token = resData.getData().toString();
            view.onGenUserTokenSuccess(token);
        }, message -> {
            view.cancleWaitingDialog();
            view.showNotifyDialog(message);
        }));
    }
}
