package com.projects.naduni.eventplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asplashscreen);
        img= (ImageView)findViewById(R.id.imageView3);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.splashscrenntransitions);
        img.startAnimation(myanim);
        final Intent i = new Intent(this,MainActivity.class);
        Thread timer = new Thread() {

            public void run() {

                try {

                    sleep(5000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
            timer.start();

    }



}
