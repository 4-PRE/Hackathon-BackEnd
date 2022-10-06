package com.example.hackathon2022.domain.welfare.presentation;

import com.example.hackathon2022.domain.welfare.presentation.dto.reqeust.WelfareQueryRequest;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareDetailResponse;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareQueryResponse;
import com.example.hackathon2022.domain.welfare.service.WelfareService;
import com.example.hackathon2022.global.infra.welfare.WelfareInfraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping
    public WelfareQueryResponse getAll(@RequestParam("page") int page, @RequestBody @Valid WelfareQueryRequest request) {
        return welfareService.getAll(page, request);
    }

    @GetMapping("/{welfare-id}")
    public WelfareDetailResponse getDetail(@PathVariable("welfare-id") Long welfareId) {
        return welfareService.getDetailOf(welfareId);
    }
}
