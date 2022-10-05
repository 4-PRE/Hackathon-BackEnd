package com.example.hackathon2022.domain.job.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Region {
    SEOUL("11", "서울특별시"),
    BUSAN("21", "부산광역시"),
    DAEGU("22", "대구광역시"),
    INCHEON("23", "인천광역시"),
    GWANGJU("24", "광주광역시"),
    DAEJEON("25", "대전광역시"),
    ULSAN("26", "울산광역시"),
    SEJONG("29", "세종특별자치시"),
    GYUNGGI("31", "경기도"),
    GWANGWON("32", "강원도"),
    CHUNGBUK("33", "충청북도"),
    CHUNGNAM("34", "충청남도"),
    JEONBUK("35", "전라북도"),
    JEONNAM("36", "전라남도"),
    GYUNGBUK("37", "경상북도"),
    GYUNGNAM("38", "경상남도"),
    JEJU("39", "제주특별자치도")
    ;

    private String code;
    private String koreanName;

    public static Region fromName(String name) {
        return Arrays.stream(Region.values()).filter(it -> it.koreanName.equals(name))
                .findFirst()
                .orElseThrow();
    }
}
