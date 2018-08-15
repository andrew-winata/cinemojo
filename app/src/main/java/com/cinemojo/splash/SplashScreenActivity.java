package com.cinemojo.splash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cinemojo.R;
import com.cinemojo.databinding.ActivitySplashScreenBinding;
import com.cinemojo.core.util.Router;

public class SplashScreenActivity extends AppCompatActivity {

    private ActivitySplashScreenBinding viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Router.goToLogin(this);
        }, 3000);
    }
}
