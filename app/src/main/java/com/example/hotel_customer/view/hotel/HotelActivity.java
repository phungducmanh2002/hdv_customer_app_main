package com.example.hotel_customer.view.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.R;
import com.example.hotel_customer.adapter.PhotoAdapter;
import com.example.hotel_customer.controller.HotelController;
import com.example.hotel_customer.databinding.ActivityHotelBinding;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.helper.FileHelper;
import com.example.hotel_customer.model.Photo;
import com.example.hotel_customer.remote.data.Hotel;
import com.example.hotel_customer.view.base.BaseActivity;
import com.example.hotel_customer.view.booking.ChooseBookingActivity;
import com.example.hotel_customer.view.custome.HotelPanel;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends BaseActivity<HotelController> {
    public ActivityHotelBinding binding;
    public PhotoAdapter photoAdapter;
    public List<Photo> photos;
    int idHotel;
    Hotel hotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityHotelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        idHotel = getIntent().getIntExtra("idHotel", -1);
        controller = new HotelController(this);
        setEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadInfo();
    }

    private void loadInfo() {
        if(idHotel == -1){
            showNotifyDialog("Lỗi!\nKhông xác định được id hotel");
            return;
        }
        controller.loadHotel(idHotel);
    }

    private void setEvent() {
        initTemplateImageSlider();
        
        binding.btnBooking.setOnClickListener(v -> {
            handleBooking();
        });
    }

    private void handleBooking() {
//        this.controller.booking();
        Intent chooseBooking = new Intent(this, ChooseBookingActivity.class);
        chooseBooking.putExtra("idHotel", idHotel);
        startActivity(chooseBooking);
    }

    private void initTemplateImageSlider() {
        photos = new ArrayList<>();
        photoAdapter = new PhotoAdapter(this, photos);
        binding.imageSliderViewPager.setAdapter(photoAdapter);
        binding.imageSliderCircleIndicator.setViewPager(binding.imageSliderViewPager);
        photoAdapter.registerDataSetObserver(binding.imageSliderCircleIndicator.getDataSetObserver());
    }

    private List<Photo> getListPhoto() {
        List<Photo> photoList = new ArrayList<>();
        photoList.add(Photo.builder().id(1).bitmap(FileHelper.GetDrawableBitmap(getDrawable(R.drawable.img_hotel))).build());
        photoList.add(Photo.builder().id(2).bitmap(FileHelper.GetDrawableBitmap(getDrawable(R.drawable.img_hotel_ba))).build());
        photoList.add(Photo.builder().id(3).bitmap(FileHelper.GetDrawableBitmap(getDrawable(R.drawable.img_hotel_hai))).build());
        return photoList;
    }

    // api response
    public void renderHotel(Hotel hotel){
        this.hotel = hotel;
        binding.tvHotelName.setText(hotel.getName());
        binding.tvHotelDescription.setText(hotel.getDescription());
        binding.tvHotline.setText(hotel.getHotline());
        binding.tvStar.setText(Float.toString(hotel.getStar()));
        String roomPriceRange = String.format("%s - %s", DataHelper.GetMoney(hotel.getMinPrice()), DataHelper.GetMoney(hotel.getMaxPrice()));
        binding.tvRoomPrice.setText(roomPriceRange);
    }

    public void addPhoto(Photo photo){
        this.photos.add(photo);
        this.photoAdapter.notifyDataSetChanged();
    }

    public void addDefaultPhoto() {
        Photo photo = new Photo();
        photo.setBitmap(DataHelper.drawableToBitmap(getDrawable(R.drawable.img_hotel)));
        this.photos.add(photo);
        this.photoAdapter.notifyDataSetChanged();
    }

    public void renderHotelsNear(Hotel[] hotels) {
        binding.hotelsNear.removeAllViews();
        for (Hotel hotel: hotels) {
            Log.d("OUHKJBNOKM", hotel.toString());
            HotelPanel hotelPanel = new HotelPanel(binding.hotelsNear.getContext());
            hotelPanel.setDataUI(hotel);
            controller.loadHotelAvatar(hotel.getId(), bitmap -> {
                hotelPanel.setAvatar(bitmap);
            });
            binding.hotelsNear.addView(hotelPanel);
        }
    }
}