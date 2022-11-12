package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.Glide;


public class Level2_Result extends AppCompatActivity {
    TextView tv_congratulations, tv_score;
    Button btn_home;

    private View decorView;
    private int	uiOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_result);

        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );

        int points = getIntent().getExtras().getInt("points");
        btn_home = findViewById(R.id.btn_home);
        tv_congratulations = findViewById(R.id.tv_congratulations);
        tv_score = findViewById(R.id.tv_score);
        tv_score.setText(points + "개를 맞추었네요!");

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Level2_Result.this, Home.class);
                startActivity(intent);
            }
        });

        //별
        LottieAnimationView animationView1 = findViewById(R.id.star1);
        LottieAnimationView animationView2 = findViewById(R.id.star2);
        LottieAnimationView animationView3 = findViewById(R.id.star3);
        LottieAnimationView animationView4 = findViewById(R.id.star4);
        //트로피

        //ImageView imageView = findViewById(R.id.imageview);

        //Glide.with(this).load(R.raw.trophy).into(imageView);

        //왕관(트로피) 애니메이션
        //animationView5.setAnimation("trophy2.json");
        //animationView5.playAnimation();

        //별 애니메이션
        animationView1.setAnimation("star.json");
        animationView2.setAnimation("star.json");
        animationView3.setAnimation("star.json");
        animationView4.setAnimation("star.json");

        if (points == 0){
            tv_congratulations.setText("감정학습을 다시 해봐요!");
        } else if (points == 1){
            tv_congratulations.setText("좀 더 분발해볼까요?");
            animationView1.playAnimation();
        }else if (points == 2){
            tv_congratulations.setText("잘했어요");
            animationView1.playAnimation();
            animationView2.playAnimation();
        }else if (points == 3){
            tv_congratulations.setText("잘했어요!");
            animationView1.playAnimation();
            animationView2.playAnimation();
            animationView3.playAnimation();
            animationView1.loop(true);

        }else {
            tv_congratulations.setText("참 잘했어요!");
            animationView1.playAnimation();
            animationView2.playAnimation();
            animationView3.playAnimation();
            animationView4.playAnimation();
            animationView1.loop(true);
        }
        animationView1.loop(true);
        animationView2.loop(true);
        animationView3.loop(true);
        animationView4.loop(true);
        animationView1.setSpeed((float) 0.4);
        animationView2.setSpeed((float) 0.4);
        animationView3.setSpeed((float) 0.4);
        animationView4.setSpeed((float) 0.4);
    }
}