package com.example.hotel_customer.view.booking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.R;
import com.example.hotel_customer.adapter.SpinnerAdapter;
import com.example.hotel_customer.databinding.ActivityBookingHistoryBinding;
import com.example.hotel_customer.model.AdapterModel;
import com.example.hotel_customer.model.BookingStatus;
import com.example.hotel_customer.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryActivity extends BaseActivity {
    ActivityBookingHistoryBinding binding;
    SpinnerAdapter spinnerAdapter;
    List<BookingStatus> statuses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityBookingHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        initUI();
        setEvent();
//        showWaitingDialog("đang đăng nhập");

    }

    private void initUI() {
        statuses = new ArrayList<>();
        statuses.add(BookingStatus.builder().id(1).name("chưa thanh toán").build());
        statuses.add(BookingStatus.builder().id(2).name("đã thanh toán").build());
        statuses.add(BookingStatus.builder().id(3).name("checkin").build());
        statuses.add(BookingStatus.builder().id(4).name("checkout").build());
        statuses.add(BookingStatus.builder().id(5).name("xong").build());
        statuses.add(BookingStatus.builder().id(6).name("đã hủy").build());
        spinnerAdapter = new SpinnerAdapter(statuses);
        binding.spStatus.setAdapter(spinnerAdapter);
    }

    private void setEvent() {
        binding.getRoot().setOnClickListener(v -> {
            Intent booking = new Intent(this, BookingActivity.class);
            startActivity(booking);
        });
    }
}