package com.example.hotel_customer.view.hotel;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.R;
import com.example.hotel_customer.adapter.PhotoAdapter;
import com.example.hotel_customer.databinding.ActivityHotelBinding;
import com.example.hotel_customer.helper.FileHelper;
import com.example.hotel_customer.remote.data.Photo;
import com.example.hotel_customer.view.booking.ChooseBookingActivity;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends AppCompatActivity {
    ActivityHotelBinding binding;
    PhotoAdapter photoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHotelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        setEvent();
    }

    private void setEvent() {
        initTemplateImageSlider();
        
        binding.btnBooking.setOnClickListener(v -> {
            handleBooking();
        });
    }

    private void handleBooking() {
        Intent chooseBooking = new Intent(this, ChooseBookingActivity.class);
        startActivity(chooseBooking);
    }

    private void initTemplateImageSlider() {
        photoAdapter = new PhotoAdapter(this, getListPhoto());
        binding.imageSliderViewPager.setAdapter(photoAdapter);
        binding.imageSliderCircleIndicator.setViewPager(binding.imageSliderViewPager);
        photoAdapter.registerDataSetObserver(binding.imageSliderCircleIndicator.getDataSetObserver());
    }

    private List<Photo> getListPhoto() {
        List<Photo> photoList = new ArrayList<>();
        photoList.add(Photo.builder().id(1).bitmap(FileHelper.GetDrawableBitmap(getDrawable(R.drawable.img_hotel))).build());
        photoList.add(Photo.builder().id(2).bitmap(FileHelper.GetDrawableBitmap(getDrawable(R.drawable.img_hotel_ba))).build());
        photoList.add(Photo.builder().id(3).bitmap(FileHelper.GetDrawableBitmap(getDrawable(R.drawable.img_hotel_hai))).build());
        return photoList;
    }
}