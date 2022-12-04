package com.example.aemotion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aemotion.ml.Model;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Camera extends AppCompatActivity {
    TextView result;
    ImageView imageView;
    ImageButton picture;
    Button next;
    int imageSize = 224;
    final static int TAKE_PICTURE= 1;
    final static int CROP_PICTURE= 2;
    private Uri pictureUri;
    int CheckON;
    String str = "아니에요 :(";

    private View decorView;
    private int	uiOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );

        Button backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Level3.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        CheckON = VO.getCheckON();
        result = findViewById(R.id.result);
        picture = findViewById(R.id.picture);
        imageView = findViewById(R.id.imageView);
        next = findViewById(R.id.next);

        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        if(CheckON == 1){
            next.setVisibility(View.INVISIBLE);
            result.setVisibility(View.INVISIBLE);
        }
        else if(CheckON == 2){
            next.setVisibility(View.INVISIBLE);
            result.setVisibility(View.INVISIBLE);
        }
        else if(CheckON == 3){
            next.setVisibility(View.INVISIBLE);
            result.setVisibility(View.INVISIBLE);
        }
        else{
            next.setVisibility(View.INVISIBLE);
            result.setVisibility(View.INVISIBLE);
        }

        //Uri exposure 무시
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picture.startAnimation(animation);

                result.setVisibility(View.VISIBLE);
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    // 임시로 사용할 파일의 경로를 생성
                    String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
                    pictureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

                    cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, pictureUri);
                    cameraIntent.putExtra("return-data", true);
                    startActivityForResult(cameraIntent, CROP_PICTURE);

                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이미지 넘김
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap1 = drawable.getBitmap();
                imageView.setImageBitmap(bitmap1);
                ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG, 70, baos1);
                byte[] bytes1 = baos1.toByteArray();
                String temp1 = Base64.encodeToString(bytes1, Base64.DEFAULT);
                SharedPreferences sharedPreferences = getSharedPreferences("MY", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("image", temp1);
                editor.apply();

                if (CheckON == 1) {
                    Intent intent = new Intent(Camera.this, Happy.class);
                    startActivity(intent);
                } else if (CheckON == 2) {
                    Intent intent = new Intent(Camera.this, Sad.class);
                    startActivity(intent);
                } else if (CheckON == 3) {
                    Intent intent = new Intent(Camera.this, Surprised.class);
                    startActivity(intent);
                } else if( CheckON == 4) {
                    Intent intent = new Intent(Camera.this, Angry.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void classifyImage(Bitmap image) {
        try {
            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            String[] classes = {"happy", "sad", "surprise", "angry"};

            System.out.println(confidences);
            next.setVisibility(View.INVISIBLE);
            //next.setVisibility(View.VISIBLE);

            MediaPlayer mediaPlayer2;
            mediaPlayer2 = MediaPlayer.create(this, R.raw.uhh);

            if (CheckON == 1){
                if(classes[maxPos] == "happy"){
                    result.setText("기쁜 표정을 잘 지었어요!");
                    next.setVisibility(View.VISIBLE);
                }
                else{
                    mediaPlayer2.start();
                    result.setText("기쁜 표정이"+" "+ str+ "\n" +"표정을 다시 한번 지어볼까요?");
                }
            }else if (CheckON == 2) {
                if (classes[maxPos] == "sad") {
                    result.setText("슬픈 표정을 잘 지었어요!");
                    next.setVisibility(View.VISIBLE);
                } else {
                    mediaPlayer2.start();
                    result.setText("슬픈 표정이"+" "+ str+ "\n" +"표정을 다시 한번 지어볼까요?");
                }
            }else if (CheckON == 3) {
                if (classes[maxPos] == "surprise") {
                    result.setText("놀란 표정을 잘 지었어요!");
                    next.setVisibility(View.VISIBLE);
                } else {
                    mediaPlayer2.start();
                    result.setText("놀란 표정이"+" "+ str+ "\n" +"표정을 다시 한번 지어볼까요?");
                }
            }else if (CheckON == 4) {
                if (classes[maxPos] == "angry") {
                    result.setText("화난 표정을 잘 지었어요!");
                    next.setVisibility(View.VISIBLE);
                } else {
                    mediaPlayer2.start();
                    result.setText("화난 표정이"+" "+ str+ "\n" +"표정을 다시 한번 지어볼까요?");
                }
            }

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            //TODO Handle the exception
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //크롭된 이미지 가져와서 이미지뷰에 보여주기
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK|| data.hasExtra("data")) { //데이터를 가지고 있는지 확인
                    final Bundle extras = data.getExtras();

                    if (extras != null) {
                        Bitmap image = extras.getParcelable("data"); //크롭한 이미지 가져오기
                        int dimension = Math.min(image.getWidth(), image.getHeight());
                        image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                        imageView.setImageBitmap(image);// 크롭한 이미지 배치하기
                        image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                        classifyImage(image);
                    }
                    // 임시 파일 삭제
                    File f = new File(pictureUri.getPath());
                    if (f.exists())
                        f.delete();
                    break;
                }
                break;

            // 이미지 크롭
            case CROP_PICTURE: {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(pictureUri, "image/*");

                intent.putExtra("outputX", 200); //크롭한 이미지 x축 크기
                intent.putExtra("outputY", 200); //크롭한 이미지 y축 크기
                intent.putExtra("aspectX", 1); //크롭 박스의 x축 비율
                intent.putExtra("aspectY", 1); //크롭 박스의 y축 비율
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent,TAKE_PICTURE);
                break;
            }
        }
    }
}
