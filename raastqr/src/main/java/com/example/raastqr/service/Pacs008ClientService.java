package com.example.raastqr.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class Pacs008ClientService {

    private final RestTemplate restTemplate;
    private final String bankUrl;

    public Pacs008ClientService(RestTemplate restTemplate,
                                @Value("${app.bank-url}") String bankUrl) {
        this.restTemplate = restTemplate;
        this.bankUrl = bankUrl;
    }

    public void sendPacs008() {
        try {
            String requestXml = Files.readString(
                    Path.of("src/main/resources/xml-file/request.xml"),
                    StandardCharsets.UTF_8
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(requestXml, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(bankUrl, request, String.class);

            System.out.println("=== BANK RESPONSE (PACS.002) ===");
            System.out.println(response.getBody());
        } catch (IOException ex) {
            System.err.println("Error reading request.xml: " + ex.getMessage());
        } catch (HttpStatusCodeException ex) {
            System.err.println("HTTP Error: " + ex.getStatusCode());
            System.err.println(ex.getResponseBodyAsString());
        } catch (ResourceAccessException ex) {
            System.err.println("Connection Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
