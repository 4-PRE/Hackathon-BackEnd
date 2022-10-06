package com.example.hackathon2022.domain.job.service;

import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobListResponseDto;
import com.example.hackathon2022.domain.job.type.Region;

public interface JobService {

    JobListResponseDto jobList(String region, Integer page, Integer size, String keyword);
    JobDetailResponseDto jobDetail(Long jobId);
}
