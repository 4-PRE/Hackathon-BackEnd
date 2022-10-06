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
    @Query(nativeQuery = true, value = "SELECT * FROM welfare WHERE benefit_flag_for_search & ?1 > 0 AND target_flag_for_search & ?2 > 0",
            countQuery = "SELECT COUNT(*) FROM welfare WHERE benefit_flag_for_search & ?1 > 0 AND target_flag_for_search & ?2 > 0")
    Page<Welfare> getAllWithFlags(int wishBenefitFlag, int targetFlag, Pageable pageable);
}
