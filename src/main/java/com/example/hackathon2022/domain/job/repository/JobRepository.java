package com.example.hackathon2022.domain.job.repository;

import com.example.hackathon2022.domain.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
