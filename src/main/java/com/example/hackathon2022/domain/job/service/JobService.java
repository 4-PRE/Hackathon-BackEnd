package com.example.hackathon2022.domain.job.service;

import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobListResponseDto;

public interface JobService {

    JobListResponseDto jobList(String region, Integer page, Integer size);
    JobDetailResponseDto jobDetail(Long jobId);
}
