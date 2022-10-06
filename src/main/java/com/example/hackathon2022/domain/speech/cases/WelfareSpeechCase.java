package com.example.hackathon2022.domain.speech.cases;

import com.example.hackathon2022.domain.speech.type.IntendType;
import com.example.hackathon2022.domain.welfare.component.WelfareInfoStorage;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareQueryResponse;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareResponse;
import com.example.hackathon2022.domain.welfare.service.WelfareService;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor
public class WelfareSpeechCase implements SpeechUnit {
    private final WelfareService welfareService;
    private final WelfareInfoStorage welfareInfoStorage;
    private final List<String> CANDIDATES = List.of("NNP", "NNG", "VV");

    @Override
    public List<IntendType> matchIntends() {
        return List.of(IntendType.WELFARE_ANSWER_BENEFIT, IntendType.WELFARE_ANSWER_TARGET);
    }

    @Override
    public boolean matches(List<Token> tokens) {
        //tokens.forEach(it -> log.info("POS: {} / value: {}", it.getPos(), it.getMorph()));

        List<String> filtered = tokens.stream().filter(it -> CANDIDATES.contains(it.getPos()))
                .map(it -> it.getMorph())
                .collect(Collectors.toList());

        return filtered.contains("복지") || filtered.contains("지원");
    }

    /*private Optional<String> getRegion(List<Token> tokens) {
        List<String> filtered = tokens.stream().filter(it -> "NNP".equals(it.getPos()))
                .map(Token::getMorph)
                .filter(it -> it.endsWith("도") || it.endsWith("시") || it.endsWith("구"))
                .collect(Collectors.toList());

        String joined = filtered.stream().collect(Collectors.joining(" "));
        return Optional.ofNullable("".equals(joined) ? null : joined);
    }*/

    private List<String> getFeatures(List<Token> tokens) {
        List<String> filtered = tokens.stream().filter(it -> "NNG".equals(it.getPos()))
                .map(Token::getMorph)
                .collect(Collectors.toList());

        List<String> answer = new ArrayList<>();
        if((filtered.contains("기초") || filtered.contains("생활")) && filtered.contains("수급"))
            answer.add("LOW_INCOME");
        if(filtered.contains("장애"))
            answer.add("DISABLED");
        if(filtered.contains("참전") || filtered.contains("베트남전") || filtered.contains("육이오"))
            answer.add("VETERAN");

        return answer;
    }

    private List<String> getBenefits(List<Token> tokens) {
        List<String> filtered = tokens.stream().filter(it -> "NNG".equals(it.getPos()) || "NNP".equals(it.getPos()))
                .map(Token::getMorph)
                .collect(Collectors.toList());
        filtered.forEach(it -> log.info("{}", it));

        List<String> answer = new ArrayList<>();
        if((filtered.contains("교육") || filtered.contains("공부")) || filtered.contains("학교"))
            answer.add("EDUCATION");
        if((filtered.contains("의료") || filtered.contains("병원")) || filtered.contains("치료"))
            answer.add("MEDICAL");
        if((filtered.contains("문화") || filtered.contains("영화")) || filtered.contains("공연"))
            answer.add("CULTURE");
        if((filtered.contains("경제") || filtered.contains("돈")) || filtered.contains("대출"))
            answer.add("FINANCIAL");

        return answer;
    }

    @Override
    public SpeechResult execute(List<Token> tokens, Optional<SpeechResult> lastAnswer) {
        SpeechResult speechResult = SpeechResult.builder()
                .metaData(lastAnswer.isPresent() ? lastAnswer.get().getMetaData() : new HashMap<>())
                .build();
        if(lastAnswer.isPresent()) {
            log.info("연속 채팅 감지");
            switch (lastAnswer.get().getIntendType()) {
                case WELFARE_ANSWER_TARGET:
                    log.info("대상자 입력이 들어왔습니다");
                    if(!lastAnswer.get().getMetaData().containsKey("target")) {
                        lastAnswer.get().getMetaData().put("target", getFeatures(tokens));
                        speechResult.setIntendType(IntendType.WELFARE_ANSWER_BENEFIT);
                        speechResult.setText("필요하신 복지를 말씀해주세요");
                        speechResult.setRecordThis(true);
                    }
                case WELFARE_ANSWER_BENEFIT:
                    log.info("혜택 입력이 들어왔습니다");
                    List<String> benefitCodes = getBenefits(tokens);
                    if(!lastAnswer.get().getMetaData().containsKey("benefit")) {
                        if(benefitCodes.size() > 0) {
                            speechResult.setIntendType(null);
                            speechResult.setText(getResultString((List<String>)lastAnswer.get().getMetaData().get("target"),
                                    benefitCodes));
                            speechResult.setRecordThis(false);
                        }else {
                            speechResult.setIntendType(IntendType.WELFARE_ANSWER_BENEFIT);
                            speechResult.setText("필요하신 복지를 말씀해주세요");
                            speechResult.setRecordThis(true);
                        }
                    }
            }

            return speechResult;
        } else {
            speechResult.setIntendType(IntendType.WELFARE_ANSWER_TARGET);
            speechResult.setText("보훈대상자, 장애인, 기초생활수급자 중 해당하시는 것들을 전부 말씀해 주세요");
            speechResult.setRecordThis(true);
            return speechResult;
        }
    }

    private String getResultString(List<String> targets, List<String> benefits) {
        Long userId = System.currentTimeMillis();
        welfareInfoStorage.setBenefit(userId, benefits);
        welfareInfoStorage.setTarget(userId, targets);

        WelfareQueryResponse response = welfareService.getAll(userId, 0);
        List<String> list = response.getList().stream()
                .limit(2)
                .map(it ->
                        String.format("%s에서 %s", it.getDepartment(), it.getSummary())
                )
                .collect(Collectors.toList());
                //.collect(Collectors.joining(" 그리고 "));

        String joined = list.get(new Float(Math.floor(Math.random() * list.size())).intValue());
        joined = joined.replace("합니다", "해주고 있어요");
        joined = joined + " 이 복지가 도움이 되셨으면 좋겠네요.";

        return joined;
    }
}
