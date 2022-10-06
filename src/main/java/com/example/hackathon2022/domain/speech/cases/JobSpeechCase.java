package com.example.hackathon2022.domain.speech.cases;

import com.example.hackathon2022.domain.job.presentation.dto.response.JobListResponseDto;
import com.example.hackathon2022.domain.job.service.JobService;
import com.example.hackathon2022.domain.speech.type.IntendType;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("jobCase")
@RequiredArgsConstructor
@Primary
@Slf4j
public class JobSpeechCase implements SpeechUnit {
    private final JobService jobService;

    private final List<String> CANDIDATES = List.of("NNP", "NNG", "VV");

    @Override
    public List<IntendType> matchIntends() {
        return List.of(IntendType.JOB_ANSWER_FOUND);
    }

    private String getRegions(List<Token> tokens) {
        List<String> filtered = tokens.stream().filter(it -> "NNG".equals(it.getPos()) || "NNP".equals(it.getPos()))
                .map(Token::getMorph)
                .collect(Collectors.toList());

        String answer = null;

        if (filtered.contains("서울특별시"))
            answer = "서울특별시";
        if (filtered.contains("부산광역시"))
            answer = "BUSAN";
        if (filtered.contains("대구광역시"))
            answer = "DAEGU";
        if (filtered.contains("인천광역시"))
            answer = "INCHEON";
        if (filtered.contains("광주광역시"))
            answer = "광주광역시";
        if (filtered.contains("대전광역시"))
            answer = "DAEJEON";
        if (filtered.contains("울산광역시"))
            answer = "ULSAN";
        if (filtered.contains("세종특별자치시"))
            answer = "SEJONG";
        if (filtered.contains("경기도"))
            answer = "경기도";
        if (filtered.contains("강원도"))
            answer = "GWANGWON";
        if (filtered.contains("충청북도"))
            answer = "CHUNGBUK";
        if (filtered.contains("충청남도"))
            answer = "CHUNGNAM";
        if (filtered.contains("전라북도"))
            answer = "JEONBUK";
        if (filtered.contains("전라남도"))
            answer = "JEONNAM";
        if (filtered.contains("경상북도"))
            answer = "GYUNGBUK";
        if (filtered.contains("경상남도"))
            answer = "GYUNGNAM";
        if (filtered.contains("제주특별자치도"))
            answer = "JEJU";

        return answer;

    }

    private String getKeywords(List<Token> tokens) {
        List<String> filtered = tokens.stream().filter(it -> "NNG".equals(it.getPos()) || "NNP".equals(it.getPos()))
                .map(Token::getMorph)
                .collect(Collectors.toList());

        String answer = null;
        if (filtered.contains("시니어"))
            answer = "시니어";

        if (filtered.contains("보건업"))
            answer = "보건업";

        if (filtered.contains("제조"))
            answer = "제조";

        if (filtered.contains("어린이집"))
            answer = "어린이집";

        return answer;
    }

    @Override
    public boolean matches(List<Token> tokens) {

        List<String> filtered = tokens.stream().filter(it -> CANDIDATES.contains(it.getPos()))
                .map(it -> it.getMorph())
                .collect(Collectors.toList());

        return filtered.contains("일자리") || filtered.contains("찾아줘") || filtered.contains("찾아주세요");
    }

    @Override
    public SpeechResult execute(List<Token> tokens, Optional<SpeechResult> lastAnswer) {
        SpeechResult speechResult = SpeechResult.builder()
                .metaData(lastAnswer.isPresent() ? lastAnswer.get().getMetaData() : new HashMap<>())
                .build();
        if (lastAnswer.isPresent()) {
            log.info("연속 채팅 감지");
            switch (lastAnswer.get().getIntendType()) {
//                case JOB_ANSWER_FOUND:
//                    log.info("일자리 지역 입력이 들어왔습니다");
//                    if (!lastAnswer.get().getMetaData().containsKey("job")) {
//                        lastAnswer.get().getMetaData().put("target", getJobs(tokens));
//                        speechResult.setIntendType(IntendType.JOB_ANSWER_FOUND);
//                        speechResult.setText("필요하신 복지를 말씀해주세요");
//                        speechResult.setRecordThis(true);
//                    }
                case JOB_ANSWER_FOUND_KEYWORD:
                    log.info("일자리 지역, 키워드 입력이 들어왔습니다");
                    if (!lastAnswer.get().getMetaData().containsKey("keyword")) {
                        speechResult.setIntendType(null);
                        speechResult.setText(getResultString(getRegions(tokens), getKeywords(tokens)));
                        speechResult.setRecordThis(false);
                    } else {
                        speechResult.setIntendType(IntendType.WELFARE_ANSWER_BENEFIT);
                        speechResult.setText("필요하신 복지를 말씀해주세요");
                        speechResult.setRecordThis(true);
                    }
            }
            return speechResult;

        } else {
        speechResult.setIntendType(IntendType.JOB_ANSWER_FOUND_KEYWORD);
        speechResult.setText("일자리 지역, 원하시는 직장을 검색해주세요.");
        speechResult.setRecordThis(true);
        return speechResult;
    }

}
    private String getResultString(String region, String keyword) {

        log.info(region);
        log.info(keyword);

        JobListResponseDto response = jobService.jobList(region, 0, 10, keyword);

        List<String> list = response.getList().stream()
                .limit(2)
                .map(it ->
                        String.format("%s %s에서 %s에 일자리가 있네요.", it.getRegion(), it.getRegionDetail(), it.getCompanyName())
                )
                .collect(Collectors.toList());
        //.collect(Collectors.joining(" 그리고 "));

        String joined = list.get(new Float(Math.floor(Math.random() * list.size())).intValue());
        joined = joined + " 이 일자리가 도움이 되셨으면 좋겠네요.";

        return joined;
    }
}

