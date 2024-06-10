package com.example.hotel_customer.view.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.Application;
import com.example.hotel_customer.controller.HomeController;
import com.example.hotel_customer.databinding.ActivityHomeBinding;
import com.example.hotel_customer.remote.data.Hotel;
import com.example.hotel_customer.view.account.ProfileActivity;
import com.example.hotel_customer.view.account.SigninActivity;
import com.example.hotel_customer.view.account.SignupActivity;
import com.example.hotel_customer.view.base.BaseActivity;
import com.example.hotel_customer.view.custome.HotelItem;
import com.example.hotel_customer.view.custome.HotelPanel;

public class HomeActivity extends BaseActivity<HomeController> {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initData();
        setEvent();
    }

    private void initData() {
        this.controller = new HomeController(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUI();
        loadHotel();
    }

    private void loadHotel() {
        this.controller.loadHotelData();
    }

    private void setEvent() {

        binding.profile.setOnClickListener(v -> {
            handleOpenProfile();
        });

        binding.btnSignup.setOnClickListener(v -> {
            handleSignup();
        });

        binding.btnSignin.setOnClickListener(v -> {
            handleSignin();
        });
    }

    private void handleSignin() {
        Intent signin = new Intent(this, SigninActivity.class);
        startActivity(signin);
    }

    private void handleSignup() {
        Intent signup = new Intent(this, SignupActivity.class);
        startActivity(signup);
    }

    private void initUI() {
        if (Application.token != null){
            initUILogined();
        }
        else{
            initUINoLogin();
        }
    }

    private void initUINoLogin() {
        binding.header.setVisibility(View.VISIBLE);
        binding.headerLogined.setVisibility(View.GONE);
    }

    private void initUILogined() {
        binding.header.setVisibility(View.GONE);
        binding.headerLogined.setVisibility(View.VISIBLE);
    }

    private void handleOpenProfile() {
        Intent profile = new Intent(this, ProfileActivity.class);
        startActivity(profile);
    }

    // api response

    public void renderHotelsNewest(Hotel[] hotels){
        binding.hotelsNewest.removeAllViews();
        for (Hotel hotel: hotels) {
            HotelItem hotelItem = new HotelItem(binding.hotelsNewest.getContext());
            hotelItem.setDataUI(hotel);
            controller.loadHotelAvatar(hotel.getId(), bitmap -> {
                hotelItem.setAvatar(bitmap);
            });
            binding.hotelsNewest.addView(hotelItem);
        }
    }
    public void renderHotelsBest(Hotel[] hotels){
        binding.hotelsBest.removeAllViews();
        for (Hotel hotel: hotels) {
            HotelPanel hotelPanel = new HotelPanel(binding.hotelsBest.getContext());
            hotelPanel.setDataUI(hotel);
            controller.loadHotelAvatar(hotel.getId(), bitmap -> {
                hotelPanel.setAvatar(bitmap);
            });
            binding.hotelsBest.addView(hotelPanel);
        }
    }
}