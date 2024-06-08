package com.example.hotel_customer.view.base;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel_customer.R;
import com.example.hotel_customer.controller.base.Controller;

public class BaseActivity<C extends Controller> extends AppCompatActivity implements View {
    protected C controller;
    protected Dialog waitingDialog, notifyDialog;

    public void showWaitingDialog() {
        if (waitingDialog == null) {
            waitingDialog = new Dialog(this);
            waitingDialog.setContentView(R.layout.dialog_waiting);
            waitingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            waitingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            waitingDialog.setCancelable(false);
        }
        waitingDialog.show();
    }
    public void showWaitingDialog(String content) {
        if (waitingDialog == null) {
            waitingDialog = new Dialog(this);
            waitingDialog.setContentView(R.layout.dialog_waiting);
            waitingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            waitingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            waitingDialog.setCancelable(false);
        }
        TextView tvContent = waitingDialog.findViewById(R.id.tvContent);
        tvContent.setText(content);
        waitingDialog.show();
    }

    public void cancleWaitingDialog() {
        if (waitingDialog != null) {
            waitingDialog.cancel();
        }
    }

    public void showNotifyDialog(String content) {
        if (notifyDialog == null) {
            notifyDialog = new Dialog(this);
            notifyDialog.setContentView(R.layout.dialog_notify);
            notifyDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            notifyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        TextView tvContent = notifyDialog.findViewById(R.id.tvContent);
        tvContent.setText(content);
        Button btnCancle = notifyDialog.findViewById(R.id.btnCancle);
        btnCancle.setOnClickListener(v -> {
            notifyDialog.cancel();
        });
        notifyDialog.show();
    }
}
