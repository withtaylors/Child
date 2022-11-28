package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class Expic extends AppCompatActivity {

    int CheckON;
    ImageView layoutback;

    private View decorView;
    private int	uiOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expic);

        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );

        final TextView mSwitcher = (TextView) findViewById(R.id.h_message); mSwitcher.setText("예시표정을 잘 따라해보세요!");

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);

        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(3000);

        mSwitcher.startAnimation(out);
        mSwitcher.startAnimation(in);

        layoutback = findViewById(R.id.layoutback);

        CheckON = VO.getCheckON();
        if(CheckON == 1){
            layoutback.setImageResource(R.drawable.happy_s);
        }
        else if(CheckON == 2){
            layoutback.setImageResource(R.drawable.sad_s);
        }
        else if(CheckON == 3){
            layoutback.setImageResource(R.drawable.surprised_s);
        }
        else{
            layoutback.setImageResource(R.drawable.angry_s);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Expic.this, Camera.class);
                startActivity(intent);
            }
        }, 3800);
    }
}