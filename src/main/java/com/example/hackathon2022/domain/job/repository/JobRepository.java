package com.example.hackathon2022.domain.job.repository;

import com.example.hackathon2022.domain.job.entity.Job;
import com.example.hackathon2022.domain.job.type.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long> {

    Page<Job> findJobByRegion(Region region, PageRequest pageRequest);

    @Query("select job from Job job where job.region = :region and job.detail.description like :keyword")
    Page<Job> findJobByDescription(Region region, String keyword, PageRequest pageRequest);
}
