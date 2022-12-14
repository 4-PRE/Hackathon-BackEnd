package com.example.hackathon2022.domain.job.presentation;


import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobListResponseDto;
import com.example.hackathon2022.domain.job.service.JobService;
import com.example.hackathon2022.domain.job.type.Region;
import com.example.hackathon2022.global.infra.job.JobInfraService;
import com.example.hackathon2022.global.response.ResponseService;
import com.example.hackathon2022.global.response.result.CommonResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

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
    public CommonResultResponse jobList(@RequestParam String region, @RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) String keyword) {
        JobListResponseDto result = jobService.jobList(region, page, size, keyword);
        return responseService.getSingleResultResponse(result);
    }

    @GetMapping("/{job_id}")
    public CommonResultResponse jobDetail(@PathVariable(name = "job_id") Long jobId) {
        JobDetailResponseDto result = jobService.jobDetail(jobId);
        return responseService.getSingleResultResponse(result);
    }
}
