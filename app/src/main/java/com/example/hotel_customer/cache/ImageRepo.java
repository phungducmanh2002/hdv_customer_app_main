package com.example.hotel_customer.cache;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.hotel_customer.Application;
import com.example.hotel_customer.helper.FileHelper;

public class ImageRepo implements ImageProxy {
    String serverIp = FileHelper.GetConfigValue("SERVER_IP");
    String serverPort = FileHelper.GetConfigValue("SERVER_PORT");
    String imgUrl = String.format("http://%s:%s/res/images", serverIp, serverPort);

    private String getImageUrl(int id) {
        return String.format("%s/%d", imgUrl, id);
    }
    @Override
    public void getImage(int id, OnLoadIMG onLoadIMG) {
        Glide.with(Application.GetContext())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(getImageUrl(id))
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        onLoadIMG.action(resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // Xử lý khi tải hình ảnh bị hủy hoặc giải phóng tài nguyên
                    }
                });
    }
}
