package edu.bzu.assignmentone.bzutodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import edu.bzu.assignmentone.bzutodo.Auth.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createAnimationSplash ();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        createAnimationFadeImg ();
    }

    public void createAnimationSplash () {
        ConstraintLayout constraintLayout = findViewById(R.id.splashLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }

    public void createAnimationFadeImg () {

        Animation animateView = AnimationUtils.loadAnimation(this,R.anim.fadelogo);
        mImageView = findViewById(R.id.bzuNoteLogo);
        animateView.reset();
        mImageView.clearAnimation();
        mImageView.setAnimation(animateView);

        animateView.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent nextActivityIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(nextActivityIntent);
                MainActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}