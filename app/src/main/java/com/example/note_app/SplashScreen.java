package com.example.note_app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar progrssBarAnimation;
    private ObjectAnimator progressAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();

        progressAnimator.setDuration(7000);


        progressAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                progrssBarAnimation.setVisibility(View.GONE);
                startActivity(new Intent(getApplicationContext(), MainActivity3.class));
            }
        });

    }

    private void init() {
        progrssBarAnimation = findViewById(R.id.progressBar);
        progressAnimator = ObjectAnimator.ofInt(progrssBarAnimation,"progress",0,100);
        progressAnimator.start();
    }


}