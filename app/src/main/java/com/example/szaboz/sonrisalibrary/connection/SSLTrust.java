package com.example.szaboz.sonrisalibrary.connection;

import android.content.Context;

import com.example.szaboz.sonrisalibrary.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import okhttp3.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by szaboz on 2017.07.12..
 */

public class SSLTrust {
    public static OkHttpClient trustcert(Context context){
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            KeyStore ksTrust = KeyStore.getInstance("BKS");
            InputStream instream = context.getResources().openRawResource(R.raw.mykeystore);
            ksTrust.load(instream, "pwdpwd".toCharArray());
            // TrustManager decides which certificate authorities to use.
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ksTrust);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            okHttpClient = new OkHttpClient.Builder().sslSocketFactory(sslContext.getSocketFactory()).build();
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | KeyManagementException e) {
            e.printStackTrace();
        }
        return okHttpClient;
    }
}
