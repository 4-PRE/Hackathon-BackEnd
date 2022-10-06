package com.example.hackathon2022.domain.welfare.repository;

import com.example.hackathon2022.domain.welfare.entity.WelfareDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WelfareDetailRepository extends CrudRepository<WelfareDetail, Long> {

}
