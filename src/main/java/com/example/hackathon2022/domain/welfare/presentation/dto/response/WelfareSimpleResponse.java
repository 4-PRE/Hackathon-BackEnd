package com.example.hackathon2022.domain.welfare.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
public class WelfareSimpleResponse extends WelfareResponse {
    @Builder
    public WelfareSimpleResponse(Long id, String department, String division, String detailLink, String targets, String summary) {
        super(id, department, division, detailLink, targets, summary);
    }
}
