package com.example.hotel_customer.view.custome;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.hotel_customer.R;
import com.example.hotel_customer.databinding.CustomeInputBinding;
import com.example.hotel_customer.databinding.CustomePickdateBinding;
import com.example.hotel_customer.helper.DataHelper;
import com.example.hotel_customer.interfaces.OnEvent;

import java.util.Calendar;
import java.util.Date;

public class PickDate extends LinearLayout implements CustomeView{

    CustomePickdateBinding binding;
    Date selectDate;

    OnEvent onBeforePickDate, onAfterPickDate;
    OnEvent onPickDate2;

    public PickDate(Context context) {
        super(context);
        init(context, null);
    }

    public PickDate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PickDate(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public PickDate(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        binding = CustomePickdateBinding.inflate(LayoutInflater.from(context), this, true);

        if(attrs != null) {
            initAttrs(context ,attrs);
        }

        setEvent();

    }

    private void setEvent() {
        binding.icon.setOnClickListener(v -> {
            showPickDatePanel();
        });
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PickDate);
        String label = a.getString(R.styleable.PickDate_pickDateLabel);
        String text = a.getString(R.styleable.PickDate_pickDateText);
        String warn = a.getString(R.styleable.PickDate_pickDateWarn);

        setPickDateText(text);
        setPickDateWarn(warn);
        setPickDateLabel(label);

        a.recycle();
    }

    public void setPickDateLabel(String label) {
        if(label != null){
            binding.label.setVisibility(VISIBLE);
            binding.label.setText(label);
        }
        else{
            binding.label.setVisibility(GONE);
        }
    }

    public void setPickDateWarn(String warn) {
        if(warn != null){
            binding.warn.setVisibility(VISIBLE);
            binding.warn.setText(warn);
        }
        else{
            binding.warn.setVisibility(GONE);
        }
    }

    public void setPickDateText(String text) {
        if(text != null){
            binding.text.setVisibility(VISIBLE);
            binding.text.setText(text);
        }
        else{
            binding.text.setVisibility(GONE);
        }
    }

    public void showPickDatePanel(){
        final Calendar calender = Calendar.getInstance();
        if(this.selectDate != null){
            calender.setTime(this.selectDate);
        }
        int mYear = calender.get(Calendar.YEAR);
        int mMonth = calender.get(Calendar.MONTH);
        int mDay = calender.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, year, month, dayOfMonth) -> {
                    Date oldDate = selectDate;
                    Date newDate = DataHelper.CreateDate(year, month, dayOfMonth);

                    if(onBeforePickDate != null){
                        onBeforePickDate.action(selectDate);
                    }
                    setPickDateDate(newDate);

                    if(onAfterPickDate != null){
                        onAfterPickDate.action(newDate);
                    }

                    if(onPickDate2 != null){
                        onPickDate2.action(oldDate, newDate);
                    }

                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void setPickDateDate(Date newDate) {
        this.selectDate = newDate;
        binding.text.setVisibility(VISIBLE);
        this.binding.text.setText(DataHelper.Date2String(newDate));
    }
    public Date getPickDateDate(){
        return selectDate;
    }
}
