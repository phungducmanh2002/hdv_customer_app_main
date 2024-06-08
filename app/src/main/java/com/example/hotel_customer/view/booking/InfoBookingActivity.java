package com.example.hotel_customer.view.booking;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.R;
import com.example.hotel_customer.databinding.ActivityInfoBookingBinding;

public class InfoBookingActivity extends AppCompatActivity {
    ActivityInfoBookingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityInfoBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        setEvent();
    }

    private void setEvent() {
        binding.btnNext.setOnClickListener(v -> {
            handleBooking();
        });
    }

    private void handleBooking() {
        Intent booking = new Intent(this, BookingActivity.class);
        startActivity(booking);
    }
}