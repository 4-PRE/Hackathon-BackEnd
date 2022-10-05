package com.example.hackathon2022.domain.welfare.service;

import com.example.hackathon2022.domain.welfare.entity.Welfare;
import com.example.hackathon2022.domain.welfare.presentation.dto.reqeust.WelfareQueryRequest;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareQueryResponse;
import com.example.hackathon2022.domain.welfare.repository.WelfareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WelfareService {
    private final WelfareRepository welfareRepository;

    @Transactional(readOnly = true)
    public WelfareQueryResponse getAll(int page, WelfareQueryRequest request) {
        int targetFlag = 0;
        if(request.getTargets().contains(""))
            targetFlag |= 0x000;
        else if(request.getTargets().contains(""))
            targetFlag |= 0x000;
        else if(request.getTargets().contains(""))
            targetFlag |= 0x000;

        int benefitFlag = 0;
        if(request.getWishBenefits().contains(""))
            benefitFlag |= 0x00000;
        else if(request.getWishBenefits().contains(""))
            benefitFlag |= 0x00000;
        else if(request.getWishBenefits().contains(""))
            benefitFlag |= 0x00000;
        else if(request.getWishBenefits().contains(""))
            benefitFlag |= 0x00000;
        else if(request.getWishBenefits().contains(""))
            benefitFlag |= 0x00000;

        Pageable pageRequeest = PageRequest.of(5, 10);
        Page<Welfare> result = welfareRepository.getAllWithFlags(targetFlag, benefitFlag, pageRequeest);

        return WelfareQueryResponse.builder()
                .page(page)
                .pageCount(result.getTotalPages())
                .items(result.getContent().size())
                .list()
                .build();
    }
}
