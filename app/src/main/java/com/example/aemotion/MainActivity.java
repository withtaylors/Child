package com.example.aemotion;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.aemotion.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int CheckON2;
    private View decorView;
    private int	uiOption;





    private ActivityMainBinding mBinding;

    private Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        CheckON2= VO.getCheckON2();

        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );


        mBinding.exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Level1.class);
                startActivity(intent);
            }
        });

        List<String> sliderItems = new ArrayList<>();
        if(CheckON2==1){
        sliderItems.add("https://cdn.pixabay.com/photo/2016/03/12/21/03/girl-1252739_960_720.jpg");
        sliderItems.add("https://cdn.pixabay.com/photo/2016/11/29/04/59/woman-1867431_960_720.jpg");
        sliderItems.add("https://cdn.pixabay.com/photo/2017/01/09/11/23/kids-1966202_960_720.jpg");
        sliderItems.add("https://cdn.pixabay.com/photo/2016/12/03/20/06/girl-1880482_960_720.jpg");
        sliderItems.add("https://cdn.pixabay.com/photo/2020/12/09/04/00/baby-5816350_960_720.jpg");
        sliderItems.add("https://cdn.pixabay.com/photo/2018/09/12/15/33/smile-3672452_960_720.jpg");
        sliderItems.add("https://cdn.pixabay.com/photo/2020/07/15/13/19/baby-5407690_960_720.jpg");
        sliderItems.add("https://cdn.pixabay.com/photo/2016/11/29/03/35/girl-1867092_960_720.jpg");
        sliderItems.add("https://cdn.pixabay.com/photo/2016/09/24/03/20/man-1690965_960_720.jpg");
        sliderItems.add("https://cdn.pixabay.com/photo/2016/11/23/18/42/boy-1854308_960_720.jpg");
        sliderItems.add("https://cdn.pixabay.com/photo/2018/03/04/23/37/child-3199624_960_720.jpg"); }
        if(CheckON2==2){
            sliderItems.add("https://cdn.pixabay.com/photo/2018/04/30/17/45/baby-3363419_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2020/04/24/19/48/baby-5088496_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2017/04/25/18/06/marigold-flower-2260427_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2015/02/27/18/27/child-652552_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2021/05/09/12/38/kid-6240743_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2017/06/09/17/26/baby-2387661_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2018/04/30/07/55/bullying-3362025_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2017/06/09/05/21/sad-2385795_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2021/04/28/19/50/child-6214874_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2014/09/03/23/08/kid-435140_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2015/07/15/06/42/man-845709_960_720.jpg");

        }
        if(CheckON2==3){
            sliderItems.add("https://cdn.pixabay.com/photo/2017/02/24/07/45/woman-2094172_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2016/02/09/08/18/female-1188508_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2012/02/29/12/04/cute-18833_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2018/04/27/21/55/surprised-3355958_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2021/07/05/20/53/surprised-6390054_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2015/04/22/14/58/burundi-734900_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2020/09/10/17/59/baby-5561133_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2021/09/12/12/37/boy-6618292_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2020/10/06/16/02/computer-games-5632589_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2020/12/03/05/55/man-5799574_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2016/06/10/00/06/woman-1447082_960_720.jpg");

        }

        if(CheckON2==4){
            sliderItems.add("https://cdn.pixabay.com/photo/2017/09/19/19/16/angry-2766265_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2017/08/25/21/43/frustrated-2681484_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2017/09/10/19/09/boy-2736659_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2021/10/20/12/32/kid-6725904_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2013/10/08/15/42/child-192713_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2018/03/03/22/11/boy-3196715_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2015/04/21/00/18/boy-732495_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2018/07/10/03/19/black-3527812_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2014/10/20/11/18/woman-495124_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2016/12/09/12/50/kids-1894804_960_720.jpg");
            sliderItems.add("https://cdn.pixabay.com/photo/2020/08/31/00/29/man-5531026_960_720.jpg");

        }
        Collections.shuffle(sliderItems);

        mBinding.vpImageSlider.setAdapter(new SliderAdapter(this, mBinding.vpImageSlider, sliderItems));

        mBinding.vpImageSlider.setClipToPadding(false);
        mBinding.vpImageSlider.setClipChildren(false);
        mBinding.vpImageSlider.setOffscreenPageLimit(3);
        mBinding.vpImageSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        mBinding.vpImageSlider.setPageTransformer(compositePageTransformer);

        mBinding.vpImageSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            mBinding.vpImageSlider.setCurrentItem(mBinding.vpImageSlider.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);
    }
}