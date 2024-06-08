package com.example.hotel_customer.controller;

import com.example.hotel_customer.controller.base.BaseController;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.remote.data.Account;
import com.example.hotel_customer.remote.repositories.AccountRepository;
import com.example.hotel_customer.view.account.SignupActivity;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupController extends BaseController<SignupActivity, AccountRepository> {
    public SignupController(SignupActivity activity){
        this.view = activity;
        this.repository = new AccountRepository();
    }

    public void createAccount(String email, String password, String firstName, String lastName, int gender, Date birthDay){

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setGender(gender);
        account.setBirthDay(birthDay);

        repository.createUser(account, new Callback<ResData>() {
            @Override
            public void onResponse(Call<ResData> call, Response<ResData> response) {
                try{
                    if (response.isSuccessful()){
                        if(response.body().getCode() == 201){
                            view.onCreateAccountSuccess(account);
                        }else{
                            throw new Exception(response.body().getMessage());
                        }
                    }
                    else{
                        throw new Exception();
                    }
                }
                catch (Exception ex){
                    view.cancleWaitingDialog();
                    view.showNotifyDialog(ex.getMessage() != null ? ex.getMessage() : "call api failed");
                }
            }

            @Override
            public void onFailure(Call<ResData> call, Throwable t) {
                view.cancleWaitingDialog();
                view.showNotifyDialog(t.getMessage());
            }
        });
    }
}
