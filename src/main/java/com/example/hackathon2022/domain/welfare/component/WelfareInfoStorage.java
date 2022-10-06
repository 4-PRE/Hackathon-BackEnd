package com.example.hackathon2022.domain.welfare.component;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
@Component
public class WelfareInfoStorage {
    private ConcurrentHashMap<String, List<String>> storage = new ConcurrentHashMap<>();

    public void setTarget(Long id, List<String> target) {
        log.info("target value inject: {}", String.format("target_%d", id));
        storage.put(String.format("target_%d", id), target);
    }

    public void setBenefit(Long id, List<String> benefit) {
        log.info("benefit value inject: {}", String.format("benefit_%d", id));
        storage.put(String.format("benefit_%d", id), benefit);
    }

    public List<String> getTarget(Long id) {
        log.info("value query: {}", String.format("target_%d", id));
        return storage.get(String.format("target_%d", id));
    }

    public List<String> getBenefit(Long id) {
        log.info("value query: {}", String.format("benefit_%d", id));
        return storage.get(String.format("benefit_%d", id));
    }
}
