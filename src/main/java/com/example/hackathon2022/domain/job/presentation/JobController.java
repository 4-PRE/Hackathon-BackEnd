package com.example.hackathon2022.domain.job.presentation;


import com.example.hackathon2022.global.infra.job.JobInfraService;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
import com.example.hackathon2022.domain.job.service.JobService;
import com.example.hackathon2022.global.response.ResponseService;
import com.example.hackathon2022.global.response.result.CommonResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {

    private final JobInfraService jobInfraService;
    private final JobService jobService;
    private final ResponseService responseService;

    @GetMapping("/update")
    public String update() {
        jobInfraService.updateAll();
        return "Finished";
    }

    @GetMapping("/{job_id}")
    public CommonResultResponse jobDetail(@PathVariable(name = "job_id") Long jobId) {
        JobDetailResponseDto result = jobService.jobDetail(jobId);
        return responseService.getSingleResultResponse(result);
    }
}
