package com.example.hotel_customer.model;

import android.graphics.Bitmap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelItemData {
    int id;
    String name;
    float star;
    float minRoomPrice;
    float maxRoomPrice;
    Bitmap image;
}
