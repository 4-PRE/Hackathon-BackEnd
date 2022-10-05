package com.example.hackathon2022.domain.welfare.presentation.dto.reqeust;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class WelfareQueryRequest {
    private List<String> targets;
    private List<String> wishBenefits;
}
