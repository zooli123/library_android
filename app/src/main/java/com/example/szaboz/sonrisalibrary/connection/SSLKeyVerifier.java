package com.example.szaboz.sonrisalibrary.connection;

import com.example.szaboz.sonrisalibrary.R;
import com.example.szaboz.sonrisalibrary.activity.LoginActivity;
import com.example.szaboz.sonrisalibrary.activity.MainActivity;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by szaboz on 2017.07.12..
 */

public class SSLKeyVerifier {
    private KeyStore keyStore;
    private TrustManagerFactory trustManagerFactory;
    private SSLContext context;
    private InputStream inputStream;

    public SSLKeyVerifier(InputStream inputStream) {
        this.inputStream = inputStream;
        initSSLContext();
    }

    private void initSSLContext() {
        try {
            context = SSLContext.getInstance("TLS");
            initTrustManagerFactory();
            context.init(null, trustManagerFactory.getTrustManagers(), null);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void initTrustManagerFactory() throws NoSuchAlgorithmException, KeyStoreException {
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm);
        setKeyStore();
        trustManagerFactory.init(keyStore);
    }

    private void setKeyStore() {
        String keyStoreType = KeyStore.getDefaultType();
        try {
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("cert", loadCert());
            this.keyStore = keyStore;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Certificate loadCert() throws CertificateException, IOException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        InputStream caInput = new BufferedInputStream(inputStream);
        Certificate cert;
        try {
            cert = certificateFactory.generateCertificate(caInput);
        } finally {
            caInput.close();
        }

        return cert;
    }

    public SSLContext getContext() {
        return context;
    }





}
