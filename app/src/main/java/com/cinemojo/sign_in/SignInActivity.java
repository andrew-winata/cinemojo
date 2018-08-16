package com.cinemojo.sign_in;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cinemojo.CinemojoApplication;
import com.cinemojo.R;
import com.cinemojo.core.model.UserCredentials;
import com.cinemojo.core.repository.LoginSessionRepository;
import com.cinemojo.databinding.ActivityLoginBinding;

import org.apache.commons.lang3.StringUtils;

public class SignInActivity extends AppCompatActivity {

    private ActivityLoginBinding viewBinding;
    private LoginSessionRepository loginSessionRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginSessionRepository = CinemojoApplication.getInstance().getLoginSessionRepository();
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewBinding.buttonSignIn.setOnClickListener(__ -> {
            this.validateAndSaveUserCredentials();
        });
    }

    private void validateAndSaveUserCredentials() {
        boolean isValid = true;
        String username = viewBinding.inputLogin.getText().toString();
        String password = viewBinding.inputPassword.getText().toString();

        if (StringUtils.isEmpty(username)) {
            viewBinding.layoutInputLogin.setError("Please input your username");
            isValid = false;
        }

        if (StringUtils.isEmpty(password)) {
            viewBinding.layoutInputPassword.setError("Please input your password");
            isValid = false;
        }

        if (isValid) {
            UserCredentials userCredentials = new UserCredentials();
            userCredentials.setUsername(username);
            userCredentials.setPassword(password);
            loginSessionRepository.saveUserCredentials(userCredentials);
            this.finish();
        }
    }
}
