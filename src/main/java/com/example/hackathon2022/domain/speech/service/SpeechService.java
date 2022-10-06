package com.example.hackathon2022.domain.speech.service;

import com.example.hackathon2022.domain.speech.cases.SpeechUnit;
import com.example.hackathon2022.domain.speech.presentation.dto.response.SpeechResponse;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class SpeechService {
    private final Komoran komoran;

    private final ConcurrentHashMap<UUID, SpeechUnit.SpeechResult> records = new ConcurrentHashMap<>();

    public SpeechResponse execute(String speech, String continuousCode) {
        final List<SpeechUnit> units = List.of();

        List<Token> tokens = komoran.analyze(speech).getTokenList();
        Optional<SpeechUnit> matchUnit = units.stream().filter(it -> it.matches(tokens)).findFirst();
        if(matchUnit.isPresent()) {
            SpeechUnit.SpeechResult result = matchUnit.get().execute(tokens,
                    Optional.ofNullable(records.get(UUID.fromString(continuousCode))));

            UUID uuid = UUID.randomUUID();
            if(result.isRecordThis())
                records.put(uuid, result);

            return SpeechResponse.builder()
                    .continuousCode(result.isRecordThis() ? uuid.toString() : null)
                    .text(result.getText())
                    .build();
        }else {
            return SpeechResponse.builder()
                    .continuousCode(null)
                    .text("무슨 답변을 드려야 할지 잘 모르겠어요")
                    .build();
        }
    }

}
