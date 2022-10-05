package com.example.hackathon2022.domain.job.presentation;

import com.example.hackathon2022.global.infra.job.JobInfraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/jobs")
@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobInfraService jobInfraService;

    @GetMapping("/update")
    public String update() {
        jobInfraService.updateAll();
        return "Finished";
    }

}
