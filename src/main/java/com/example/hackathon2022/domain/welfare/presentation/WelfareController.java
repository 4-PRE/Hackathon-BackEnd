package com.example.hackathon2022.domain.welfare.presentation;

import com.example.hackathon2022.domain.welfare.component.WelfareInfoStorage;
import com.example.hackathon2022.domain.welfare.presentation.dto.reqeust.WelfareQueryRequest;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareDetailResponse;
import com.example.hackathon2022.domain.welfare.presentation.dto.response.WelfareQueryResponse;
import com.example.hackathon2022.domain.welfare.service.WelfareService;
import com.example.hackathon2022.global.infra.welfare.WelfareInfraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/welfare")
@CrossOrigin("http://localhost:3000")
@RestController
public class WelfareController {
    private final WelfareInfraService welfareInfraService;
    private final WelfareService welfareService;

    @GetMapping("/update")
    public void update() {
        welfareInfraService.updateAll();
    }

    @GetMapping("/{user-id}")
    public WelfareQueryResponse getAll(@PathVariable("user-id") Long userId, @RequestParam("page") int page) {
        return welfareService.getAll(userId, page);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/wishlist/{user-id}")
    public void updateWishlist(@PathVariable("user-id") Long userId, @RequestBody @Valid WelfareQueryRequest request) {
        welfareService.updateSessionInfo(userId, request);
    }

    @GetMapping("/details/{welfare-id}")
    public WelfareDetailResponse getDetail(@PathVariable("welfare-id") Long welfareId) {
        return welfareService.getDetailOf(welfareId);
    }
}
