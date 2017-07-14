package com.example.szaboz.sonrisalibrary.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.szaboz.sonrisalibrary.bean.User;
import com.example.szaboz.sonrisalibrary.connection.ApiManager;
import com.example.szaboz.sonrisalibrary.connection.SSLTrust;
import com.example.szaboz.sonrisalibrary.factory.UserFactory;
import com.example.szaboz.sonrisalibrary.fragment.LoginFragment;
import com.example.szaboz.sonrisalibrary.R;
import com.example.szaboz.sonrisalibrary.fragment.SignUpFragment;
import com.jakewharton.retrofit.Ok3Client;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit.RestAdapter;

public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginPageListeners {
    private List<User> users;
    public static OkHttpClient trustClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserFactory uf = new UserFactory();
        users = uf.getAllUsers();
        trustClient = SSLTrust.trustcert(this);
        String ENDPOINT = "https://192.168.87.171:8443";

        setContentView(R.layout.activity_login);
        RestAdapter REST_ADAPTER = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setClient(new Ok3Client(trustClient))
                .build();

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

        EditText email = (EditText)findViewById(R.id.text_email);
        EditText password = (EditText)findViewById(R.id.text_pwd);



//        for(User user : users) {
//            if(user.email.equals(email.getText().toString()) && user.password.equals(password.getText().toString())) {
//                Intent mainIntent = new Intent(this, MainActivity.class);
//                String username = user.email.split("@")[0];
//                mainIntent.putExtra("username", username);
//                startActivity(mainIntent);
//                finish();
//            }
//        }
    }
}
