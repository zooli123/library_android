package com.example.szaboz.sonrisalibrary.connection;

import com.example.szaboz.sonrisalibrary.activity.LoginActivity;
import com.example.szaboz.sonrisalibrary.bean.User;
import com.jakewharton.retrofit.Ok3Client;

import retrofit.RestAdapter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiManager {
    public interface MyAPI {
        @GET("/")
        Call<User> getUser(@Path("email") String username);

    }
    public static final String ENDPOINT = "https://192.168.87.171:8443/";
    //Member of Retrofit Library - creates adapter object
    //TODO: Remove Debugging during QA phase
    public static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(ENDPOINT)
            .setClient(new Ok3Client(LoginActivity.trustClient))
            .build();
    private static final MyAPI MYAPI = REST_ADAPTER.create(MyAPI.class);

    public static MyAPI getService() {
        return MYAPI;
    }
}