package com.example.raastqr.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class NoopSbpStatusClientService implements SbpStatusClientService {

    @Override
    public List<String> fetchPacs002Messages() {
        return List.of();
    }
}
