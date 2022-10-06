package com.example.hackathon2022.domain.job.service.Impl;

import com.example.hackathon2022.domain.job.entity.Job;
import com.example.hackathon2022.domain.job.entity.JobDetail;
import com.example.hackathon2022.domain.job.exception.JobDetailNotFoundException;
import com.example.hackathon2022.domain.job.exception.JobNotFoundException;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobListResponseDto;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobResponseDto;
import com.example.hackathon2022.domain.job.repository.JobDetailRepository;
import com.example.hackathon2022.domain.job.repository.JobRepository;
import com.example.hackathon2022.domain.job.service.JobService;
import com.example.hackathon2022.domain.job.type.Region;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobDetailRepository jobDetailRepository;

    @Override
    @Transactional
    public JobListResponseDto jobList(String region1, Integer page, Integer size, String keyWord) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Region region = Region.fromName(region1);

        Page<Job> jobList = jobRepository.findJobByRegion(region, pageRequest);

        if(keyWord != null) {

            log.info(region.name());

            Page<Job> jobByDescription = jobRepository.findJobByDescription(region, "%"+keyWord+"%", pageRequest);
            log.info("totalSize : " + jobByDescription.getTotalElements());

             return getJobListResponseDto(jobByDescription);

        }

        return getJobListResponseDto(jobList);

    }

    private JobListResponseDto getJobListResponseDto(Page<Job> jobList) {
        return JobListResponseDto.builder()
                .count(Math.toIntExact(jobList.getTotalElements()))
                .totalPage(jobList.getTotalPages())
                .list(jobList.map(job ->
                        JobResponseDto.builder()
                                .id(job.getId())
                                .companyName(job.getCompanyName())
                                .startDate(job.getStartDate())
                                .endDate(job.getEndDate())
                                .requireNumber(job.getRequireNumber())
                                .region(job.getRegion().getKoreanName())
                                .regionDetail(job.getRegionDetail())
                                .telephone(job.getTelephone())
                                .build()
                ).toList())
                .build();
    }

    @Override
    @Transactional
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
                .region(job.getRegionDetail())
                .address(jobDetail.getAddress())
                .salary(job.getSalary())
                .telephone(job.getTelephone())
                .arrange(jobDetail.getArrange())
                .description(jobDetail.getDescription())
                .build();
    }
}
