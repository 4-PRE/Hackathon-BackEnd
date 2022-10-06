package com.example.hackathon2022.domain.speech.presentation;

import com.example.hackathon2022.domain.speech.presentation.dto.response.SpeechResponse;
import com.example.hackathon2022.domain.speech.service.SpeechService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
@RequestMapping("/speech")
@RestController
public class SpeechController {

    private final SpeechService speechService;

    @GetMapping
    public SpeechResponse speech(@RequestParam("speech") String speechString, @RequestParam(value = "continuous_code", defaultValue = "") String continuousCode) {
        return speechService.execute(speechString, continuousCode);
    }
}
