package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Level2 extends AppCompatActivity {

    TextView Timer;
    ImageView ivShowImage;

    ProgressBar pb_quiz_progress;

    HashMap<String, Integer> map = new HashMap<>();
    ArrayList<String> techList = new ArrayList<>();

    int index;
    Button btn1, btn2, btn3, btn4, nextButton;
    TextView tvPoints;
    int points;

    CountDownTimer countDownTimer;
    long millisUntilFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Timer = findViewById(R.id.Timer);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.option_one);
        btn2 = findViewById(R.id.option_two);
        btn3 = findViewById(R.id.option_three);
        btn4 = findViewById(R.id.option_four);
        nextButton = findViewById(R.id.nextQuestion);
        tvPoints = findViewById(R.id.tv_points);
        pb_quiz_progress = findViewById(R.id.pb_quiz_progress);
        // Initialize index with 0
        index = 0;

        techList.add("기쁨");
        techList.add("슬픔");
        techList.add("놀람");
        techList.add("화남");

        map.put(techList.get(0), R.drawable.happy);
        map.put(techList.get(1), R.drawable.sad);
        map.put(techList.get(2), R.drawable.surprised);
        map.put(techList.get(3), R.drawable.angry_pic);

        Collections.shuffle(techList);
        millisUntilFinished = 10000;
        points = 0;
        startGame();

        nextButton.setVisibility(View.INVISIBLE);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
                System.out.print("다음버튼 눌림\n");
            }
        });
    }

    private void startGame() {
        // Initialize millisUntilFinished with 10 seconds.
        millisUntilFinished = 10000;
        // Set the TextView for Timer.
        //Timer.setText("" + (millisUntilFinished / 1000) + "s");
        // Set the TextView for points.
        tvPoints.setText(points + " / " + techList.size());

        generateQuestions(index);

        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update tvTimer every 1 second to show the number of seconds remaining.
                //Timer.setText("" + (millisUntilFinished / 1000) + "s");
                pb_quiz_progress.setProgress((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                // Increment index by 1 so that the next question can be presented
                // automatically when the user is unable to select his/her answer.
                index++;
                // When timer is finished check if all questions are being asked.
                if (index > techList.size() - 1){
                    // If true, hide the ImageView and Buttons.
                    ivShowImage.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    btn2.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);
                    // Go to GameOver screen with points using an Intent
                    Intent intent = new Intent(Level2.this, Level2_Result.class);
                    intent.putExtra("points", points);
                    startActivity(intent);
                    // Finish StartGame Activity
                    finish();
                } else {
                    // In the else part, that is, if all questions are not being asked,
                    // initialize countDownTimer with null and call startGame() again.
                    // And this is the case, when no answer is selected before the
                    // time limit is over. So, the player will be presented with the
                    // next question and no points will be granted. If you want
                    // you can also decrement the points here for skipping a question
                    // and that'll make the quiz a bit harder.
                    countDownTimer = null;
                    startGame();
                }
            }
        }.start(); // Call start() method to start the timer.
    }

    private void generateQuestions(int index) {
        // Clone techList to a new ArrayList called techListTemp.
        ArrayList<String> techListTemp = (ArrayList<String>) techList.clone();
        // Get the correct answer for the current question from techList using index.
        String correctAnswer = techList.get(index);
        // You need to find three non-repeated incorrect answers randomly.
        // So, delete the correct answer from techListTemp.
        // Shuffle it and get first three elements from it.
        techListTemp.remove(correctAnswer);
        Collections.shuffle(techListTemp);
        // Create a temporary ArrayList for storing three non-repeated random answers
        // from techListTemp and one correct answer.
        ArrayList<String> newList = new ArrayList<>();
        // Get first three elements from techListTemp and add into newList.
        newList.add(techListTemp.get(0));
        newList.add(techListTemp.get(1));
        newList.add(techListTemp.get(2));
        // Also add the correct answer into newList
        newList.add(correctAnswer);
        // Shuffle newList so that the correct answer can be placed in one of the four
        // buttons, randomly.
        Collections.shuffle(newList);
        // Once you shuffle newList, set all four Button's text with the elements
        // from newList.
        btn1.setText(newList.get(0));
        btn2.setText(newList.get(1));
        btn3.setText(newList.get(2));
        btn4.setText(newList.get(3));
        // Also, set the ImageView with current image from map
        ivShowImage.setImageResource(map.get(techList.get(index)));
    }

    public void nextQuestion() {
        // This method is called because the user has tapped the Next button,
        // so, set the background color of all the buttons to the color that we used in start_game.xml.
        btn1.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        btn2.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        btn3.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        btn4.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
//        btn1.setBackgroundColor(Color.parseColor("#DB6400"));
//        btn2.setBackgroundColor(Color.parseColor("#DB6400"));
//        btn3.setBackgroundColor(Color.parseColor("#DB6400"));
//        btn4.setBackgroundColor(Color.parseColor("#DB6400"));
        // Enable the buttons
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        // Cancel the countDownTimer
        countDownTimer.cancel();
        index++;
        // Check if all questions have been asked.
        if (index > techList.size() - 1){
            // If true, hide the ImageView and Buttons.
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            // Go to GameOver screen with points
            Intent intent = new Intent(Level2.this, Level2_Result.class);
            intent.putExtra("points", points);
            startActivity(intent);
            // Finish StartGame Activity
            finish();
        }else {
            // Till there is at least one question left, initialize countDownTimer with null and
            // call startGame()
            countDownTimer = null;
            startGame();
        }
    }

    public void answerSelected(View view) {
        nextButton.setVisibility(View.VISIBLE);
        // Change the clicked Button's background color
        //view.setBackgroundColor(Color.parseColor("#17243e"));
        // Disable all four Buttons
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        // The user has selected an answer, so, cancel the countDownTimer
        countDownTimer.cancel();
        // Get clicked button's text for user answer
        String answer = ((Button) view).getText().toString().trim();
        System.out.println("내가 선택한 답 :          " +answer);
        // And, get the correct answer for the current question from techList using index
        // as position.
        String correctAnswer = techList.get(index);
        System.out.println("맞는 답 :          " +correctAnswer);

        // Compare answer and correctAnswer, that is, the answer selected by the user
        // and the correct answer for this question.
        if(answer.equals(correctAnswer)){
            view.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg));
            // If true, the user has selected the right answer. So, increment points.
            points++;
//            // Here we are incrementing points by 1 here, but, you can increment by any number
//            // you want.
//            // Update the TextViews for points and result
            tvPoints.setText(points + " / " + techList.size());
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    nextQuestion(); //딜레이 후 시작할 코드 작성
                }
            }, 2000);
        } else {
            view.setBackground(ContextCompat.getDrawable(this, R.drawable.uncorrect_option_border_bg));
            // In else, that is, when the user answer is incorrect, show "Wrong" in tvResult.
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    nextQuestion(); //딜레이 후 시작할 코드 작성
                }
            }, 2000);
        }
    }
}