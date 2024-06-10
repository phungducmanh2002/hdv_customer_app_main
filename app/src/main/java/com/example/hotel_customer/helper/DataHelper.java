package com.example.hotel_customer.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataHelper {
    private static final Gson gson = new Gson();
    // DATE
    public static int GetCurrentMonth(){
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Vì tháng được đếm từ 0
        return currentMonth;
    }
    public static int GetCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        return currentYear;
    }
    public static Date CreateDate(int year, int monthOfYear, int dayOfMonth){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear); // Tháng bắt đầu từ 0 cho tháng 1
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        return calendar.getTime();
    }
    public static String Date2String(Date date){
        if(date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
    public static String Date2String(Date date, String format){
        if(date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    public static Date MinusDay(Date date, int number){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - number);
        return calendar.getTime();
    }
    public static Date PlusDay(Date date, int number){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + number);
        return calendar.getTime();
    }

    // MAPPER

    public static <T> T ConvertFromLinkedTreeMap(LinkedTreeMap<?, ?> linkedTreeMap, Class<T> cls){
        Gson gson = new Gson();
        String json = gson.toJson(linkedTreeMap);
        Type type = TypeToken.getParameterized(cls, cls).getType();
        return gson.fromJson(json,type);
    }
    public static <T> T convert(Object data, Class<T> clazz) {
        String json = gson.toJson(data);
        Log.d("HUOJNOUJNLUOJ", json);
        return gson.fromJson(json, clazz);
    }
    public static <T> T ConvertFromObject(Object object, Class<T> cls){
        LinkedTreeMap<?, ?> linkedTreeMap = (LinkedTreeMap<?, ?>)object;
        Gson gson = new Gson();
        String json = gson.toJson(linkedTreeMap);
        Type type = TypeToken.getParameterized(cls, cls).getType();
        return gson.fromJson(json,type);
    }

    public static <T> T ConvertFromObjectArr(Object object, Class<T> cls){
        Gson gson = new Gson();
        String json = gson.toJson(object); // Convert object to JSON string
        return gson.fromJson(json, cls); // Convert JSON string to desired class
    }

    // NUMBER

    public static String FormatNumber(long number){
        DecimalFormat formatter = new DecimalFormat("###,###");
        return formatter.format(number);
    }
    public static String GetMoney(long number) {
        DecimalFormat formatter = new DecimalFormat("###,### VNĐ");
        return formatter.format(number);
    }
    public static String GetMoney(double number) {
        DecimalFormat formatter = new DecimalFormat("###,### VNĐ");
        return formatter.format(number);
    }

    // STREAM (IMAGE)
    public static byte[] GetBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public static Bitmap Bytes2Bitmap(byte[] byteArray){
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        return ((BitmapDrawable) drawable).getBitmap();
    }
}
