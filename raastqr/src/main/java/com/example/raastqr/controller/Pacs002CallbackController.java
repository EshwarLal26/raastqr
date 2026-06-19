package com.example.raastqr.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Pacs002CallbackController {

    @PostMapping(value = "/pacs002", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE})
    public ResponseEntity<String> receivePacs002(@RequestBody String xml) {
        System.out.println("=== CALLBACK PACS.002 RECEIVED ===");
        System.out.println(xml);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/pacs002")
    public String pacs002Help() {
        return "Use POST with application/xml to send PACS.002";
    }
}
