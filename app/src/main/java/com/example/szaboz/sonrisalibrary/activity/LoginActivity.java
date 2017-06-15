package com.example.szaboz.sonrisalibrary.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.szaboz.sonrisalibrary.fragment.LoginFragment;
import com.example.szaboz.sonrisalibrary.R;
import com.example.szaboz.sonrisalibrary.fragment.SignUpFragment;

public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginPageListeners {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
            LoginFragment loginFragment = LoginFragment.newInstance();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, loginFragment).commit();
        }
    }


    @Override
    public void onSignUpPressed() {
        SignUpFragment signUpFragment = SignUpFragment.newInstance();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, signUpFragment);
        transaction.addToBackStack(signUpFragment.toString());
        transaction.commit();
    }

    @Override
    public void onLoginPressed() {
        Intent menuIntent = new Intent(this, MainActivity.class);
        startActivity(menuIntent);
    }

}
