package com.example.raastqr.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpSbpStatusClientService implements SbpStatusClientService {

    private static final Logger logger = LoggerFactory.getLogger(HttpSbpStatusClientService.class);

    private final RestTemplate restTemplate;
    private final String statusUrl;
    private final String pacs004Url;

    public HttpSbpStatusClientService(RestTemplate restTemplate,
                                      @Value("${app.sbp-status-url}") String statusUrl,
                                      @Value("${app.sbp-pacs004-url}") String pacs004Url) {
        this.restTemplate = restTemplate;
        this.statusUrl = statusUrl;
        this.pacs004Url = pacs004Url;
    }

    @Override
    public List<String> fetchPacs002Messages() {
        try {
            ResponseEntity<List<String>> response = restTemplate.exchange(
                    statusUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );
            return response.getBody() == null ? List.of() : response.getBody();
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                logger.warn("pacs.002 polling endpoint not found: {}", statusUrl);
                return List.of();
            }
            throw ex;
        } catch (ResourceAccessException ex) {
            logger.warn("pacs.002 polling endpoint unavailable: {}", statusUrl);
            return List.of();
        }
    }

    @Override
    public Optional<String> fetchPacs004Message() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(pacs004Url, String.class);
            if (response.getStatusCode() == HttpStatus.NO_CONTENT || response.getBody() == null || response.getBody().isBlank()) {
                return Optional.empty();
            }
            return Optional.of(response.getBody());
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND || ex.getStatusCode() == HttpStatus.NO_CONTENT) {
                logger.warn("pacs.004 polling endpoint not found or empty: {}", pacs004Url);
                return Optional.empty();
            }
            throw ex;
        } catch (ResourceAccessException ex) {
            logger.warn("pacs.004 polling endpoint unavailable: {}", pacs004Url);
            return Optional.empty();
        }
    }
}
