package com.example.hotel_customer.view.account;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.Application;
import com.example.hotel_customer.R;
import com.example.hotel_customer.controller.CreateUserController;
import com.example.hotel_customer.databinding.ActivityCreateCustomerBinding;
import com.example.hotel_customer.remote.data.User;
import com.example.hotel_customer.view.base.BaseActivity;

public class CreateCustomerActivity extends BaseActivity<CreateUserController> {
    ActivityCreateCustomerBinding binding;
    int idAccount;
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCreateCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        controller = new CreateUserController(this);
        idAccount = getIntent().getIntExtra("idAccount", -1);
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        setEvent();
    }

    private void setEvent() {
        binding.btnNext.setOnClickListener(v -> {
            handleCreateUser();
        });
    }

    private void handleCreateUser() {
        if(isValidData()){
            controller.createCustomer(idAccount, binding.ipUsername.getInputText());
        }
    }

    private boolean isValidData() {
        if(binding.ipUsername.getInputText().equals("")){
            binding.ipUsername.setInputWarn("vui lòng nhập username");
            binding.ipUsername.setInputFocus();
            return false;
        }
        else{

        }
        return true;
    }

    public void onCreateUserSuccess(User user) {
        controller.signin(email, password);
    }

    public void onGenUserTokenSuccess(String token) {
        Application.token = token;
        finish();
    }
}