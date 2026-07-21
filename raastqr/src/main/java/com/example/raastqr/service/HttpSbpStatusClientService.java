package com.example.raastqr.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
            ResponseEntity<String> response = restTemplate.postForEntity(statusUrl, null, String.class);
            String body = response.getBody();
            if (body == null || body.isBlank()) {
                return List.of();
            }

            String trimmedBody = body.trim();
            if (!looksLikePacs002Xml(trimmedBody)) {
                logger.warn("pacs.002 endpoint returned response that is not a pacs.002 XML message from {}", statusUrl);
                return List.of();
            }

            return List.of(trimmedBody);
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                logger.warn("pacs.002 polling POST endpoint not found: {}", statusUrl);
                return List.of();
            }
            throw ex;
        } catch (ResourceAccessException ex) {
            logger.warn("pacs.002 polling endpoint unavailable: {}", statusUrl);
            return List.of();
        }
    }
    private boolean looksLikePacs002Xml(String body) {
        String lowerBody = body.toLowerCase();
        return body.startsWith("<")
                && !lowerBody.startsWith("<!doctype html")
                && !lowerBody.startsWith("<html")
                && !lowerBody.contains("<body")
                && body.contains("pacs.002");
    }
}
