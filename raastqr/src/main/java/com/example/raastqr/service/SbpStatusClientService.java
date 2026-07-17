package com.example.raastqr.service;

import java.util.List;
import java.util.Optional;

public interface SbpStatusClientService {

    List<String> fetchPacs002Messages();

    Optional<String> fetchPacs004Message();
}
