package com.example.hackathon2022.global.infra.job;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class JobListResponse {

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    public static class Body {
        private List<Item> items;
    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String projNo;
        private String orgName;
        private String jobType;
        private String trnStatNm;

        private String dstrCd1Nm;
        private String dstrCd2Nm;

        private String intCnt;
        private String hpInvtCnt;
        private String hpNotiSdate;
        private String hpNotiEdate;
    }

    private Body body;

}
