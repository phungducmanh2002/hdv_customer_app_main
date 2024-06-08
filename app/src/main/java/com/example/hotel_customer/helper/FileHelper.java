package com.example.hotel_customer.helper;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.hotel_customer.Application;
import com.example.hotel_customer.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class FileHelper {
    public static String GetConfigValue(String name){
        Resources resources = Application.GetContext().getResources();
        try{
            InputStream is = resources.openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(is);
            return properties.getProperty(name);
        }
        catch (Resources.NotFoundException e){
            Log.e("GET PROPERTIES ERROR", "Unable to find the config file: " + e.getMessage());
        }
        catch (IOException e){
            Log.e("GET PROPERTIES ERROR", "Failed to open config file.");
        }

        return null;
    }
    public static <T> T ConvertJson(String json, Class<T> cls){
        Gson gson = new Gson();
//        Type type = TypeToken.getParameterized(cls, cls).getType();
        return gson.fromJson(json,cls);
    }
    public static Bitmap GetDrawableBitmap(Drawable drawable){
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        return bitmap;
    }
}
