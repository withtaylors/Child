package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level3 extends AppCompatActivity {

    Button happy, sad, surprised, angry;

    int CheckON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3);

        happy = findViewById(R.id.happy);
        sad = findViewById(R.id.sad);
        surprised = findViewById(R.id.surprised);
        angry = findViewById(R.id.angry);

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckON = 1;
                VO.setCheckON(CheckON);
                Intent intent = new Intent(Level3.this, Camera.class);
                startActivity(intent);
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckON = 2;
                VO.setCheckON(CheckON);
                Intent intent = new Intent(Level3.this, Camera.class);
                startActivity(intent);
            }
        });

        surprised.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckON = 3;
                VO.setCheckON(CheckON);
                Intent intent = new Intent(Level3.this, Camera.class);
                startActivity(intent);
            }
        });

        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckON = 4;
                VO.setCheckON(CheckON);
                Intent intent = new Intent(Level3.this, Camera.class);
                startActivity(intent);
            }
        });

    }
}