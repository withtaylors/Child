package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

public class Surprised extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surprised);


        SharedPreferences sharedPreferences = getSharedPreferences("MY", Context.MODE_PRIVATE );

        ImageView surprise = findViewById(R.id.surprise);
        String temp1 = sharedPreferences.getString("image", " ");
        byte[] encodeByte = Base64.decode(temp1, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        surprise.setImageBitmap(bitmap);
    }
}