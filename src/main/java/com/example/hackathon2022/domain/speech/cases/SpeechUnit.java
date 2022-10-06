package com.example.hackathon2022.domain.speech.cases;

import com.example.hackathon2022.domain.speech.type.IntendType;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpeechUnit {
    boolean matches(List<Token> tokens);

    @Getter
    @AllArgsConstructor
    @Builder
    class SpeechResult {
        private boolean recordThis;
        private IntendType intendType;
        private String text;
    }

    SpeechResult execute(List<Token> tokens, Optional<SpeechResult> lastAnswer);
}
