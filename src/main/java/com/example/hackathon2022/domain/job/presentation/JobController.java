package com.example.hackathon2022.domain.job.presentation;


import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobListResponseDto;
import com.example.hackathon2022.domain.job.service.JobService;
import com.example.hackathon2022.global.infra.job.JobInfraService;
import com.example.hackathon2022.global.response.ResponseService;
import com.example.hackathon2022.global.response.result.CommonResultResponse;
import lombok.RequiredArgsConstructor;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
>>>>>>> Stashed changes

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
@CrossOrigin("http://localhost:3000")
public class JobController {

    private final JobInfraService jobInfraService;
    private final JobService jobService;
    private final ResponseService responseService;

    @GetMapping("/update")
    public String update() {
        jobInfraService.updateAll();
        return "Finished";
    }

    @GetMapping("")
    public CommonResultResponse jobList(@RequestParam String region, @RequestParam Integer page, @RequestParam Integer size) {
        JobListResponseDto result = jobService.jobList(region, page, size);
        return responseService.getSingleResultResponse(result);
    }

    @GetMapping("/{job_id}")
    public CommonResultResponse jobDetail(@PathVariable(name = "job_id") Long jobId) {
        JobDetailResponseDto result = jobService.jobDetail(jobId);
        return responseService.getSingleResultResponse(result);
    }
}
