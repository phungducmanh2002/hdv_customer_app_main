package com.example.hotel_customer.cache;

import android.graphics.Bitmap;

import com.example.hotel_customer.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class ImageRepoCache implements ImageProxy {
    private static  ImageRepoCache instance;
    public static ImageRepoCache gI(){
        if (instance == null){
            instance = new ImageRepoCache();
        }
        return instance;
    }
    public static void reset(){
        instance = null;
    }

    List<Photo> photos = new ArrayList<>();
    int maxSize = 50;
    ImageRepo imageRepo = new ImageRepo();

    private ImageRepoCache(){}
    @Override
    public void getImage(int id, OnLoadIMG onLoadIMG) {
        Photo photo = getPhotoCache(id);
        if(photo == null){
            imageRepo.getImage(id, bitmap -> {
                cachePhoto(Photo.builder().id(id).bitmap(bitmap).build());
                onLoadIMG.action(bitmap);
            });
        }
        else{
            onLoadIMG.action(photo.getBitmap());
        }
    }
    public Photo getPhotoCache(int id){
        for (int i = 0; i < photos.size(); i++) {
            if (photos.get(i).getId() == id) {
                Photo photo = photos.get(i);
                // Dịch mảng qua phải 1 lần từ vị trí 0 đến vị trí của hình ảnh vừa tìm được
                for (int j = i; j > 0; j--) {
                    photos.set(j, photos.get(j - 1));
                }
                // Đưa hình ảnh lên vị trí 0
                photos.set(0, photo);
                return photo;
            }
        }
        return null;
    }
    public void  cachePhoto(Photo photo){
        // Đưa hình ảnh vào đầu mảng
        photos.add(0, photo);
        // Nếu kích thước mảng vượt quá maxSize thì xóa phần tử cuối cùng
        if (photos.size() > maxSize) {
            photos.remove(photos.size() - 1);
        }
    }
}
