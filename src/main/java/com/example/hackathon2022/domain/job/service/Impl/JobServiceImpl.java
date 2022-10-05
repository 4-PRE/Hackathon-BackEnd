package com.example.hackathon2022.domain.job.service.Impl;

import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobListResponseDto;
import com.example.hackathon2022.domain.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    @Override
    public JobListResponseDto jobList() {
        return null;
    }

    @Override
    public JobDetailResponseDto jobDetail(Long job_id) {
        return null;
    }
}
