package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sackcentury.shinebuttonlib.ShineButton;

public class Level2_Result extends AppCompatActivity {
    TextView tvPoints, tv_congratulations, tv_score;
    ProgressBar pb_result;
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
        tvPoints = findViewById(R.id.tvPoints);
        pb_result = findViewById(R.id.pb_result);
        btn_home = findViewById(R.id.btn_home);
        tv_congratulations = findViewById(R.id.tv_congratulations);
        tv_score = findViewById(R.id.tv_score);


        tv_score.setText(points + "개를 맞추었네요!");
        pb_result.setProgress(points);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Level2_Result.this, Home.class);
                startActivity(intent);
            }
        });

        if (points == 0){
            tvPoints.setText("0%");
            tv_congratulations.setText("감정학습을 다시 해봐요!");
        } else if (points == 1){
            tvPoints.setText("25%");
            tv_congratulations.setText("좀 더 분발해볼까요?");
        }else if (points == 2){
            tvPoints.setText("50%");
            tv_congratulations.setText("잘했어요");
        }else if (points == 3){
            tvPoints.setText("75%");
            tv_congratulations.setText("잘했어요!");
        }else {
            tvPoints.setText("100%");
            tv_congratulations.setText("참 잘했어요!");
        }
    }
}