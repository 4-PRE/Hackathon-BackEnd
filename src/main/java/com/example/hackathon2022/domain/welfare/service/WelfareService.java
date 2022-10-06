package com.example.hackathon2022.domain.welfare.service;

import com.example.hackathon2022.domain.welfare.component.WelfareInfoStorage;
import com.example.hackathon2022.domain.welfare.entity.Welfare;
import com.example.hackathon2022.domain.welfare.entity.WelfareDetail;
import com.example.hackathon2022.domain.welfare.presentation.dto.reqeust.WelfareQueryRequest;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareDetailResponse;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareQueryResponse;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareSimpleResponse;
import com.example.hackathon2022.domain.welfare.repository.WelfareDetailRepository;
import com.example.hackathon2022.domain.welfare.repository.WelfareRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Primary
@Service
public class WelfareService {

    private final WelfareInfoStorage welfareInfoStorage;
    private final WelfareRepository welfareRepository;
    private final WelfareDetailRepository welfareDetailRepository;

    @Transactional(readOnly = true)
    public WelfareQueryResponse getAll(Long userId, int page) {
        List<String> wishBenefitList = welfareInfoStorage.getBenefit(userId);
        List<String> targetList = welfareInfoStorage.getTarget(userId);

        int targetFlag = 0;
        if(targetList.contains("LOW_INCOME"))
            targetFlag |= 0b001;
        if(targetList.contains("DISABLED"))
            targetFlag |= 0b010;
        if(targetList.contains("VETERAN"))
            targetFlag |= 0b100;

        int benefitFlag = 0;
        if(wishBenefitList.contains("EDUCATION"))
            benefitFlag |= 0b10000;
        if(wishBenefitList.contains("MEDICAL"))
            benefitFlag |= 0b01000;
        if(wishBenefitList.contains("CULTURE"))
            benefitFlag |= 0b00100;
        if(wishBenefitList.contains("FINANCIAL"))
            benefitFlag |= 0b00010;
        if(wishBenefitList.contains("OTHER"))
            benefitFlag |= 0b00001;

        log.info("target: {} / benefit: {}", targetFlag, benefitFlag);

        Pageable pageRequeest = PageRequest.of(page, 10);
        Page<Welfare> result = welfareRepository.getAllWithFlags(benefitFlag, targetFlag, pageRequeest);

        return WelfareQueryResponse.builder()
                .page(result.getNumber())
                .pageCount(result.getTotalPages())
                .items(result.getContent().size())
                .list(result.getContent().stream().map(it -> WelfareSimpleResponse.builder()
                        .id(it.getId())
                        .summary(it.getSummary())
                        .department(it.getDepartment())
                        .division(it.getDivision())
                        .detailLink(it.getDetailLink())
                        .targets(it.getKeywords())
                        .build()).collect(Collectors.toList()))
                .build();
    }

    public void updateSessionInfo(Long userId, WelfareQueryRequest request) {
        welfareInfoStorage.setBenefit(userId, request.getWishBenefits());
        welfareInfoStorage.setTarget(userId, request.getTargets());

        log.info("session update completed");
    }

    @Transactional(readOnly = true)
    public WelfareDetailResponse getDetailOf(Long welfareId) {
        WelfareDetail detail = welfareDetailRepository.findById(welfareId)
                .orElseThrow();

        Welfare welfare = detail.getWelfare();

        return WelfareDetailResponse.builder()
                .id(welfare.getId())
                .summary(welfare.getSummary())
                .department(welfare.getDepartment())
                .division(welfare.getDivision())
                .detailLink(welfare.getDetailLink())
                .targets(welfare.getKeywords())
                .callNumber(detail.getCallNumber())
                .callName(detail.getCallName())
                .callMethod(detail.getCallMethod().name())
                .requirementDetail(detail.getRequirementDetail())
                .benefitDetail(detail.getBenefitDetail())
                .build();
    }
}
