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

   private Integer count;
   private Integer totalPage;
   private List<JobResponseDto> list;

}
