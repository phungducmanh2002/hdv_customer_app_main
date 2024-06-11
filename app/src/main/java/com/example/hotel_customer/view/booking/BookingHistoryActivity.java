package com.example.hotel_customer.view.booking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.Application;
import com.example.hotel_customer.R;
import com.example.hotel_customer.adapter.SpinnerAdapter;
import com.example.hotel_customer.controller.BookingHistoryController;
import com.example.hotel_customer.databinding.ActivityBookingHistoryBinding;
import com.example.hotel_customer.model.AdapterModel;
import com.example.hotel_customer.model.BookingStatus;
import com.example.hotel_customer.remote.data.Booking;
import com.example.hotel_customer.remote.data.RoomClass;
import com.example.hotel_customer.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryActivity extends BaseActivity<BookingHistoryController> {
    ActivityBookingHistoryBinding binding;
    SpinnerAdapter spinnerAdapter;
    List<BookingStatus> statuses;
    Integer idStatusCurrent;
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

        this.controller = new BookingHistoryController(this);
        initUI();
        setEvent();
//        showWaitingDialog("đang đăng nhập");

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initUI() {
        statuses = new ArrayList<>();
        statuses.add(BookingStatus.builder().id(1).name("chưa thanh toán").build());
        statuses.add(BookingStatus.builder().id(2).name("đã thanh toán").build());
        statuses.add(BookingStatus.builder().id(3).name("checkin").build());
        statuses.add(BookingStatus.builder().id(4).name("checkout").build());
        statuses.add(BookingStatus.builder().id(5).name("đã hủy").build());
        spinnerAdapter = new SpinnerAdapter(statuses);
        binding.spStatus.setAdapter(spinnerAdapter);
    }

    private void setEvent() {
        binding.spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BookingStatus bookingStatus = (BookingStatus) binding.spStatus.getItemAtPosition(position);
                idStatusCurrent = bookingStatus.getId();
                loadUserBooking(bookingStatus.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void loadUserBooking(int idStatus) {
        controller.loadUserBookings(Application.user.getId(), idStatus);
    }
    public void onLoadBookingsSuccess(Booking[] bookings) {
        binding.bookings.removeAllViews();
        for (Booking b: bookings
             ) {
            com.example.hotel_customer.view.custome.Booking bookingUI = new com.example.hotel_customer.view.custome.Booking(binding.bookings.getContext());
            bookingUI.setDataUI(b);
            bookingUI.setOnView(objects -> {
                Intent booking = new Intent(this, BookingActivity.class);
                booking.putExtra("idBooking", b.getId());
                startActivity(booking);
            });
            bookingUI.setOnCancle(objects -> {
                cancleBooking(b.getId());
            });
            binding.bookings.addView(bookingUI);
        }
    }
    private void cancleBooking(Integer id) {
        controller.cancleBooking(id);
    }
    public void onCancleBookingSuccess() {
        if(idStatusCurrent != null){
            loadUserBooking(idStatusCurrent);
        }
    }
}