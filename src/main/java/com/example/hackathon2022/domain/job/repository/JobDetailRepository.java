package com.example.hackathon2022.domain.job.repository;

import com.example.hackathon2022.domain.job.entity.JobDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDetailRepository extends JpaRepository<JobDetail, Long> {
}
