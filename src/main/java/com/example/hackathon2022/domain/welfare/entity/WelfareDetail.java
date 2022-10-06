package com.example.hackathon2022.domain.welfare.entity;

import com.example.hackathon2022.domain.welfare.type.ContactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WelfareDetail {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn
    @MapsId
    private Welfare welfare;

    private String callName;

    private String callNumber;

    @Enumerated(EnumType.STRING)
    private ContactType callMethod;

    @Lob
    private String benefitDetail;

    @Lob
    private String requirementDetail;
}
