package com.example.hotel_customer.view.custome;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.hotel_customer.R;
import com.example.hotel_customer.databinding.CustomeChooseRoomBinding;
import com.example.hotel_customer.databinding.CustomeHotelItemBinding;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.interfaces.OnEvent;
import com.example.hotel_customer.remote.data.Hotel;
import com.example.hotel_customer.remote.data.Room;
import com.example.hotel_customer.view.hotel.HotelActivity;

public class ChooseRoom extends LinearLayout implements CustomeView {
    CustomeChooseRoomBinding binding;
    Integer idRoom;

    public ChooseRoom(Context context) {
        super(context);
        init(context, null);
    }

    public ChooseRoom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChooseRoom(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ChooseRoom(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        binding = CustomeChooseRoomBinding.inflate(LayoutInflater.from(context), this, true);
        setEvent();
    }

    private void setEvent() {
    }

    public void setDataUI(Room room) {
        if (room == null)
            return;
        idRoom = room.getId();
        String roomPrice = DataHelper.GetMoney(room.getRoomPrice());

        binding.tvRoomName.setText(room.getName());
        binding.tvRoomPrice.setText(roomPrice);
    }

    public void setOnEvent(OnEvent onClick){
        if(onClick != null){
            binding.btnAction.setOnClickListener(v -> {
                onClick.action(idRoom);
            });
        }
    }

    public void setActionBackground(Drawable drawable){
        binding.btnAction.setBackgroundDrawable(drawable);
    }

    public void setActoinText(String text){
        binding.btnAction.setText(text);
    }
}
