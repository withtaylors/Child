package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);

        Button level3 = (Button) findViewById(R.id.level3);
        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level3.startAnimation(animation);


                Intent intent = new Intent(getApplicationContext(), Level3.class);
                startActivity(intent);
            }
        });
    }
}