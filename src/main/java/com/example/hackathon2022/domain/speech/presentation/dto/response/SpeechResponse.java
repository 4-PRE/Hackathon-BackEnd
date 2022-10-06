package com.example.hackathon2022.domain.speech.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SpeechResponse {
    private String text;
    private String continuousCode;
}
