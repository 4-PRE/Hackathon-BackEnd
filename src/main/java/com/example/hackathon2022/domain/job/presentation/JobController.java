package com.example.hackathon2022.domain.job.presentation;

import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
import com.example.hackathon2022.domain.job.service.JobService;
import com.example.hackathon2022.global.response.ResponseService;
import com.example.hackathon2022.global.response.result.CommonResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
    private final ResponseService responseService;

    @GetMapping("/{job_id}")
    public CommonResultResponse jobDetail(@PathVariable(name = "job_id") Long job_id) {
        JobDetailResponseDto result = jobService.jobDetail(job_id);
        return responseService.getSingleResultResponse(result);
    }
}
