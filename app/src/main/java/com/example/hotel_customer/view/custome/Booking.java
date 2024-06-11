package com.example.hotel_customer.view.custome;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.hotel_customer.databinding.CustomeChooseRoomBinding;
import com.example.hotel_customer.databinding.CustomeItemBookingBinding;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.interfaces.OnEvent;
import com.example.hotel_customer.remote.data.Room;

public class Booking extends LinearLayout implements CustomeView {
    CustomeItemBookingBinding binding;
    Integer idRoom;

    public Booking(Context context) {
        super(context);
        init(context, null);
    }

    public Booking(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Booking(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public Booking(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        binding = CustomeItemBookingBinding.inflate(LayoutInflater.from(context), this, true);
        setEvent();
    }

    private void setEvent() {
    }

    public void setDataUI(com.example.hotel_customer.remote.data.Booking booking) {
        if(booking == null){
            return;
        }
        binding.tvId.setText(Integer.toString(booking.getId()));
        binding.tvStatus.setText(Integer.toString(booking.getStatus()));
        binding.tvCheckin.setText(DataHelper.Date2String(booking.getCheckin()));
        binding.tvCheckout.setText(DataHelper.Date2String(booking.getCheckout()));

        if (booking.getStatus() < 3){
            binding.btnCancle.setVisibility(VISIBLE);
        }
        else{
            binding.btnCancle.setVisibility(GONE);
        }
    }

    public void setOnCancle(OnEvent onCancle){
        binding.btnCancle.setOnClickListener(v -> {
            onCancle.action();
        });
    }

    public void setOnView(OnEvent onView){
        binding.btnView.setOnClickListener(v -> {
            onView.action();
        });
    }

}
