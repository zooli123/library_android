package com.example.szaboz.sonrisalibrary;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class LoginActivity extends FragmentActivity implements LoginFragment.ClickedOnSignUpListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
            LoginFragment loginFragment = new LoginFragment();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, loginFragment).commit();
        }
    }


    @Override
    public void onSignUpPressed() {
        SignUpFragment signUpFragment = new SignUpFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, signUpFragment);
        transaction.addToBackStack(signUpFragment.toString());
        transaction.commit();
    }
}
