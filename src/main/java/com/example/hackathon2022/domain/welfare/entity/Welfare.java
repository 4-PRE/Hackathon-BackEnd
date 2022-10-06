package com.example.hackathon2022.domain.welfare.entity;

import lombok.*;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "welfare")
    private WelfareDetail welfareDetail;
    public void setWelfareDetail(WelfareDetail welfareDetail) {
        this.welfareDetail = welfareDetail;
    }

    private transient String internalId;
}
