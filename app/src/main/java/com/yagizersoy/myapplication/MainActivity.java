package com.yagizersoy.myapplication;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView, textView2;
    int score;
    int screenX, screenY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bu kısmı internetten araştırıp buldum
        //Ekran yükseklik ve genişliğini buluyoruz
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenX = size.x;
        screenY = size.y;

        score = 0;

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        Toast toast;

        startGame();
    }

    public void startGame(){
        textView2.setText("Score: " + (score = 0));
        new CountDownTimer(10000,600){
            public void onTick(long milisaniye){

                textView.setText("Time: " + milisaniye / 1000);
                float rndmX = new Random().nextInt(screenX - imageView.getMeasuredWidth());
                float rndmY = new Random().nextInt(screenY - 2 * imageView.getMeasuredHeight());
                imageView.setX(rndmX);
                imageView.setY(rndmY);
            }
            public void onFinish(){
                textView.setText("Time is Finish!");
                imageView.setVisibility(View.INVISIBLE);

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Do you want try again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startGame();
                        imageView.setVisibility(View.VISIBLE);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over.", Toast.LENGTH_LONG);
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void setImageView(View view){
        score++;
        textView2.setText("Score: " + score);
    }
}