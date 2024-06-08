package com.example.hotel_customer.remote.repositories.callback;

import com.example.hotel_customer.interfaces.OnEvent;

import lombok.Setter;

public class BaseRepositoryCallback implements RepositoryCallback {
    @Setter
    OnEvent onFailedEvent;

    @Setter
    OnEvent onSuccessEvent;
    @Override
    public void onsuccess(Object object) {
        if(onSuccessEvent != null){
            onSuccessEvent.action(object);
        }
    }

    @Override
    public void onfailed(Object object) {
        if(onFailedEvent != null){
            onFailedEvent.action(object);
        }
    }
}
