package com.example.hotel_customer.remote.service;

import com.example.hotel_customer.model.AccountLogin;
import com.example.hotel_customer.model.ContainToken;
import com.example.hotel_customer.model.ResData;
import com.example.hotel_customer.model.UserCreate;
import com.example.hotel_customer.remote.data.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("/user/accounts")
    public Call<ResData> createAccount(@Body Account account);

    @POST("/user/accounts/{idAccount}/users")
    public Call<ResData> createUser(@Path("idAccount") int idAccount, @Body UserCreate userCreate);

    @POST("/user/auth/gen-user-token")
    public Call<ResData> genUserToken(@Body AccountLogin accountLogin);

    @POST("/user/auth/decode-token")
    public Call<ResData> decodeToken(@Body ContainToken token);

    @GET("/user/users/{idUser}/accounts")
    public Call<ResData> getAccountByUser(@Path("idUser") int idUser);


}
