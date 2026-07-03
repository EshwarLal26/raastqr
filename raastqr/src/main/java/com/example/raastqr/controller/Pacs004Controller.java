package com.example.raastqr.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raastqr.dto.Pacs004Dto;
import com.example.raastqr.service.Pacs004Service;

@RestController
@RequestMapping("/api")
public class Pacs004Controller {

    private final Pacs004Service pacs004Service;

    public Pacs004Controller(Pacs004Service pacs004Service) {
        this.pacs004Service = pacs004Service;
    }

    @PostMapping(
            value = "/pacs004",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<String> generatePacs004(@RequestBody Pacs004Dto dto) throws Exception {
        String xml = pacs004Service.buildPacs004Xml(dto);
        return ResponseEntity.ok(xml);
    }

    @PostMapping(
            value = "/pacs004",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE},
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> receivePacs004(@RequestBody String xml) {
        System.out.println("=== CALLBACK PACS.004 RECEIVED ===");
        System.out.println(xml);
        return ResponseEntity.ok("OK");
    }
}