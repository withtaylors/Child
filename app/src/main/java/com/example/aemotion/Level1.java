package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level1 extends AppCompatActivity {

    int CheckON2;
    Button backbtn;

    private View decorView;
    private int	uiOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );

       /*final Animation front = AnimationUtils.loadAnimation(this, R.anim.front_animator);
       final Animation back = AnimationUtils.loadAnimation(this,R.anim.back_animator);*/

       TextView image_1 = findViewById(R.id.image_1);
       TextView image_2 = findViewById(R.id.image_2);
       TextView image_3 = findViewById(R.id.image_3);
       TextView image_4 = findViewById(R.id.image_4);

        Button backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

       /* ObjectAnimator animator = ObjectAnimator.ofFloat(image_1, "rotationY", 360);
        animator.setDuration(600);
*/

       /* float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        final float distance = image_1.getCameraDistance() * (scale + (scale / 3));
        image_1.setCameraDistance(distance);*/

        image_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               CheckON2=1;
                VO.setCheckON2(CheckON2);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                }
        });

        image_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckON2=2;
                VO.setCheckON2(CheckON2);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        image_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckON2=3;
                VO.setCheckON2(CheckON2);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        image_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckON2=4;
                VO.setCheckON2(CheckON2);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });



    }

}












/*
   */
/* private TextView[] cardText = new TextView[4];
    private TextView clickTextView;
    private boolean[] viewClickCheck = new boolean[4];

    private List<Integer> listNum = new ArrayList<>();

    int[] imagesources = {R.drawable.happy_learn, R.drawable.sad, R.drawable.surprise_learn, R.drawable.angry_pic};
   // int[] imagesources2 = {R.drawable.happy_word, R.drawable.sad_word, R.drawable.surprise_word, R.drawable.angry_word};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);


        for (int i = 1; i < 5; i++) {
            listNum.add(i);
        }
        Collections.shuffle(listNum);

        for (int i = 0; i < cardText.length; i++) {
            String viewName = "image_" + (i + 1);
            cardText[i] = findViewById(getResources().getIdentifier(viewName, "id", getPackageName()));
            cardText[i].setText(String.valueOf(listNum.get(i)));
            viewClickCheck[i] = false;
        }

        textViewOnClickListener();

        findViewById(R.id.re_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reViewInit();
            }
        });
    }

    private void textViewOnClickListener() {
        for (TextView v : cardText) {
            v.setOnClickListener(cardClick(v));
        }
    }

    private void reViewInit() {
        listNum.clear();

        for (int i = 1; i < 5; i++) {
            listNum.add(i);
        }

        for (int i = 0; i < cardText.length; i++) {
            cardText[i].setText(String.valueOf(listNum.get(i)));
            cardText[i].setBackgroundResource(R.drawable.happy_word);
            cardText[i].setTextColor(getResources().getColor(R.color.text_transparent));
            viewClickCheck[i] = false;
        }

        textViewOnClickListener();
    }

    private View.OnClickListener cardClick(final TextView textView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final double d = Math.random() * 4;
                clickTextView = findViewById(view.getId());

                float scale = getApplicationContext().getResources().getDisplayMetrics().density;
                final float distance = clickTextView.getCameraDistance() * (scale + (scale / 3));

                clickTextView.setCameraDistance(distance);
                clickTextView.animate().withLayer()
                        .rotationY(90)
                        .setDuration(150)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                if (!viewClickCheck[Integer.parseInt(clickTextView.getText().toString()) - 1]) {
                                    clickTextView.setBackgroundResource(imagesources[(int) d]);
                                    clickTextView.setTextColor(getResources().getColor(R.color.text_color));
                                    viewClickCheck[Integer.parseInt(clickTextView.getText().toString()) - 1] = true;
                                } else {
                                    clickTextView.setBackgroundResource(imagesources[(int) d]);

                                    clickTextView.setTextColor(getResources().getColor(R.color.text_transparent));
                                    viewClickCheck[Integer.parseInt(clickTextView.getText().toString()) - 1] = false;
                                }
                                clickTextView.setRotationY(-90);
                                clickTextView.animate().withLayer()
                                        .rotationY(0)
                                        .setDuration(250)
                                        .start();
                            }
                        }).start();


            }


        }*/
