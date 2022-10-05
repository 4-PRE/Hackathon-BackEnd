package com.example.hackathon2022.domain.job.controller;

import com.example.hackathon2022.domain.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
}
