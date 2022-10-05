package com.example.hackathon2022.global.infra.job;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class JobDetailResponse {

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    public static class Body {
        private Item item;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String addr;
        private String telNum;
        private String license;
        private String projTime;
        private String recuAgeNm;
    }

    private Body body;

}
