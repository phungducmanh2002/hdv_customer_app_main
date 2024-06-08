package com.example.hotel_customer.remote.repositories;

import androidx.annotation.NonNull;

import com.example.hotel_customer.remote.data.User;
import com.example.hotel_customer.remote.repositories.callback.RepositoryCallback;
import com.example.hotel_customer.remote.repositories.interfaces.UserRepository;
import com.example.hotel_customer.remote.service.UserService;

public class UserRepositoryImpl implements UserRepository {
    UserService apiService;

    public UserRepositoryImpl(UserService apiService){
        this.apiService = apiService;
    }

    public void showUserInfo(@NonNull RepositoryCallback callback){
        callback.onsuccess("lyans");
    }
    @Override
    public void createUser(User user, RepositoryCallback callback) {

    }

    @Override
    public void getUser(int id, RepositoryCallback callback) {

    }

    @Override
    public void getUserRole(int id, RepositoryCallback callback) {

    }

    @Override
    public void getUserAvatar(int id, RepositoryCallback callback) {

    }

    @Override
    public void updateUserAvatar(int id, byte[] imageData, RepositoryCallback callback) {

    }

    @Override
    public void getUserAccount(int id, RepositoryCallback callback) {

    }
}
