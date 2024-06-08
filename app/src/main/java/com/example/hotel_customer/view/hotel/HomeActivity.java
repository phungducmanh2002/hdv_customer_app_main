package com.example.hotel_customer.view.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.Application;
import com.example.hotel_customer.R;
import com.example.hotel_customer.databinding.ActivityHomeBinding;
import com.example.hotel_customer.view.account.ProfileActivity;
import com.example.hotel_customer.view.account.SigninActivity;
import com.example.hotel_customer.view.account.SignupActivity;

public class HomeActivity extends AppCompatActivity {
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
        
        setEvent();
    }

    private void setEvent() {
        initUI();
        
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
        if (Application.isLogined){
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
}