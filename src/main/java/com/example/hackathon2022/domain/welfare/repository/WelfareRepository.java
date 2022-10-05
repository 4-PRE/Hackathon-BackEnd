package com.example.hackathon2022.domain.welfare.repository;

import com.example.hackathon2022.domain.welfare.entity.Welfare;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WelfareRepository extends JpaRepository<Welfare, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM welfare WHERE benefit_flag & :wishBenefitFlag > 0 AND target_flag & :targetFlag > 0;")
    Page<Welfare> getAllWithFlags(int targetFlag, int wishBenefitFlag, Pageable pageable);
}
