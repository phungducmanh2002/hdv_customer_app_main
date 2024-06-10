package com.example.hotel_customer.view.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotel_customer.R;
import com.example.hotel_customer.controller.SignupController;
import com.example.hotel_customer.databinding.ActivitySignupBinding;
import com.example.hotel_customer.remote.data.Account;
import com.example.hotel_customer.view.base.BaseActivity;

import java.util.Date;

public class SignupActivity extends BaseActivity<SignupController> {
    ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        controller = new SignupController(this);

        setEvent();
    }

    private void setEvent() {
        binding.btnNext.setOnClickListener(v -> {
            handleSignup();
        });
    }

    private void handleSignup() {
        checkData();
    }

    private void checkData() {
        String email = binding.ipEmail.getInputText();
        String password = binding.ipPassword.getInputText();
        String firstName = binding.ipFirstName.getInputText();
        String lastName = binding.ipLastName.getInputText();
        Date birthDay = binding.pdBirthDay.getPickDateDate();

        if (email.equals("")) {
            binding.ipEmail.setInputFocus();
            binding.ipEmail.setInputWarn("email không hợp lệ");
            return;
        } else {
            binding.ipEmail.setInputWarn(null);
        }
        if (password.equals("")) {
            binding.ipPassword.setInputFocus();
            binding.ipPassword.setInputWarn("password không hợp lệ");
            return;
        } else {
            binding.ipPassword.setInputWarn(null);

        }
        if (firstName.equals("")) {
            binding.ipFirstName.setInputFocus();
            binding.ipFirstName.setInputWarn("họ không hợp lệ");
            return;
        } else {
            binding.ipFirstName.setInputWarn(null);

        }
        if (lastName.equals("")) {
            binding.ipLastName.setInputFocus();
            binding.ipLastName.setInputWarn("tên không hợp lệ");
            return;
        } else {
            binding.ipLastName.setInputWarn(null);

        }
        if (birthDay == null) {
            binding.pdBirthDay.showPickDatePanel();
            return;
        }

        int gender = binding.rbMale.isChecked() ? 0 : binding.rbFemale.isChecked() ? 1 : 2;

        createAccount(email, password, firstName, lastName, gender, birthDay);

    }

    private void createAccount(String email, String password, String firstName, String lastName, int gender, Date birthDay) {
        showWaitingDialog();
        controller.createAccount(email, password, firstName, lastName, gender, birthDay);
    }

    public void onCreateAccountSuccess(Account account) {
        cancleWaitingDialog();
        Intent signin = new Intent(this, SigninActivity.class);
        signin.putExtra("email", binding.ipEmail.getInputText());
        signin.putExtra("password", binding.ipPassword.getInputText());
        startActivity(signin);
        finish();
    }
}