package com.example.hackathon2022.domain.welfare.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Welfare {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String department;

    private String division;

    private String summary;

    private String detailLink;

    private String keywords;

    private Integer targetFlagForSearch;
    private Integer benefitFlagForSearch;
}
