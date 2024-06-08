package com.example.hotel_customer.view.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.Application;
import com.example.hotel_customer.R;
import com.example.hotel_customer.databinding.ActivitySigninBinding;
import com.example.hotel_customer.view.hotel.HomeActivity;

public class SigninActivity extends AppCompatActivity {
    ActivitySigninBinding binding;
    String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        
        initUI();
        setEvent();
    }

    private void initUI() {
        if(email != null && password != null){
            binding.ipEmail.setInputText(email);
            binding.ipPassword.setInputText(password);
        }
    }

    private void setEvent() {
        binding.btnSignin.setOnClickListener(v -> {
            handleSignin();
        });
    }

    private void handleSignin() {
        Application.isLogined = true;
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
    }
}