package com.example.hackathon2022.global.infra.job;

import com.example.hackathon2022.global.config.FeignXmlConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jobList", url = "http://apis.data.go.kr/B552474/JobBsnInfoService/getJobBsnRecruitList",
configuration = FeignXmlConfiguration.class)
public interface JobListFeign {
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public JobListResponse execute(
            @RequestParam(value="serviceKey") String key,
            @RequestParam(value="numOfRows") Integer numberOfRows,
            @RequestParam(value="pageNo") Integer page
    );
}
