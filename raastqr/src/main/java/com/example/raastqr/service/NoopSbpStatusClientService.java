package com.example.raastqr.service;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnMissingBean(SbpStatusClientService.class)
public class NoopSbpStatusClientService implements SbpStatusClientService {

    @Override
    public List<String> fetchPacs002Messages() {
        return List.of();
    }
}
