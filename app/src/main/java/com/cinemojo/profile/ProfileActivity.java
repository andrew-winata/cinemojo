package com.cinemojo.profile;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cinemojo.CinemojoApplication;
import com.cinemojo.R;
import com.cinemojo.core.repository.LoginSessionRepository;
import com.cinemojo.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding viewBinding;
    private LoginSessionRepository loginSessionRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginSessionRepository = CinemojoApplication.getInstance().getLoginSessionRepository();
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        viewBinding.textWelcome.setText(getString(R.string.welcome_profile, loginSessionRepository.getUsername()));
        viewBinding.buttonSignOut.setOnClickListener(__ -> {
            loginSessionRepository.signOut();
            this.finish();
        });
    }
}
