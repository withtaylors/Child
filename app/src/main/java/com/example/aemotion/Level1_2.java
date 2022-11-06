package com.example.aemotion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.aemotion.R;

public class Level1_2 extends AppCompatActivity {

    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_2);

       Button button = findViewById(R.id.exit);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Level1.class);
                startActivity(intent);

            }
        });






        /**
         * 가로 슬라이드 뷰 Fragment
         */

        //ViewPager2
        mPager = findViewById(R.id.viewpager);
        //Adapter
        pagerAdapter = new MyAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);
        //Indicator

        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        /**
         * 이 부분 조정하여 처음 시작하는 이미지 설정.
         * 2000장 생성하였으니 현재위치 1002로 설정하여
         * 좌 우로 슬라이딩 할 수 있게 함. 거의 무한대로
         */

        mPager.setCurrentItem(0); //시작 지점
        mPager.setOffscreenPageLimit(5); //최대 이미지 수

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });
    }

}