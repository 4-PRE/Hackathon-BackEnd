package com.example.hackathon2022.global.infra.job;

import com.example.hackathon2022.domain.job.entity.Job;
import com.example.hackathon2022.domain.job.entity.JobDetail;
import com.example.hackathon2022.domain.job.repository.JobDetailRepository;
import com.example.hackathon2022.domain.job.repository.JobRepository;
import com.example.hackathon2022.domain.job.type.Region;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Component
public class JobInfraService {
    private final JobApiProperties jobApiProperties;
    private final JobRepository jobRepository;
    private final JobDetailRepository jobDetailRepository;
    private final JobListFeign jobListFeign;
    private final JobDetailFeign jobDetailFeign;

    @Transactional
    public void updateAll() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        List<Job> jobList = new ArrayList<>();
        List<JobDetail> detailList = new ArrayList<>();

        for(int i = 1; i < 10; i++) {
            jobListFeign.execute(
                    jobApiProperties.getKey(), 10, i
            ).getBody().getItems().forEach(it -> {
                JobDetailResponse.Item detail = jobDetailFeign.execute(jobApiProperties.getKey(), it.getProjNo()).getBody().getItem();
                if("완료".equals(it.getTrnStatNm())) return;

                Job job = Job.builder()
                        .companyName(it.getOrgName())
                        .startDate(LocalDate.parse(it.getHpNotiSdate(), formatter))
                        .endDate(LocalDate.parse(it.getHpNotiSdate(), formatter))
                        .region(Region.fromName(it.getDstrCd1Nm()))
                        .regionDetail(it.getDstrCd2Nm())
                        .requireNumber(Integer.parseInt(it.getHpInvtCnt()))
                        .salary(Integer.parseInt(it.getIntCnt()))
                        .telephone(detail.getTelNum())
                        .build();

                JobDetail jobDetail = JobDetail.builder()
                        .job(job)
                        .address(detail.getAddr())
                        .age(detail.getRecuAgeNm())
                        .requirement(detail.getLicense())
                        .arrange(detail.getArrange())
                        .description(detail.getContent())
                        .workStartHour(Integer.parseInt(detail.getProjTime().split(" ~ ")[0]))
                        .workEndHour(Integer.parseInt(detail.getProjTime().split(" ~ ")[1]))
                        .build();
                job.addDetail(jobDetail);

                jobList.add(job);
                detailList.add(jobDetail);
            });
        }

        log.info(jobList);

        jobRepository.saveAll(jobList);
        jobDetailRepository.saveAll(detailList);
    }
}
