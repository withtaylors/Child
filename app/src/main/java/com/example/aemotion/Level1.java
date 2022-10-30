package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level1 extends AppCompatActivity {

    private TextView[] cardText = new TextView[4];
    private TextView clickTextView;
    private boolean[] viewClickCheck = new boolean[4];

    private List<Integer> listNum = new ArrayList<>();

    int[] imagesources = {R.drawable.happy_learn, R.drawable.happy_learn, R.drawable.happy_learn, R.drawable.happy_learn};

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
        Collections.shuffle(listNum);

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
                                    clickTextView.setBackgroundResource(R.drawable.happy_word);
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


        };
    }
}