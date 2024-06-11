package com.example.hotel_customer.view.booking;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.Application;
import com.example.hotel_customer.R;
import com.example.hotel_customer.controller.BookingController2;
import com.example.hotel_customer.databinding.ActivityInfoBookingBinding;
import com.example.hotel_customer.remote.data.Booking;
import com.example.hotel_customer.view.base.BaseActivity;

public class InfoBookingActivity extends BaseActivity<BookingController2> {
    ActivityInfoBookingBinding binding;
    Booking booking;

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

        this.controller = new BookingController2(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            booking = getIntent().getSerializableExtra("booking", Booking.class);
            setEvent();
            Log.d("OHJNOIHLJIO", booking.toString());
        } else {
            showNotifyDialog("Phiên bản không hỗ trợ!");
        }
    }

    private void setEvent() {
        binding.btnNext.setOnClickListener(v -> {
            handleBooking();
        });
    }

    private void handleBooking() {
        if (isValidData()) {
            booking.setPersonName(binding.ipPersonName.getInputText());
            booking.setPersonPhone(binding.ipPersonPhone.getInputText());
            booking.setNote(binding.tvNote.getText().toString());
            if (Application.user != null){
                booking.setIdUser(Application.user.getId());
            }
            controller.createBooking(booking);
        }

    }

    private boolean isValidData() {
        if (binding.ipPersonName.getInputText().equals("")) {
            binding.ipPersonName.setInputFocus();
            binding.ipPersonName.setInputWarn("vui lòng nhập tên của bạn");
            return false;
        } else {
            binding.ipPersonName.setInputWarn(null);
        }

        if (binding.ipPersonPhone.getInputText().equals("")) {
            binding.ipPersonPhone.setInputFocus();
            binding.ipPersonPhone.setInputWarn("vui lòng nhập số điện thoại");
            return false;
        } else {
            binding.ipPersonPhone.setInputWarn(null);
        }

        return true;
    }
    public void onCreateBookingSuccess(Booking booking1) {
        Intent booking = new Intent(this, BookingActivity.class);
        booking.putExtra("idBooking", booking1.getId());
        startActivity(booking);
        finish();
    }
}