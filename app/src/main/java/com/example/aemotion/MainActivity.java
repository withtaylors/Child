package com.example.aemotion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aemotion.ml.ModelT;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MainActivity extends AppCompatActivity {

    TextView result, confidence;
    ImageView imageView;
    Button picture;
    int imageSize = 224;
    final static int TAKE_PICTURE = 1;
    final static int CROP_PICTURE = 2;
    private Uri pictureUri;

    int CheckON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckON = VO.getCheckON();

        confidence = findViewById(R.id.confidence);
        result = findViewById(R.id.result);
        picture = findViewById(R.id.picture);
        imageView = findViewById(R.id.imageView);

        //Uri exposure 무시
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    }

    public void classifyImage(Bitmap image) {
        try {
            ModelT model = ModelT.newInstance(getApplicationContext());

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
            ModelT.Outputs outputs = model.process(inputFeature0);
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

            if (CheckON == 1){
                if(classes[maxPos] == "happy"){
                    result.setText(classes[maxPos]);
                }
                else{
                    Toast.makeText(MainActivity.this, "기쁜 표정이 아니에요! 표정을 다시 한번 지어보세요 :)", Toast.LENGTH_SHORT).show();
                    result.setText("기쁜표정아님");
                }
            }else if (CheckON == 2) {
                if (classes[maxPos] == "sad") {
                    result.setText(classes[maxPos]);
                } else {
                    Toast.makeText(MainActivity.this, "슬픈 표정이 아니에요! 표정을 다시 한번 지어보세요 :)", Toast.LENGTH_SHORT).show();
                    result.setText("슬픈표정아님");
                }
            }else if (CheckON == 3) {
                if (classes[maxPos] == "surprise") {
                    result.setText(classes[maxPos]);
                } else {
                    Toast.makeText(MainActivity.this, "놀란 표정이 아니에요! 표정을 다시 한번 지어보세요 :)", Toast.LENGTH_SHORT).show();
                    result.setText("놀란표정아님");
                }
            }else if (CheckON == 4) {
                if (classes[maxPos] == "angry") {
                    result.setText(classes[maxPos]);
                } else {
                    Toast.makeText(MainActivity.this, "화난 표정이 아니에요! 표정을 다시 한번 지어보세요 :)", Toast.LENGTH_SHORT).show();
                    result.setText("화난표정아님");
                }
            }

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }

    //사진 동그라미 테두리로 나오게하는 코드
    private Bitmap getRoundedCroppedBitmap(Bitmap image) {
        int widthLight = image.getWidth();
        int heightLight = image.getHeight();

        Bitmap output = Bitmap.createBitmap(image.getWidth(), image.getHeight(),
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        Paint paintColor = new Paint();
        paintColor.setFlags(Paint.ANTI_ALIAS_FLAG);

        RectF rectF = new RectF(new Rect(0, 0, widthLight, heightLight));

        canvas.drawRoundRect(rectF, widthLight / 2, heightLight / 2, paintColor);

        Paint paintImage = new Paint();
        paintImage.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(image, 0, 0, paintImage);

        return output;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //크롭된 이미지 가져와서 이미지뷰에 보여주기
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK || data.hasExtra("data")) { //데이터를 가지고 있는지 확인
                    System.out.println("datain8888!!");
                    final Bundle extras = data.getExtras();

                    if (extras != null) {
                        Bitmap image = extras.getParcelable("data"); //크롭한 이미지 가져오기
                        int dimension = Math.min(image.getWidth(), image.getHeight());
                        image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                        Bitmap a = getRoundedCroppedBitmap(image);
                        imageView.setImageBitmap(a);// 크롭한 이미지 배치하기
                        a = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                        classifyImage(a);
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
                startActivityForResult(intent, TAKE_PICTURE);

                break;
            }
        }

    }
}