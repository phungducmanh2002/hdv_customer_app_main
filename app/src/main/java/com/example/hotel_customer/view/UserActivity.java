package com.example.hotel_customer.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.controller.UserController;
import com.example.hotel_customer.databinding.ActivityUserBinding;
import com.example.hotel_customer.view.base.BaseActivity;
import com.example.hotel_customer.view.hotel.HomeActivity;

public class UserActivity extends BaseActivity<UserController> {
    public ActivityUserBinding binding;
    public UserActivity(){
        controller = new UserController(this);
    }

    private void setEvent() {
        binding.clickMe.setOnClickListener(v -> {
            controller.showUserInfo();
            Log.d("TEST_BINDING", binding.toString());
        });

        binding.next.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setEvent();
    }
    @Override
    protected void onPause() {
        super.onPause();
        binding = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (binding == null){
            binding = ActivityUserBinding.inflate(getLayoutInflater());
        }
    }

}