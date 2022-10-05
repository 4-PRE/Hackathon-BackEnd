package com.example.hackathon2022.domain.job.entity;

import com.example.hackathon2022.domain.job.type.Region;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private LocalDate startDate;
    private LocalDate endDate;

    private Integer requireNumber;

    @Enumerated(EnumType.STRING)
    private Region region;

    private String regionDetail;

    private Integer salary;

    private String telephone;

    @OneToOne(mappedBy = "job")
    private JobDetail detail;

    public void addDetail(JobDetail jobDetail) {
        this.detail = jobDetail;
    }
}
