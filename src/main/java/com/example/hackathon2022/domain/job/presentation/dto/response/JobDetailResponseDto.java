package com.example.hackathon2022.domain.job.presentation.dto.response;

<<<<<<< Updated upstream

=======
import com.example.hackathon2022.domain.job.type.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class JobDetailResponseDto {

    private String companyName;
    private LocalDate start_date;
    private LocalDate end_date;
    private Integer work_start_hour;
    private Integer work_end_hour;
    private Integer age;
    private String requireNumber;
    private Region region;
    private String address;
    private Integer salary;
    private String telephone;
    
>>>>>>> Stashed changes
}
