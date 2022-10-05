package com.example.hackathon2022.domain.job.entity;

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

    private String age;
    private String requirement;

    private String address;
}
