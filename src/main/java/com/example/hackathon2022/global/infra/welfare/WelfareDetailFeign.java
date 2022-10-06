package com.example.hackathon2022.global.infra.welfare;

import com.example.hackathon2022.global.config.FeignXmlConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "welfareDetail", url = "https://www.bokjiro.go.kr/ssis-tbu/NationalWelfareInformations/NationalWelfaredetailed.do")
public interface WelfareDetailFeign {
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public String  execute(
            @RequestParam(value = "serviceKey") String key,
            @RequestParam(value = "callTp") String mode,
            @RequestParam(value = "servId") String serviceId,
            @RequestParam(value = "SG_APIM") String sgAPIM
    );
}