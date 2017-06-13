package com.example.szaboz.sonrisalibrary;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class LoginActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
            LoginFragment loginFragment = new LoginFragment();
            loginFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, loginFragment).commit();
        }


    }



}
