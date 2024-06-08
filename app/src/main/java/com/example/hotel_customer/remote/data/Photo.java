package com.example.hotel_customer.remote.data;

import android.graphics.Bitmap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photo {
    int id;
    Bitmap bitmap;
}
