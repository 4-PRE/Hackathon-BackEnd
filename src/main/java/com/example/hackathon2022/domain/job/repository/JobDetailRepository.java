package com.example.hackathon2022.domain.job.repository;

import com.example.hackathon2022.domain.job.entity.JobDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobDetailRepository extends CrudRepository<JobDetail, Long> {

    List<JobDetail> findByDescriptionContaining(String keyword);

}
