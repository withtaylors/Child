package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Level2_Result extends AppCompatActivity {
    TextView tvPoints, tv_congratulations, tv_score;
    ProgressBar pb_result;

    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_result);

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