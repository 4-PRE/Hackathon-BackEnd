package com.example.hackathon2022.domain.welfare.presentation;

import com.example.hackathon2022.domain.welfare.presentation.dto.reqeust.WelfareQueryRequest;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareQueryResponse;
import com.example.hackathon2022.domain.welfare.service.WelfareService;
import com.example.hackathon2022.global.infra.welfare.WelfareInfraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/welfare")
@RestController
public class WelfareController {
    private final WelfareInfraService welfareInfraService;
    private final WelfareService welfareService;

    @GetMapping("/update")
    public void update() {
        welfareInfraService.updateAll();
    }

    @GetMapping("/")
    public WelfareQueryResponse getAll(@RequestParam("page") int page, WelfareQueryRequest request) {
        return welfareService.getAll(page, request);
    }
}
