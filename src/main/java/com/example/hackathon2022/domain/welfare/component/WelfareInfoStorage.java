package com.example.hackathon2022.domain.welfare.component;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WelfareInfoStorage {
    private ConcurrentHashMap<String, List<String>> storage = new ConcurrentHashMap<>();

    public void setTarget(Long id, List<String> target) {
        storage.put(String.format("target_%d", id), target);
    }

    public void setBenefit(Long id, List<String> benefit) {
        storage.put(String.format("benefit_%d", id), benefit);
    }

    public List<String> getTarget(Long id) {
        return storage.get(String.format("target_%d", id));
    }

    public List<String> getBenefit(Long id) {
        return storage.get(String.format("benefit_%d", id));
    }
}
