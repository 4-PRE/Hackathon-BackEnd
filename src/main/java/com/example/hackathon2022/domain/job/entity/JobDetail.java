package com.example.hackathon2022.domain.job.entity;

import com.example.hackathon2022.domain.job.type.JobKind;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class JobDetail {

    @Id
    private Long id;

    @MapsId
    @JoinColumn
    @OneToOne
    private Job job;

    private Integer workStartHour;
    private Integer workEndHour;

    private Integer age;
    private String requirement;

    private String address;

    @Enumerated(EnumType.STRING)
    private JobKind jobKind;
}
