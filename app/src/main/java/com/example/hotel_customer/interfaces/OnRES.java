package com.example.hotel_customer.interfaces;

import com.example.hotel_customer.model.ResData;

public interface OnRES {
    public interface Success{
        public void action(ResData resData);
    }
    public interface Failed{
        public void action(String message);
    }
}
