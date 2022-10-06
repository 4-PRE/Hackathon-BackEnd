package com.example.hackathon2022.domain.welfare.presentation.dto.reqeust;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class WelfareQueryRequest {
    @NotNull
    private List<String> targets;

    @NotNull
    private List<String> wishBenefits;
}
