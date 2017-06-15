package com.example.szaboz.sonrisalibrary;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginFragment extends Fragment {
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    LoginPageListeners loginPageListener;
    public interface LoginPageListeners {
        void onSignUpPressed();
        void onLoginPressed();
    }

    public void handleSignUpClick(){
        loginPageListener.onSignUpPressed();
    }
    public void handleLoginClick(){
        loginPageListener.onLoginPressed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            loginPageListener = (LoginPageListeners) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement LoginPageListeners");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button signUpBtn = (Button) view.findViewById(R.id.btn_toSignUp);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignUpClick();
            }
        });

        Button loginBtn = (Button) view.findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLoginClick();
            }
        });
        return view;
    }
}
