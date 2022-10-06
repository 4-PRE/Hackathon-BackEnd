package com.example.hackathon2022.domain.welfare.service;

import com.example.hackathon2022.domain.welfare.entity.Welfare;
import com.example.hackathon2022.domain.welfare.presentation.dto.reqeust.WelfareQueryRequest;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareQueryResponse;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareResponse;
import com.example.hackathon2022.domain.welfare.repository.WelfareRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class WelfareService {
    private final WelfareRepository welfareRepository;

    @Transactional(readOnly = true)
    public WelfareQueryResponse getAll(int page, WelfareQueryRequest request) {
        int targetFlag = 0;
        if(request.getTargets().contains("LOW_INCOME"))
            targetFlag |= 0b001;
        if(request.getTargets().contains("DISABLED"))
            targetFlag |= 0b010;
        if(request.getTargets().contains("VETERAN"))
            targetFlag |= 0b100;

        int benefitFlag = 0;
        if(request.getWishBenefits().contains("EDUCATION"))
            benefitFlag |= 0b10000;
        if(request.getWishBenefits().contains("MEDICAL"))
            benefitFlag |= 0b01000;
        if(request.getWishBenefits().contains("CULTURE"))
            benefitFlag |= 0b00100;
        if(request.getWishBenefits().contains("FINANCIAL"))
            benefitFlag |= 0b00010;
        if(request.getWishBenefits().contains("OTHER"))
            benefitFlag |= 0b00001;

        log.info("target: {} / benefit: {}", targetFlag, benefitFlag);

        Pageable pageRequeest = PageRequest.of(page, 10);
        Page<Welfare> result = welfareRepository.getAllWithFlags(benefitFlag, targetFlag, pageRequeest);

        return WelfareQueryResponse.builder()
                .page(result.getNumber())
                .pageCount(result.getTotalPages())
                .items(result.getContent().size())
                .list(result.getContent().stream().map(it -> WelfareResponse.builder()
                        .summary(it.getSummary())
                        .department(it.getDepartment())
                        .division(it.getDivision())
                        .detailLink(it.getDetailLink())
                        .targets(it.getKeywords())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}
