package com.example.hackathon2022.domain.speech.presentation.dto.response.service;

import com.example.hackathon2022.domain.speech.cases.SpeechUnit;
import com.example.hackathon2022.domain.speech.presentation.dto.response.SpeechResponse;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
@RequiredArgsConstructor
@Service
public class SpeechService {
    private final Komoran komoran;

    @Qualifier("welfareCase")
    private final SpeechUnit welfareUnit;

    @Qualifier("jobCase")
    private final SpeechUnit jobSpeechUnit;

    private final ConcurrentHashMap<UUID, SpeechUnit.SpeechResult> records = new ConcurrentHashMap<>(); // records 값에는 뭐 들어가냐

    public SpeechResponse execute(String speech, String continuousCode) {
        log.info("speech: {}, code: {}", speech, continuousCode);
        final List<SpeechUnit> units = List.of(welfareUnit, jobSpeechUnit);

        List<Token> tokens = komoran.analyze(speech).getTokenList();
        Optional<SpeechUnit> matchUnit = units.stream().filter(it -> {
            if(it.matches(tokens)) {
                return true;
            }

            if(!"".equals(continuousCode)) {
                return it.matchIntends().contains(records.get(UUID.fromString(continuousCode)).getIntendType());
            }

            return false;
        }).findFirst();
        if(matchUnit.isPresent()) { // 전에 했던 대화 이어주는 if 문
            log.info("found");
            SpeechUnit.SpeechResult result = matchUnit.get().execute(tokens,
                    Optional.ofNullable("".equals(continuousCode) ? null : records.get(UUID.fromString(continuousCode)))); // null 허용
            // uuid로 비교해서 null 이면 null 다르면 uuid로 key값 해서 value 가져옴
            // token에 프론트 요청 값 넣고 말 이어가는 uuid 생성

            log.info(result.getText());

            UUID uuid = UUID.randomUUID();

            log.info(uuid);

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
