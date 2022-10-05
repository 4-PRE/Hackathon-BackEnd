package com.example.hackathon2022.domain.job.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Region {
    SEOUL("11"),
    BUSAN("21"),
    DAEGU("22"),
    INCHEON("23"),
    GWANGJU("24"),
    DAEJEON("25"),
    ULSAN("26"),
    SEJONG("29"),
    GYUNGGI("31"),
    GWANGWON("32"),
    CHUNGBUK("33"),
    CHUNGNAM("34"),
    JEONBUK("35"),
    JEONNAM("36"),
    GYUNGBUK("37"),
    GYUNGNAM("38"),
    JEJU("39")
    ;

    private String code;
}
