package com.example.szaboz.sonrisalibrary.connection;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by szaboz on 2017.07.12..
 */

public class SSLConnector extends AsyncTask<String, Void, String> {
    private String messageFromServer = "No message came from server.";
    private static final String SERVER_ADDRESS = "https://192.168.87.171:8443";
    private SSLKeyVerifier sslKeyVerifier;

    public SSLConnector(SSLKeyVerifier sslKeyVerifier) {
        this.sslKeyVerifier = sslKeyVerifier;
    }

    @Override
    protected String doInBackground(String... params) {
        setMessageFromServer();
        return messageFromServer;
    }

    private void setMessageFromServer() {
        try {
            HttpsURLConnection urlConnection = connectToServer();
            urlConnection.setSSLSocketFactory(sslKeyVerifier.getContext().getSocketFactory());
            InputStream in = urlConnection.getInputStream();
            messageFromServer = in.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HttpsURLConnection connectToServer() throws IOException {
        URL url = new URL(SERVER_ADDRESS);
        HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
        return urlConnection;
    }

    public String getMessageFromServer() {
        return messageFromServer;
    }
}
