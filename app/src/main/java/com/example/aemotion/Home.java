package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sackcentury.shinebuttonlib.ShineButton;

public class Home extends AppCompatActivity {
    private View 	decorView;
    private int	uiOption;

    ShineButton shineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );

        shineButton = (ShineButton) findViewById(R.id.star1);
        Animation Myanim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        ImageView starIv1=(ImageView)findViewById(R.id.test);

        starIv1.clearAnimation();
        starIv1.setImageResource(R.drawable.ic_star);
        starIv1.startAnimation(Myanim1);

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);

        Button level1 = (Button) findViewById(R.id.level1);
        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level1.startAnimation(animation);

                Intent intent = new Intent(getApplicationContext(), Level1.class);
                startActivity(intent);
            }
        });

        Button level2 = (Button) findViewById(R.id.level2);
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level2.startAnimation(animation);

                Intent intent = new Intent(getApplicationContext(), Level2.class);
                startActivity(intent);
            }
        });

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