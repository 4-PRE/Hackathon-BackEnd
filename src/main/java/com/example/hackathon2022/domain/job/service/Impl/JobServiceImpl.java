package com.example.hackathon2022.domain.job.service.Impl;

import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobListResponseDto;
import com.example.hackathon2022.domain.job.service.JobService;
import lombok.RequiredArgsConstructor;
import com.example.hackathon2022.domain.job.entity.Job;
import com.example.hackathon2022.domain.job.entity.JobDetail;
import com.example.hackathon2022.domain.job.exception.JobDetailNotFoundException;
import com.example.hackathon2022.domain.job.exception.JobNotFoundException;
import com.example.hackathon2022.domain.job.repository.JobDetailRepository;
import com.example.hackathon2022.domain.job.repository.JobRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobDetailRepository jobDetailRepository;

    @Override
    public JobListResponseDto jobList() {
        return null;
    }

    @Override
    public JobDetailResponseDto jobDetail(Long jobId) {

        Job job = jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException(HttpStatus.NOT_FOUND, "일자리를 찾을 수 없습니다."));

        JobDetail jobDetail = jobDetailRepository.findById(job.getId()).orElseThrow(() -> new JobDetailNotFoundException(HttpStatus.NOT_FOUND, "일자리 상세정보를 찾을 수 없습니다."));

        return JobDetailResponseDto.builder()
                .companyName(job.getCompanyName())
                .start_date(job.getStartDate())
                .end_date(job.getEndDate())
                .work_start_hour(jobDetail.getWorkStartHour())
                .work_end_hour(jobDetail.getWorkEndHour())
                .age(jobDetail.getAge())
                .requireNumber(String.format("%d명", job.getRequireNumber()))
                .requirement(jobDetail.getRequirement())
                .region(job.getRegion().getKoreanName())
                .address(jobDetail.getAddress())
                .salary(job.getSalary())
                .telephone(job.getTelephone())
                .build();
    }
}
