package com.openAI.LamaSpringAi.controller;

import com.openAI.LamaSpringAi.service.TextToSpeechService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tts")
public class TtsController {

    private final TextToSpeechService ttsService;

    public TtsController(TextToSpeechService ttsService) {
        this.ttsService = ttsService;
    }

    @PostMapping
    public String speak(@RequestBody String text) throws Exception {
        ttsService.convert(text);
        return "Audio generated successfully as output.mp3";
    }
}
