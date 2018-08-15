package com.blibli.cinemojo.splash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.blibli.cinemojo.R;
import com.blibli.cinemojo.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    private ActivitySplashScreenBinding viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        crossfade(viewBinding.splashContainer, viewBinding.splashTitle, 1000, 1000);

    }

    private void crossfade(ViewGroup sceneRoot, View targetView, long duration, long startDelay) {
        TransitionManager.beginDelayedTransition(sceneRoot, new Fade().setDuration(duration).setStartDelay(startDelay).addTarget(targetView));
    }
}
