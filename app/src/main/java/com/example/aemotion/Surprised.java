package com.example.aemotion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class Surprised extends AppCompatActivity {
    private KonfettiView konfettiView = null;
    private Shape.DrawableShape drawableShape = null;
    MediaPlayer mediaPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surprised);

        //효과음
        mediaPlayer2 = MediaPlayer.create(this, R.raw.clap);
        mediaPlayer2.start();

        SharedPreferences sharedPreferences = getSharedPreferences("MY", Context.MODE_PRIVATE );
        ImageView surprise = findViewById(R.id.surprise);

        String temp1 = sharedPreferences.getString("image", " ");
        byte[] encodeByte = Base64.decode(temp1, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        surprise.setImageBitmap(bitmap);

        final Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.btn_star);
        Shape.DrawableShape drawableShape = new Shape.DrawableShape(drawable, true);

        konfettiView = findViewById(R.id.konfettiView);
        EmitterConfig emitterConfig = new Emitter(5L, TimeUnit.SECONDS).perSecond(50);
        Party party = new PartyFactory(emitterConfig)
                .angle(270)
                .spread(90)
                .setSpeedBetween(1f, 5f)
                .timeToLive(2000L)
                .shapes(new Shape.Rectangle(0.2f), drawableShape)
                .sizes(new Size(12, 5f, 0.2f))
                .position(0.0, 0.0, 1.0, 0.0)
                .build();
        konfettiView.start(party);

        Button back = findViewById(R.id.back);
        Button home = findViewById(R.id.home);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Surprised.this, Level3.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Surprised.this, Home.class);
                startActivity(intent);
            }
        });
    }

    // MediaPlayer는 시스템 리소스를 잡아먹는다.
    // MediaPlayer는 필요이상으로 사용하지 않도록 주의해야 한다.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer2 != null) {
            mediaPlayer2.release();
            mediaPlayer2 = null;
        }
    }
}