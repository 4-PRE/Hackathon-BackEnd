package com.example.hackathon2022;

import com.example.hackathon2022.global.infra.job.JobInfraService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiTest {

    JobInfraService jobInfraService;

    @Test
    void test() {
        jobInfraService.updateAll();
    }

}
