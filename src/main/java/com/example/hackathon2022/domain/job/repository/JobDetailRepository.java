package com.example.hackathon2022.domain.job.repository;

import com.example.hackathon2022.domain.job.entity.JobDetail;
import org.springframework.data.repository.CrudRepository;

public interface JobDetailRepository extends CrudRepository<JobDetail, Long> {

}
