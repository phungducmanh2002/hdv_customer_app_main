package com.example.hotel_customer.view.custome;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.hotel_customer.R;
import com.example.hotel_customer.databinding.CustomeInputBinding;

public class Input extends LinearLayout implements CustomeView{

    CustomeInputBinding binding;

    public Input(Context context) {
        super(context);
        init(context, null);
    }

    public Input(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Input(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    public Input(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        binding = CustomeInputBinding.inflate(LayoutInflater.from(context), this, true);

        if(attrs != null) {
            initAttrs(context ,attrs);
        }

    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Input);
        String text = a.getString(R.styleable.Input_inputText);
        String hint = a.getString(R.styleable.Input_inputHint);
        String warn = a.getString(R.styleable.Input_inputWarn);
        String label = a.getString(R.styleable.Input_inputLabel);

        Integer line = a.getInteger(R.styleable.Input_inputLine , -1);
        Integer length = a.getInteger(R.styleable.Input_inputLength , -1);
        Integer type = a.getInt(R.styleable.Input_inputType, -1);

        if(text != null){
            setInputText(text);
        }
        if(hint != null){
            setInputHint(hint);
        }
        if(warn != null){
            setInputWarn(warn);
        }
        if(label != null){
            setInputLabel(label);
        }
        if(line != -1){
            setInputLine(line);
        }
        if(length != -1){
            setInputLength(length);
        }
        if(type != -1){
            setInputType(type);
        }

        a.recycle();
    }

    private void setInputType(Integer type) {
        if(type == null){
            return;
        }
        switch (type){
            case 1:{

            }
            case 2:{

            }
            case 3:{

            }
        }
    }

    public void setInputLength(Integer length) {
    }

    public void setInputLine(Integer line) {
        binding.input.setMaxLines(line);
        binding.input.setLines(line);
    }

    public void setInputLabel(String label) {
        if(label != null){
            binding.label.setText(label);
            binding.label.setVisibility(VISIBLE);
        }
        else{
            binding.label.setVisibility(GONE);
        }
    }

    public void setInputWarn(String warn) {
        if(warn != null){
            binding.warn.setVisibility(VISIBLE);
            binding.warn.setText(warn);
        }
        else{
            binding.warn.setVisibility(GONE);
        }
    }

    public void setInputHint(String hint) {
        binding.input.setHint(hint);
    }

    public void setInputText(String text) {
        binding.input.setText(text);
    }

    public void setInputFocus(){
        binding.input.requestFocus();
    }

    public String getInputText(){
        return binding.input.getText().toString();
    }
}
