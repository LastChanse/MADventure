package com.example.madventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        // Музыка
        class LongAndComplicatedTask extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... noargs) {
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sandship);
                mediaPlayer.start();
            }
        }

        LongAndComplicatedTask longTask = new LongAndComplicatedTask(); // Создаем экземпляр
        longTask.execute(); // запускаем

        // Заставка
        // Движение фона
        background = (ImageView) findViewById(R.id.launchBackground);
        Animation bgAnim = AnimationUtils.loadAnimation(this, R.anim.launch_background);
        background.startAnimation(bgAnim);

        // Появление лого с названием
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                launchLogo = (LinearLayout) findViewById(R.id.launchLogo);
                Animation lgAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.launch_logo);
                launchLogo.setAlpha(1);
                launchLogo.startAnimation(lgAnim);
            }
        }, 30000);

        // Переход на следующий экран
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchActivity.this, OnBoardingActivity.class));
            }
        }, 35000);
    }
}