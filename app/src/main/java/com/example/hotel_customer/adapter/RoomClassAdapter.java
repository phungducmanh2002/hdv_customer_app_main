package com.example.hotel_customer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotel_customer.R;
import com.example.hotel_customer.remote.data.RoomClass;

import java.util.List;

public class RoomClassAdapter extends BaseAdapter {
    private List<RoomClass> roomClasses;
    public RoomClassAdapter(List<RoomClass> roomClasses) {
        this.roomClasses = roomClasses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.spinner_item_room_class, null);
        }

        RoomClass currentRoomClass = roomClasses.get(position);
        TextView textView = convertView.findViewById(R.id.name);
        textView.setText(currentRoomClass.getName());

        return convertView;
    }

    @Nullable
    @Override
    public RoomClass getItem(int position) {
        if(this.roomClasses == null)
            return null;
        return this.roomClasses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
       if(this.roomClasses == null)
           return  0;
       return roomClasses.size();
    }
}
