package com.example.hotel_customer.view.custome;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.hotel_customer.databinding.CustomeChooseRoomBinding;
import com.example.hotel_customer.databinding.CustomeRoomBookBinding;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.interfaces.OnEvent;
import com.example.hotel_customer.remote.data.BookingRoom;
import com.example.hotel_customer.remote.data.Room;

public class RoomBook extends LinearLayout implements CustomeView {
    CustomeRoomBookBinding binding;
    Integer idRoom;

    public RoomBook(Context context) {
        super(context);
        init(context, null);
    }

    public RoomBook(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RoomBook(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public RoomBook(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        binding = CustomeRoomBookBinding.inflate(LayoutInflater.from(context), this, true);
        setEvent();
    }

    private void setEvent() {
    }

    public void setDataUI(BookingRoom room) {
        if (room == null)
            return;
        idRoom = room.getId();
        String roomPrice = DataHelper.GetMoney(room.getRoomPrice());

        binding.tvRoomName.setText(Integer.toString(room.getIdRoom()));
        binding.tvRoomClassName.setText(room.getRoomClassName());
        binding.tvRoomPrice.setText(roomPrice);
    }
}
