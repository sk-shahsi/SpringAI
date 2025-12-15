package com.openAI.LamaSpringAi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;

@Service
public class TextToSpeechService {
    @Value("${tts.voicerss.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public void convert(String text) throws Exception {

        String url =
                "https://api.voicerss.org/?" +
                        "key=" + apiKey +
                        "&hl=en-us" +
                        "&src=" + text +
                        "&c=MP3";

        byte[] audioBytes = restTemplate.getForObject(url, byte[].class);

        try (FileOutputStream fos = new FileOutputStream("output.mp3")) {
            fos.write(audioBytes);
        }
    }
}
