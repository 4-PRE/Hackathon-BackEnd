package com.example.hackathon2022.domain.job.service;

import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
<<<<<<< Updated upstream
import com.example.hackathon2022.domain.job.presentation.dto.response.JobListResponseDto;

public interface JobService {

    JobListResponseDto jobList();

    JobDetailResponseDto jobDetail(Long job_id);
=======

public interface JobService {

    JobDetailResponseDto jobDetail(Long job_id);

>>>>>>> Stashed changes
}
