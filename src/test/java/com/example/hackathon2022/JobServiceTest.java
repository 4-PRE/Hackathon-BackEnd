package com.example.hackathon2022;

import com.example.hackathon2022.domain.job.presentation.dto.response.JobDetailResponseDto;
import com.example.hackathon2022.domain.job.presentation.dto.response.JobListResponseDto;
import com.example.hackathon2022.domain.job.service.JobService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JobServiceTest {

    @Autowired
    JobService jobService;

    @Test
    @DisplayName("일자리 list 테스트")
    void jobListTest() {

        // given // when
//        JobListResponseDto result = jobService.jobList("서울특별시", 0, 5);

        // then
//        Assertions.assertNotNull(result);

    }

    @Test
    @DisplayName("일자리 상세보기 테스트")
    void jobDetailTest() {

        // given // when
        JobDetailResponseDto result = jobService.jobDetail(1L);

        // then
        Assertions.assertNotNull(result);
    }
}
