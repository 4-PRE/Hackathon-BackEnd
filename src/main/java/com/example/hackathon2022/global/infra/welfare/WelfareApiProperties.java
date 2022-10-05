package com.example.hackathon2022.global.infra.welfare;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
@Getter
@Setter
@AllArgsConstructor
@ConfigurationProperties(value = "infra.welfare")
public class WelfareApiProperties {
    private String key;
}
