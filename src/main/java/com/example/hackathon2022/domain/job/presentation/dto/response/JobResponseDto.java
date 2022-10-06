package com.example.hackathon2022.domain.job.presentation.dto.response;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JobResponseDto {

    private Long id;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer requireNumber;
    private String region;
    private String regionDetail;
    private String telephone;

}
