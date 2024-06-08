package com.example.hotel_customer.remote.repositories.interfaces;

import com.example.hotel_customer.remote.data.User;
import com.example.hotel_customer.remote.repositories.callback.RepositoryCallback;

public interface UserRepository extends Repository{
    public void createUser(User user, RepositoryCallback callback);
    public void getUser(int id, RepositoryCallback callback);
    public void getUserRole(int id, RepositoryCallback callback);
    public void getUserAvatar(int id, RepositoryCallback callback);
    public void updateUserAvatar(int id, byte[] imageData, RepositoryCallback callback);
    public void getUserAccount(int id, RepositoryCallback callback);
}
