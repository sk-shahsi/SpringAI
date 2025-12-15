package com.openAI.LamaSpringAi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

@Service
public class TextToSpeechService {

    private final String apiKey;

    public TextToSpeechService() {
        this.apiKey = System.getenv("TTS_API_KEY"); // read from environment
        if (apiKey == null || apiKey.isEmpty()) {
            throw new RuntimeException("TTS_API_KEY environment variable not set!");
        }
    }

    private RestTemplate getUnsafeRestTemplate() throws Exception {
        // Trust all SSL certificates
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        }}, new SecureRandom());

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        return new RestTemplate();
    }

    public void convert(String text) throws Exception {
        String url = "https://api.voicerss.org/?key=" + apiKey + "&hl=en-us&src=" + text + "&c=MP3";
        byte[] audioBytes = getUnsafeRestTemplate().getForObject(url, byte[].class);

        try (FileOutputStream fos = new FileOutputStream("output.mp3")) {
            fos.write(audioBytes);
        }
        
    }
}
