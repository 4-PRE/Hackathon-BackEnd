package com.example.hackathon2022.domain.welfare.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class WelfareQueryResponse {
    private int page;
    private int items;
    private int pageCount;
    private List<WelfareResponse> list;
}
