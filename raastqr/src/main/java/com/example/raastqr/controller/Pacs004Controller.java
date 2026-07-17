package com.example.raastqr.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raastqr.dto.Pacs004Dto;
import com.example.raastqr.dto.PaymentStatusResponse;
import com.example.raastqr.service.Pacs004Service;
import com.example.raastqr.service.PaymentTrackingService;

@RestController
@RequestMapping("/api")
public class Pacs004Controller {

    private final Pacs004Service pacs004Service;
    private final PaymentTrackingService paymentTrackingService;

    public Pacs004Controller(Pacs004Service pacs004Service,
                             PaymentTrackingService paymentTrackingService) {
        this.pacs004Service = pacs004Service;
        this.paymentTrackingService = paymentTrackingService;
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
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PaymentStatusResponse> receivePacs004(@RequestBody String xml) {
        PaymentTrackingService.Pacs004CallbackData callbackData = pacs004Service.parseIncomingPacs004(xml);
        PaymentStatusResponse status = paymentTrackingService.updateFromPacs004(callbackData, xml, "PACS004_CALLBACK");
        return ResponseEntity.ok(status);
    }
}
