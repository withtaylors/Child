package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Copyright extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_copyright);

        Button cottoncandy = (Button) findViewById(R.id.cottoncandy);
        cottoncandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.flaticon.com/free-icons/cotton-candy"));
                startActivity(intent);
            }
        });

        Button f1 = (Button) findViewById(R.id.f1);
        cottoncandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.flaticon.com/free-icons/feelings"));
                startActivity(intent);
            }
        });

        Button f2 = (Button) findViewById(R.id.f1);
        cottoncandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.flaticon.com/free-icons/crying-baby"));
                startActivity(intent);
            }
        });
        Button f3 = (Button) findViewById(R.id.f1);
        cottoncandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.flaticon.com/free-icons/surprise"));
                startActivity(intent);
            }
        });
        Button f4 = (Button) findViewById(R.id.f1);
        cottoncandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.flaticon.com/free-icons/anger"));
                startActivity(intent);
            }
        });

        Button trophy = (Button) findViewById(R.id.trophy);
        cottoncandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.flaticon.com/free-icons/best-seller"));
                startActivity(intent);
            }
        });
}
}