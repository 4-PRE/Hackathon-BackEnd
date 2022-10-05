package com.example.hackathon2022.global.infra.welfare;

import com.example.hackathon2022.global.config.FeignXmlConfiguration;
import com.example.hackathon2022.global.infra.job.JobListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "welfareList", url = "https://www.bokjiro.go.kr/ssis-tbu/NationalWelfareInformations/NationalWelfarelist.do")//,
        //configuration = FeignXmlConfiguration.class)
public interface WelfareListFeign {
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public String execute(
            @RequestParam(value = "serviceKey") String key,
            @RequestParam(value = "callTp") String mode,
            @RequestParam(value = "pageNo") Integer page,
            @RequestParam(value = "numOfRows") Integer itemCount,
            @RequestParam(value = "srchKeyCode") String viewType,
            @RequestParam(value = "lifeArray") String ageType,
            @RequestParam(value = "SG_APIM") String sgAPIM
    );
}