package com.example.szaboz.sonrisalibrary.activity;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.AsyncTask;
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

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginPageListeners {
    private List<User> users;
    public static OkHttpClient trustClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserFactory uf = new UserFactory();
        users = uf.getAllUsers();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkUtil networkJob = new NetworkUtil();
        networkJob.execute();


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

    private class NetworkUtil extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            trustClient = SSLTrust.generateClient(LoginActivity.this);

            Request request = new Request.Builder()
                    .url(ApiManager.ENDPOINT)
                    .build();

            try {
                Response response = trustClient.newCall(request).execute();
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
//                builder1.setMessage(response.message());
//                builder1.setCancelable(true);
//
//                builder1.setPositiveButton(
//                        "Yes",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//
//                builder1.setNegativeButton(
//                        "No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//
//                AlertDialog alert11 = builder1.create();
//                alert11.show();




            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String message) {
            //process message
        }
    }
}
