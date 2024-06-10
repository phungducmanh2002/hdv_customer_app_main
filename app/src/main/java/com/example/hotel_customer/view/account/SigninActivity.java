package com.example.hotel_customer.view.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.Application;
import com.example.hotel_customer.controller.SigninController;
import com.example.hotel_customer.databinding.ActivitySigninBinding;
import com.example.hotel_customer.remote.data.Account;
import com.example.hotel_customer.view.base.BaseActivity;
import com.example.hotel_customer.view.hotel.HomeActivity;

public class SigninActivity extends BaseActivity<SigninController> {
    ActivitySigninBinding binding;
    String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");

        controller = new SigninController(this);
        
        initUI();
        setEvent();
    }

    private void initUI() {
        if(email != null && password != null){
            binding.ipEmail.setInputText(email);
            binding.ipPassword.setInputText(password);
        }
    }

    private void setEvent() {
        binding.btnSignin.setOnClickListener(v -> {
            handleSignin();
        });
    }
    private void handleSignin() {
        if(isValidData()){
            String email = binding.ipEmail.getInputText();
            String password = binding.ipPassword.getInputText();
            controller.signin(email, password);
        }
//        Intent home = new Intent(this, HomeActivity.class);
//        startActivity(home);
    }
    private boolean isValidData() {
        if(binding.ipEmail.getInputText().equals("")){
            binding.ipEmail.setInputWarn("vui lòng nhập email");
            binding.ipEmail.setInputFocus();
            return false;
        }
        else{
            binding.ipEmail.setInputWarn(null);
        }

        if(binding.ipPassword.getInputText().equals("")){
            binding.ipPassword.setInputWarn("vui lòng nhập mật khẩu");
            binding.ipPassword.setInputFocus();
            return false;
        }
        else{
            binding.ipPassword.setInputWarn(null);
        }

        return true;
    }
    public void onGenUserTokenSuccess(String token) {
        Application.token = token;
        finish();
    }
    public void onUserNotFound(Account account) {
        Intent createCustomer = new Intent(this, CreateCustomerActivity.class);
        createCustomer.putExtra("idAccount", account.getId());
        createCustomer.putExtra("email", binding.ipEmail.getInputText());
        createCustomer.putExtra("password", binding.ipPassword.getInputText());
        startActivity(createCustomer);
        finish();
    }
}