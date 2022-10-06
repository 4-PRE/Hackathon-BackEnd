package com.example.hackathon2022.domain.welfare.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WelfareDetailResponse extends WelfareResponse {
    private String callName;
    private String callNumber;
    private String callMethod;
    private String benefitDetail;
    private String requirementDetail;

    @Builder
    public WelfareDetailResponse(Long id, String department, String division, String detailLink, String targets, String summary, String callName, String callNumber, String callMethod, String benefitDetail, String requirementDetail) {
        super(id, department, division, detailLink, targets, summary);
        this.callName = callName;
        this.callNumber = callNumber;
        this.callMethod = callMethod;
        this.benefitDetail = benefitDetail;
        this.requirementDetail = requirementDetail;
    }
}
