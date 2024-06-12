package com.example.hotel_customer;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.hotel_customer.databinding.ActivityMainBinding;
import com.example.hotel_customer.helper.FileHelper;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String serverIp = FileHelper.GetConfigValue("SERVER_IP");
    String serverPort = FileHelper.GetConfigValue("SERVER_PORT");
    String imgUrl = String.format("http://%s:%s/res/images", serverIp, serverPort);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        
        binding.btn.setOnClickListener(v -> {
            loadImage();
        });
    }

    private void loadImage() {
        Glide.with(Application.GetContext())
                .load(getImageUrl(3))
                .into(binding.img);
    }
    private String getImageUrl(int id) {
        return String.format("%s/%d", imgUrl, id);
    }
}