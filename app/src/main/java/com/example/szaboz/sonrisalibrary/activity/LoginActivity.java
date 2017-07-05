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
        overridePendingTransition(R.anim.appear, R.anim.disappear);


        if (findViewById(R.id.fragment_container_login) != null){
            if (savedInstanceState != null){
                return;
            }
            LoginFragment loginFragment = LoginFragment.newInstance();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fragment_container_login, loginFragment).commit();
        }
    }


    @Override
    public void onSignUpPressed() {
        SignUpFragment signUpFragment = SignUpFragment.newInstance();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.appear, R.animator.disappear);
        transaction.replace(R.id.fragment_container_login, signUpFragment);
        transaction.addToBackStack(signUpFragment.toString());
        transaction.commit();
    }

    @Override
    public void onLoginPressed() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

}
