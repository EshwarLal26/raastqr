package com.example.raastqr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raastqr.dto.Pacs008Request;
import com.example.raastqr.dto.PaymentStatusResponse;
import com.example.raastqr.service.Pacs008ClientService;

@RestController
@RequestMapping("/api")
public class Pacs008Controller {

    private final Pacs008ClientService pacs008ClientService;

    public Pacs008Controller(Pacs008ClientService pacs008ClientService) {
        this.pacs008ClientService = pacs008ClientService;
    }

    @PostMapping("/pacs008")
    public ResponseEntity<String> generateXml(@RequestBody Pacs008Request request) throws Exception {
        return ResponseEntity.ok(pacs008ClientService.buildPacs008Xml(request));
    }

    @PostMapping("/pacs008/send")
    public ResponseEntity<PaymentStatusResponse> sendPacs008(@RequestBody Pacs008Request request) throws Exception {
        return ResponseEntity.ok(pacs008ClientService.sendAndTrackPacs008(request));
    }
}
