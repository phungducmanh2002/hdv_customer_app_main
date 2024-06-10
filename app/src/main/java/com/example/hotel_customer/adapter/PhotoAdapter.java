package com.example.hotel_customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.hotel_customer.databinding.AdapterPhotoBinding;
import com.example.hotel_customer.model.Photo;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {
    AdapterPhotoBinding binding;
    Context context;

    List<Photo> datas;

    public PhotoAdapter(Context context, List<Photo> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        if (this.datas == null)
            return 0;
        return this.datas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        binding = AdapterPhotoBinding.inflate(LayoutInflater.from(context), container, false);

        Photo photo = datas.get(position);
        if (photo != null) {
//            Glide.with(this.context).load(photo.getId()).into(binding.imgPhoto);
            binding.imgPhoto.setImageBitmap(photo.getBitmap());
        }
        container.addView(binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
