package com.example.chemm.youtubeapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Animation alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mImageView = (ImageView) findViewById(R.id.title_screen_image_view);
        initialize();
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                    Intent intent = new Intent(SplashScreenActivity.this, SearchActivity.class);
                    startActivity(intent);
                    finish();
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
            }
        };
        thread.start();
    }

    private void initialize(){
        alpha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        mImageView.startAnimation(alpha);
    }


}
