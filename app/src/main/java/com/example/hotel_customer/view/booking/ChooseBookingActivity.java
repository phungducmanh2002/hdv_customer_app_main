package com.example.hotel_customer.view.booking;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.R;
import com.example.hotel_customer.adapter.RoomClassAdapter;
import com.example.hotel_customer.controller.BookingController;
import com.example.hotel_customer.databinding.ActivityChooseBookingBinding;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.remote.data.Booking;
import com.example.hotel_customer.remote.data.Room;
import com.example.hotel_customer.remote.data.RoomClass;
import com.example.hotel_customer.view.base.BaseActivity;
import com.example.hotel_customer.view.custome.ChooseRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ChooseBookingActivity extends BaseActivity<BookingController> {
    ActivityChooseBookingBinding binding;
    Dialog dialogChoooseRoom;
    List<RoomClass> roomClasses;
    RoomClassAdapter roomClassAdapter;
    int idHotel;
    List<Room> roomsSelected = new ArrayList<>();
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

        controller = new BookingController(this);
        idHotel = getIntent().getIntExtra("idHotel", -1);
        setEvent();
    }
    private void setEvent() {
        binding.pdCheckin.setOnAfterPickDate(objects -> {
            Date newDate = (Date) objects[0];
            Date checkOutDate = binding.pdCheckout.getPickDateDate();
            if(checkOutDate == null){
                return;
            }
            if(checkOutDate.before(newDate)){
                binding.pdCheckout.setPickDateDate(DataHelper.PlusDay(newDate, 1));
            }
            binding.btnAddRoom.setEnabled(true);
            roomsSelected.clear();
            reRenderRoomsSelected();
        });

        binding.pdCheckout.setOnAfterPickDate(objects -> {
            Date newDate = (Date) objects[0];
            Date checkinDate = binding.pdCheckin.getPickDateDate();
            if(checkinDate == null){
                return;
            }
            if(checkinDate.after(newDate)){
                binding.pdCheckin.setPickDateDate(DataHelper.MinusDay(newDate, 1));
            }
            binding.btnAddRoom.setEnabled(true);
            roomsSelected.clear();
            reRenderRoomsSelected();
        });

        binding.btnAddRoom.setOnClickListener(v -> {
            showDialogSelectRoom();
        });
        
        binding.btnNext.setOnClickListener(v -> {
            handleNext();
        });
    }
    private void handleNext() {
        List<Integer> idRooms = new ArrayList<>();
        for (Room r :roomsSelected
             ) {
            idRooms.add(r.getId());
        }
        Integer[] ids = new Integer[idRooms.size()];
        ids = idRooms.toArray(ids);
        Booking booking = new Booking();
        booking.setCheckin(binding.pdCheckin.getPickDateDate());
        booking.setCheckout(binding.pdCheckout.getPickDateDate());
        booking.setIdRooms(ids);

        Intent intent = new Intent(this, InfoBookingActivity.class);
        intent.putExtra("booking", booking);
        startActivity(intent);
        finish();
    }
    private void showDialogSelectRoom() {
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

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    RoomClass roomClass = (RoomClass) spinner.getItemAtPosition(position);
                    Date checkin = binding.pdCheckin.getPickDateDate();
                    Date checkout = binding.pdCheckout.getPickDateDate();
                    controller.loadHotelRoomCLassRooms(idHotel, roomClass.getId(), checkin, checkout);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        updateRoomClassesData();
    }
    private void updateRoomClassesData() {
        controller.loadHotelRoomClasses(idHotel);
        // gọi api lấy dữ liệu room class GET: /hotels/{idHotel}/room-classes
        // sau khi gọi api thành công thì cập nhập data cho room classes và notify change adapter
//
//        // data giả
//        roomClasses.clear();
//        roomClasses.add(RoomClass.builder().id(1).name("single").build());
//        roomClasses.add(RoomClass.builder().id(2).name("single queen").build());
//        roomClasses.add(RoomClass.builder().id(3).name("double").build());
//        roomClasses.add(RoomClass.builder().id(4).name("double queen").build());
//        roomClassAdapter.notifyDataSetChanged();
//
//        dialogChoooseRoom.show();
    }
    public void onGetHotelRoomClassesSuccess(RoomClass[] roomClasses) {
        this.roomClasses.clear();
        this.roomClasses.addAll(Arrays.stream(roomClasses).collect(Collectors.toList()));
        this.roomClassAdapter.notifyDataSetChanged();
        dialogChoooseRoom.show();
    }
    public void onGetHotelRoomClassRoomsSuccess(Room[] rooms) {
        LinearLayout parent = dialogChoooseRoom.findViewById(R.id.rooms);
        parent.removeAllViews();
        for (Room room: rooms
             ) {
            if (roomsSelected.contains(room)){
                continue;
            }
            ChooseRoom chooseRoom = new ChooseRoom(parent.getContext());
            chooseRoom.setDataUI(room);
            chooseRoom.setOnEvent(objects -> {
                addRoom(room);
                parent.removeView(chooseRoom);
            });
            parent.addView(chooseRoom);
        }
    }
    private void addRoom(Room room){
        roomsSelected.add(room);
        reRenderRoomsSelected();
    }
    private void reRenderRoomsSelected() {
        binding.rooms.removeAllViews();
        float totolMoney = 0.0f;
        for (Room room: roomsSelected
             ) {
            ChooseRoom chooseRoom = new ChooseRoom(binding.rooms.getContext());
            chooseRoom.setDataUI(room);
            chooseRoom.setActionBackground(getDrawable(R.drawable.bg_fill_rounded_red));
            chooseRoom.setActoinText("xóa");
            chooseRoom.setOnEvent(objects -> {
                roomsSelected.remove(room);
                reRenderRoomsSelected();
            });
            binding.rooms.addView(chooseRoom);
            totolMoney += room.getRoomPrice();
        }
        binding.totalMoney.setText(DataHelper.GetMoney(totolMoney));
        if(roomsSelected.size() > 0){
            binding.btnNext.setEnabled(true);
        }
        else{
            binding.btnNext.setEnabled(false);
        }
    }
}