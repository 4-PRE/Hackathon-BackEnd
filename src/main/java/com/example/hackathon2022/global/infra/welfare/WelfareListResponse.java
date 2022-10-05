package com.example.hackathon2022.global.infra.welfare;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class WelfareListResponse {
    private Integer jurMnofNm;
    private String jurOrgNm;
    private String lifeArray;
    private String servDgst;
    private String trgterIndvdlArray;
}
