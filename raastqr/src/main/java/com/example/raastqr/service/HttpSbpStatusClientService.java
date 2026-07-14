package com.example.raastqr.service;

import java.util.List;

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

    public HttpSbpStatusClientService(RestTemplate restTemplate,
                                      @Value("${app.sbp-status-url}") String statusUrl) {
        this.restTemplate = restTemplate;
        this.statusUrl = statusUrl;
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
}
