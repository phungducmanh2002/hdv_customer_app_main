package com.example.hotel_customer.view.custome;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.hotel_customer.databinding.CustomeHotelItemBinding;
import com.example.hotel_customer.databinding.CustomeInputBinding;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.interfaces.OnEvent;
import com.example.hotel_customer.model.HotelItemData;
import com.example.hotel_customer.view.hotel.HotelActivity;

public class HotelItem extends LinearLayout implements CustomeView {
    CustomeHotelItemBinding binding;
    OnEvent onEvent = new OnEvent() {
        @Override
        public void action(Object... objects) {
            Intent hotel = new Intent(getContext(), HotelActivity.class);
            getContext().startActivity(hotel);
        }
    };
    Integer idHotel;

    public HotelItem(Context context) {
        super(context);
        init(context, null);
    }

    public HotelItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HotelItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public HotelItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        binding = CustomeHotelItemBinding.inflate(LayoutInflater.from(context), this, true);
        setEvent();
    }

    private void setEvent() {
        binding.btnView.setOnClickListener(v -> {
            onEvent.action();
        });
    }

    public void setDataUI(HotelItemData hotelItemData) {
        if (hotelItemData == null)
            return;
        idHotel = hotelItemData.getId();
        String minRoomPrice = DataHelper.GetMoney(hotelItemData.getMinRoomPrice());
        String maxRoomPrice = DataHelper.GetMoney(hotelItemData.getMaxRoomPrice());
        String roomPriceRange = String.format("%s - %s", minRoomPrice, maxRoomPrice);

        binding.image.setImageBitmap(hotelItemData.getImage());
        binding.tvHotelName.setText(hotelItemData.getName());
        binding.tvStar.setText(Float.toString(hotelItemData.getStar()));
        binding.tvRoomPriceRange.setText(roomPriceRange);
    }

    public void setOnEvent(OnEvent onClick){
        if(onClick != null){
            binding.btnView.setOnClickListener(v -> {
                onClick.action(idHotel);
            });
        }
    }
}
