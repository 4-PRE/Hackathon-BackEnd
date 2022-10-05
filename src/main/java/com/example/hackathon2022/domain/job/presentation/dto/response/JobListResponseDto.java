package com.example.hackathon2022.domain.job.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class JobListResponseDto {

<<<<<<< Updated upstream
    private Long job_id;
    private String companyName;
    private LocalDate start_date;
    private LocalDate end_date;
    private Integer requireNumber;
    private String region;
    private String regionDetail;
    private Integer salary;
    private String telephone;
=======
    private Integer totalPage;
    private Integer count;
    private List<JobResponseDto> list;
>>>>>>> Stashed changes
}
