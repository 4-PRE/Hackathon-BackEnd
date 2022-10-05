package com.example.hackathon2022.domain.welfare.repository;

import com.example.hackathon2022.domain.welfare.entity.Welfare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WelfareRepository extends JpaRepository<Welfare, Long> {

}
