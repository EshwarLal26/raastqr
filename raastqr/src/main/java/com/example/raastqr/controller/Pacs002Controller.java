package com.example.raastqr.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.raastqr.dto.Pacs002Dto;
import com.example.raastqr.service.Pacs002Service;

@RestController
@RequestMapping("/api")
public class Pacs002Controller {

    private final Pacs002Service pacs002Service;

    public Pacs002Controller(Pacs002Service pacs002Service) {
        this.pacs002Service = pacs002Service;
    }

    // Generate PACS.002 XML from JSON DTO
    @PostMapping(
            value = "/pacs002",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<String> generatePacs002(@RequestBody Pacs002Dto dto) throws Exception {
        String xml = pacs002Service.buildPacs002Xml(dto);
        return ResponseEntity.ok(xml);
    }

    // Receive callback PACS.002 XML
    @PostMapping(
            value = "/pacs002",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE},
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> receivePacs002(@RequestBody String xml) {
        System.out.println("=== CALLBACK PACS.002 RECEIVED ===");
        System.out.println(xml);
        return ResponseEntity.ok("OK");
    }


    }
