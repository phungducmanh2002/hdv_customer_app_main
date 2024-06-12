package com.example.hotel_customer.view.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.Application;
import com.example.hotel_customer.R;
import com.example.hotel_customer.controller.EditAvatarController;
import com.example.hotel_customer.databinding.ActivityEditAvatarBinding;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.helper.FileHelper;
import com.example.hotel_customer.view.base.BaseActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.IOException;
import java.io.InputStream;

public class EditAvatarActivity extends BaseActivity<EditAvatarController> {
    ActivityEditAvatarBinding binding;
    byte[] avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityEditAvatarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controller = new EditAvatarController(this);
        setEvent();
    }

    private void setEvent() {
        binding.btnChooseAvatar.setOnClickListener(v -> {
            ImagePicker.with(this).crop().compress(1024).maxResultSize(1080,1080).start();
        });
        binding.btnChooseAvatar.callOnClick();

        binding.btnNext.setOnClickListener(v -> {
            handleNext();
        });
    }

    private void handleNext() {
        if (avatar == null){
            binding.btnChooseAvatar.callOnClick();
            return;
        }
        controller.updateUserAvatar(Application.user.getId(), avatar);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }
        Uri uri = data.getData();
        if(uri == null){
            return;
        }
        try {
            InputStream iStream =   getContentResolver().openInputStream(uri);
            byte[] avatarData = DataHelper.GetBytes(iStream);
            onSelectAvatarResponse(avatarData);
        } catch (IOException e) {
//            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("SELECT_AVATAR_RESULT", e.getMessage());
        }
    }

    private void onSelectAvatarResponse(byte[] avatarData) {
        byte[] avatarDataClone = avatarData.clone();
        this.avatar = avatarData.clone();
        binding.avatar.setImageBitmap(DataHelper.Bytes2Bitmap(avatarDataClone));
    }

    public void onUpdateUserAvatarSuccess() {
        finish();
    }
}