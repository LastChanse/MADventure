package com.example.madventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class LaunchActivity extends AppCompatActivity {

    ImageView background;
    LinearLayout launchLogo;
    MediaPlayer mediaPlayer;
    boolean skip = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        // Привязки
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sandship);
        background = (ImageView) findViewById(R.id.launchBackground);
        launchLogo = (LinearLayout) findViewById(R.id.launchLogo);

        // При касании по фону можно пропустить заставку
        background.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mediaPlayer.stop();
                background.clearAnimation();
                launchLogo.clearAnimation();
                launchLogo.setAlpha(1f);
                skip = true;
                startActivity(new Intent(LaunchActivity.this, OnBoardingActivity.class));
                return false;
            }
        });

        // Музыка
        class LongAndComplicatedTask extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... noargs) {
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                mediaPlayer.start();
            }
        }

        LongAndComplicatedTask longTask = new LongAndComplicatedTask(); // Создаем экземпляр
        longTask.execute(); // запускаем

        // Заставка
        // Движение фона
        Animation bgAnim = AnimationUtils.loadAnimation(this, R.anim.launch_background);
        background.startAnimation(bgAnim);

        // Появление лого с названием
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Animation lgAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.launch_logo);
                launchLogo.setAlpha(1);
                launchLogo.startAnimation(lgAnim);
            }
        }, 30000);

        // Переход на следующий экран
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!skip) {
                    startActivity(new Intent(LaunchActivity.this, OnBoardingActivity.class));
                }
            }
        }, 35000
        );
    }
}