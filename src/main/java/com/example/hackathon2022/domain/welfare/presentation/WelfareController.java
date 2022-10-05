package com.example.hackathon2022.domain.welfare.presentation;

import com.example.hackathon2022.global.infra.welfare.WelfareInfraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/welfare")
@RestController
public class WelfareController {
    private final WelfareInfraService welfareInfraService;

    @GetMapping("/update")
    public void update() {
        welfareInfraService.updateAll();
    }

}
