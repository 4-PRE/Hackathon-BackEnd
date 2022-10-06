package com.example.hackathon2022.domain.speech.cases;

import com.example.hackathon2022.domain.speech.type.IntendType;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface SpeechUnit {
    List<IntendType> matchIntends();
    boolean matches(List<Token> tokens);

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    class SpeechResult {
        private boolean recordThis;
        private IntendType intendType;
        private String text;
        private Map<String, Object> metaData;
    }

    SpeechResult execute(List<Token> tokens, Optional<SpeechResult> lastAnswer);
}
