package com.example.hackathon2022.domain.welfare.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WelfareResponse {
    private String department;
    private String division;
    private String detailLink;
    private String targets;
    private String summary;
}
