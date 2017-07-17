package com.example.szaboz.sonrisalibrary.connection;

import android.content.Context;
import android.support.annotation.NonNull;

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

public class SSLTrust {
    public static OkHttpClient generateClient(Context context){
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            KeyStore ksTrust = generateKeyStore(context);
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

    @NonNull
    private static KeyStore generateKeyStore(Context context) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException {
        KeyStore ksTrust = KeyStore.getInstance("BKS");
        InputStream instream = context.getResources().openRawResource(R.raw.kstore);
        ksTrust.load(instream, "pwdpwd".toCharArray());
        return ksTrust;
    }
}
