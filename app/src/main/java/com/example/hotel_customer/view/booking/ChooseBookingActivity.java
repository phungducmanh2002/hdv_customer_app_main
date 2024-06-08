package com.example.hotel_customer.view.booking;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.R;
import com.example.hotel_customer.adapter.RoomClassAdapter;
import com.example.hotel_customer.databinding.ActivityChooseBookingBinding;
import com.example.hotel_customer.remote.data.RoomClass;
import com.example.hotel_customer.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ChooseBookingActivity extends BaseActivity {
    ActivityChooseBookingBinding binding;
    Dialog dialogChoooseRoom;
    List<RoomClass> roomClasses;
    RoomClassAdapter roomClassAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityChooseBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setEvent();
    }

    private void setEvent() {
        binding.btnAddRoom.setOnClickListener(v -> {
            showDialogSelectRoom();
        });
        
        binding.btnNext.setOnClickListener(v -> {
            handleNext();
        });
    }

    private void handleNext() {
        Intent intent = new Intent(this, InfoBookingActivity.class);
        startActivity(intent);
    }

    private void showDialogSelectRoom() {
        showWaitingDialog();
        if(dialogChoooseRoom == null){
            dialogChoooseRoom= new Dialog(this);
            dialogChoooseRoom.setContentView(R.layout.dialog_choose_room);

            dialogChoooseRoom.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialogChoooseRoom.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_fill_rounded_smoke));

            Spinner spinner = dialogChoooseRoom.findViewById(R.id.spLoaiPhong);
            roomClasses = new ArrayList<>();
            roomClassAdapter = new RoomClassAdapter( roomClasses);
            spinner.setAdapter(roomClassAdapter);

            dialogChoooseRoom.findViewById(R.id.btnClose).setOnClickListener(v -> {
                dialogChoooseRoom.cancel();
            });
        }
        updateRoomClassesData();
    }

    private void updateRoomClassesData() {
        // gọi api lấy dữ liệu room class GET: /hotels/{idHotel}/room-classes
        // sau khi gọi api thành công thì cập nhập data cho room classes và notify change adapter

        // data giả
        roomClasses.clear();
        roomClasses.add(RoomClass.builder().id(1).name("single").build());
        roomClasses.add(RoomClass.builder().id(2).name("single queen").build());
        roomClasses.add(RoomClass.builder().id(3).name("double").build());
        roomClasses.add(RoomClass.builder().id(4).name("double queen").build());
        roomClassAdapter.notifyDataSetChanged();

        dialogChoooseRoom.show();
        cancleWaitingDialog();
    }
}