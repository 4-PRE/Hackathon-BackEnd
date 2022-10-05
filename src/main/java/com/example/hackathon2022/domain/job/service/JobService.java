package com.example.hackathon2022.domain.job.service;

import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobListResponseDto;

public interface JobService {

    JobListResponseDto jobList();

    JobDetailResponseDto jobDetail(Long jobId);
}
