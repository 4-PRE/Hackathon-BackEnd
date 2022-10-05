package com.example.hackathon2022.domain.job.presentation.dto.response;

import com.example.hackathon2022.domain.job.type.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class JobListResponseDto {

    private Long job_id;
    private String companyName;
    private LocalDate start_date;
    private LocalDate end_date;
    private Integer requireNumber;
    private Region region;
    private String regionDetail;
    private Integer salary;
    private String telephone;
}
