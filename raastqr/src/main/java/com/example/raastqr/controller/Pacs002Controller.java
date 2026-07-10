package com.example.raastqr.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raastqr.dto.Pacs002Dto;
import com.example.raastqr.service.Pacs002Service;
import com.example.raastqr.service.PaymentTrackingService;

@RestController
@RequestMapping("/api")
public class Pacs002Controller {

    private final Pacs002Service pacs002Service;
    private final PaymentTrackingService paymentTrackingService;

    public Pacs002Controller(Pacs002Service pacs002Service,
                             PaymentTrackingService paymentTrackingService) {
        this.pacs002Service = pacs002Service;
        this.paymentTrackingService = paymentTrackingService;
    }

    @PostMapping(
            value = "/pacs002",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<String> generatePacs002(@RequestBody Pacs002Dto dto) throws Exception {
        String xml = pacs002Service.buildPacs002Xml(dto);
        return ResponseEntity.ok(xml);
    }

    @PostMapping(
            value = "/pacs002",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE},
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> receivePacs002(@RequestBody String xml) {
        PaymentTrackingService.Pacs002CallbackData callbackData = pacs002Service.parseIncomingPacs002(xml);
        paymentTrackingService.updateFromPacs002(callbackData, xml);
        return ResponseEntity.ok("OK");
    }
}
