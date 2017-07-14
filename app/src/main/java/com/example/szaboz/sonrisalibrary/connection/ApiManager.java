package com.example.szaboz.sonrisalibrary.connection;

import com.example.szaboz.sonrisalibrary.activity.LoginActivity;
import com.jakewharton.retrofit.Ok3Client;

import javax.security.auth.callback.Callback;

import retrofit.RestAdapter;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiManager {
    public interface MyAPI {
        @GET("/path")
        public void voyager(@Query("var1") String var1, @Query("var2") String var2,
                            Callback response);
    }
    private static final String ENDPOINT = "https://192.168.87.171:8443";
    //Member of Retrofit Library - creates adapter object
    //TODO: Remove Debugging during QA phase
    public static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(ENDPOINT)
            .setClient(new Ok3Client( LoginActivity.trustClient))
            .build();
    private static final MyAPI MYAPI = REST_ADAPTER.create(MyAPI.class);
    public static MyAPI getService() {
        return MYAPI;
    }
}