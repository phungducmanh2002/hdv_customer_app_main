package com.example.hotel_customer.view.booking;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.R;
import com.example.hotel_customer.controller.BookingController2;
import com.example.hotel_customer.controller.BookingController3;
import com.example.hotel_customer.databinding.ActivityBookingBinding;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.remote.data.Booking;
import com.example.hotel_customer.remote.data.BookingRoom;
import com.example.hotel_customer.view.base.BaseActivity;
import com.example.hotel_customer.view.custome.RoomBook;

public class BookingActivity extends BaseActivity<BookingController3> {
    ActivityBookingBinding binding;
    int idBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding  = ActivityBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        idBooking = getIntent().getIntExtra("idBooking", -1);
        controller = new BookingController3(this);
        setEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(idBooking != -1){
            controller.loadBooking(idBooking);
        }
    }

    private void setEvent() {
        binding.btnQRCode.setOnClickListener(v -> {
            handleShowQRCode();
        });
    }

    private void handleShowQRCode() {
        Intent qrThanhToan = new Intent(this, QRCodeBookingActivity.class);
        startActivity(qrThanhToan);
    }

    public void onLoadBookingSuccess(Booking booking) {
        binding.tvIdBooking.setText(Integer.toString(booking.getId()));
        binding.tvCheckin.setText(DataHelper.Date2String(booking.getCheckin()));
        binding.tvCheckout.setText(DataHelper.Date2String(booking.getCheckout()));
        if(booking.getStatus() == 1){
            binding.tvStatus.setText("chưa thanh toán");
        }
        if(booking.getStatus() == 2){
            binding.tvStatus.setText("đã thanh toán");
        }
        if(booking.getStatus() == 3){
            binding.tvStatus.setText("checkin");
        }
        if(booking.getStatus() == 4){
            binding.tvStatus.setText("checkout");
        }
        if(booking.getStatus() == 5){
            binding.tvStatus.setText("đã hủy");
        }

        float totalMoney = 0.0f;
        binding.rooms.removeAllViews();
        for (BookingRoom r: booking.getRooms()
             ) {
            RoomBook roomBook = new RoomBook(binding.rooms.getContext());
            roomBook.setDataUI(r);
            binding.rooms.addView(roomBook);
            totalMoney += r.getRoomPrice();
        }
        binding.tvTotalMoney.setText(DataHelper.GetMoney(totalMoney));
    }
}